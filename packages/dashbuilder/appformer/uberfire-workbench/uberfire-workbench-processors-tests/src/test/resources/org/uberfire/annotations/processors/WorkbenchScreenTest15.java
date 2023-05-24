package org.uberfire.annotations.processors;

import com.google.gwt.user.client.ui.IsWidget;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;

@WorkbenchScreen(identifier = "test15")
public class WorkbenchScreenTest15 {

    @WorkbenchPartView
    public IsWidget getView() {
        return null;
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "title";
    }

}
