package com.hkbea.odinfw.ui.forms;

import com.vaadin.spring.annotation.SpringView;

@SpringView(name="DruidMonitorForm")
public class DruidMonitorForm extends WebForm {
    protected String createWebURL() {
        return String.format("%s/druid/", getBaseUrl());
    }
}
