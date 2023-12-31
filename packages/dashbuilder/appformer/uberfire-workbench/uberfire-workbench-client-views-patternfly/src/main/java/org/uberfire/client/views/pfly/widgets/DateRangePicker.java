/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License. 
 */


package org.uberfire.client.views.pfly.widgets;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.jboss.errai.common.client.api.IsElement;
import org.jboss.errai.common.client.dom.Event;
import org.jboss.errai.common.client.dom.TextInput;
import org.jboss.errai.ui.client.local.spi.TranslationService;

import static org.uberfire.client.views.pfly.resources.i18n.Constants.ApplyLabel;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.April;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.August;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.CancelLabel;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.CustomRangeLabel;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.December;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.February;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.FridayShort;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.FromLabel;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.January;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.July;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.June;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.March;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.May;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.MondayShort;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.November;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.October;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.SaturdayShort;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.September;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.SundayShort;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.ThursdayShort;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.ToLabel;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.TuesdayShort;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.WednesdayShort;
import static org.uberfire.client.views.pfly.resources.i18n.Constants.WeekLabel;

/**
 * Wrapper component for <a href="http://www.daterangepicker.com">Date Range Picker</a>
 */
@Dependent
public class DateRangePicker implements IsElement {

    @Inject
    private TextInput input;

    @Inject
    private JQueryProducer.JQuery<DateRangePicker.JQueryDateRangePickerElement> jQuery;

    @Inject
    private TranslationService translationService;

    public void setup(final DateRangePickerOptions options,
                      final DateRangePickerCallback callback) {
        setupI18n(options);
        jQuery.wrap(getElement()).daterangepicker(options,
                                                  callback);
    }

    protected void setupI18n(final DateRangePickerOptions options) {
        options.setApplyLabel(translationService.format(ApplyLabel));
        options.setCancelLabel(translationService.format(CancelLabel));
        options.setCustomRangeLabel(translationService.format(CustomRangeLabel));
        options.setFromLabel(translationService.format(FromLabel));
        options.setToLabel(translationService.format(ToLabel));
        options.setWeekLabel(translationService.format(WeekLabel));
        options.setDaysOfWeek(new String[]{
                translationService.format(SundayShort),
                translationService.format(MondayShort),
                translationService.format(TuesdayShort),
                translationService.format(WednesdayShort),
                translationService.format(ThursdayShort),
                translationService.format(FridayShort),
                translationService.format(SaturdayShort)
        });

        options.setMonthNames(new String[]{
                translationService.format(January),
                translationService.format(February),
                translationService.format(March),
                translationService.format(April),
                translationService.format(May),
                translationService.format(June),
                translationService.format(July),
                translationService.format(August),
                translationService.format(September),
                translationService.format(October),
                translationService.format(November),
                translationService.format(December),
        });
    }

    public void addApplyListener(final DateTimePickerEventCallback callback) {
        jQuery.wrap(getElement()).on("apply.daterangepicker",
                                     callback);
    }

    @Override
    public TextInput getElement() {
        return input;
    }

    @JsType(isNative = true)
    public interface JQueryDateRangePickerElement extends JQueryProducer.JQueryElement {

        DateRangePickerElement daterangepicker(DateRangePickerOptions options,
                                               DateRangePickerCallback callback);

        void on(String event,
                DateTimePickerEventCallback callback);
    }

    @JsFunction
    @FunctionalInterface
    public interface DateRangePickerCallback {

        void update(Moment start,
                    Moment end,
                    String label);
    }

    @JsFunction
    @FunctionalInterface
    public interface DateTimePickerEventCallback {

        void onEvent(Event event,
                     DateRangePickerElement picker);
    }

    @JsType(isNative = true)
    public interface DateRangePickerElement {

        @JsProperty
        Moment getStartDate();

        void setStartDate(Moment startDate);

        @JsProperty
        Moment getEndDate();

        void setEndDate(Moment endDate);

        @JsProperty
        String getChosenLabel();
    }
}
