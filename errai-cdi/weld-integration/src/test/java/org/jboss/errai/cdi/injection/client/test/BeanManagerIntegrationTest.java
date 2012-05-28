package org.jboss.errai.cdi.injection.client.test;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Set;

import org.jboss.errai.cdi.injection.client.ApplicationScopedBean;
import org.jboss.errai.cdi.injection.client.CommonInterface;
import org.jboss.errai.cdi.injection.client.CommonInterfaceB;
import org.jboss.errai.cdi.injection.client.DependentScopedBean;
import org.jboss.errai.cdi.injection.client.DependentScopedBeanWithDependencies;
import org.jboss.errai.cdi.injection.client.InheritedApplicationScopedBean;
import org.jboss.errai.cdi.injection.client.qualifier.LincolnBar;
import org.jboss.errai.cdi.injection.client.qualifier.QualA;
import org.jboss.errai.cdi.injection.client.qualifier.QualAppScopeBeanA;
import org.jboss.errai.cdi.injection.client.qualifier.QualAppScopeBeanB;
import org.jboss.errai.cdi.injection.client.qualifier.QualB;
import org.jboss.errai.cdi.injection.client.qualifier.QualEnum;
import org.jboss.errai.cdi.injection.client.qualifier.QualParmAppScopeBeanApples;
import org.jboss.errai.cdi.injection.client.qualifier.QualParmAppScopeBeanOranges;
import org.jboss.errai.cdi.injection.client.qualifier.QualV;
import org.jboss.errai.enterprise.client.cdi.AbstractErraiCDITest;
import org.jboss.errai.enterprise.client.cdi.api.CDI;
import org.jboss.errai.ioc.client.container.IOC;
import org.jboss.errai.ioc.client.container.IOCBeanDef;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.ioc.client.container.IOCResolutionException;

/**
 * @author Mike Brock
 */
public class BeanManagerIntegrationTest extends AbstractErraiCDITest {

  @Override
  public String getModuleName() {
    return "org.jboss.errai.cdi.injection.InjectionTestModule";
  }

  public void testBeanManagerLookupSimple() {
    final IOCBeanDef<ApplicationScopedBean> bean = IOC.getBeanManager().lookupBean(ApplicationScopedBean.class);
    assertNotNull(bean);
  }

  public void testBeanManagerLookupInheritedScopeBean() {
    final IOCBeanDef<InheritedApplicationScopedBean> bean
            = IOC.getBeanManager().lookupBean(InheritedApplicationScopedBean.class);
    assertNotNull("inherited application scoped bean did not lookup", bean);

    final InheritedApplicationScopedBean beanInst = bean.getInstance();
    assertNotNull("bean instance is null", beanInst);

    final DependentScopedBean bean1 = beanInst.getBean1();
    assertNotNull("bean1 is null", bean1);

    final DependentScopedBeanWithDependencies beanWithDependencies = beanInst.getBeanWithDependencies();
    assertNotNull("beanWithDependencies is null", beanWithDependencies);

    final DependentScopedBean bean2 = beanWithDependencies.getBean();
    assertNotSame("bean1 and bean2 should be different", bean1, bean2);

    final InheritedApplicationScopedBean beanInst2 = bean.getInstance();
    assertSame("bean is not observing application scope", beanInst, beanInst2);
  }

  public void testBeanManagerAPIs() {
    final IOCBeanManager mgr = IOC.getBeanManager();
    final IOCBeanDef<QualAppScopeBeanA> bean = mgr.lookupBean(QualAppScopeBeanA.class);

    final Set<Annotation> a = bean.getQualifiers();
    assertEquals("there should be one qualifier", 1, a.size());
    assertEquals("wrong qualifier", QualA.class, a.iterator().next().annotationType());

    assertEquals("unmanaged bean should have no entries", 0, mgr.lookupBeans(String.class).size());
    assertEquals("unmanaged bean should return null bean ref", null, mgr.lookupBean(String.class));
  }

  public void testQualifiedLookup() {
    final QualA qualA = new QualA() {
      @Override
      public Class<? extends Annotation> annotationType() {
        return QualA.class;
      }
    };

    final QualB qualB = new QualB() {
      @Override
      public Class<? extends Annotation> annotationType() {
        return QualB.class;
      }
    };

    final Collection<IOCBeanDef> beans = IOC.getBeanManager().lookupBeans(CommonInterface.class);
    assertEquals("wrong number of beans", 2, beans.size());

    final IOCBeanDef<CommonInterface> beanA = IOC.getBeanManager().lookupBean(CommonInterface.class, qualA);
    assertNotNull("no bean found", beanA);
    assertTrue("wrong bean looked up", beanA.getInstance() instanceof QualAppScopeBeanA);

    final IOCBeanDef<CommonInterface> beanB = IOC.getBeanManager().lookupBean(CommonInterface.class, qualB);
    assertNotNull("no bean found", beanB);
    assertTrue("wrong bean looked up", beanB.getInstance() instanceof QualAppScopeBeanB);
  }

  public void testQualifierLookupWithAnnoAttrib() {
    final QualV qualApples = new QualV() {
      @Override
      public QualEnum value() {
        return QualEnum.APPLES;
      }

      @Override
      public Class<? extends Annotation> annotationType() {
        return QualV.class;
      }

      @Override
      public int amount() {
        return 5;
      }
    };

    final QualV qualOranges = new QualV() {
      @Override
      public QualEnum value() {
        return QualEnum.ORANGES;
      }

      @Override
      public Class<? extends Annotation> annotationType() {
        return QualV.class;
      }

      @Override
      public int amount() {
        return 6;
      }
    };

    final Collection<IOCBeanDef> beans = IOC.getBeanManager().lookupBeans(CommonInterfaceB.class);
    assertEquals("wrong number of beans", 2, beans.size());

    final IOCBeanDef<CommonInterfaceB> beanA = IOC.getBeanManager().lookupBean(CommonInterfaceB.class, qualApples);
    assertNotNull("no bean found", beanA);
    assertTrue("wrong bean looked up", beanA.getInstance() instanceof QualParmAppScopeBeanApples);

    final IOCBeanDef<CommonInterfaceB> beanB = IOC.getBeanManager().lookupBean(CommonInterfaceB.class, qualOranges);
    assertNotNull("no bean found", beanB);
    assertTrue("wrong bean looked up", beanB.getInstance() instanceof QualParmAppScopeBeanOranges);
  }

  public void testQualifiedLookupFailure() {
    final LincolnBar wrongAnno = new LincolnBar() {
      @Override
      public Class<? extends Annotation> annotationType() {
        return LincolnBar.class;
      }
    };

    try {
      final IOCBeanDef<CommonInterface> bean = IOC.getBeanManager().lookupBean(CommonInterface.class);
      fail("should have thrown an exception, but got: " + bean);
    }
    catch (IOCResolutionException e) {
      assertTrue("wrong exception thrown", e.getMessage().contains("multiple matching"));
    }

    try {
      final IOCBeanDef<CommonInterface> bean = IOC.getBeanManager().lookupBean(CommonInterface.class, wrongAnno);
      fail("should have thrown an exception, but got: " + bean);
    }
    catch (IOCResolutionException e) {
      assertTrue("wrong exception thrown", e.getMessage().contains("no matching"));
    }
  }
}