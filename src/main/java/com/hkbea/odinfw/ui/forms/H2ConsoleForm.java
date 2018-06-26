package com.hkbea.odinfw.ui.forms;

import com.vaadin.spring.annotation.SpringView;

@SpringView(name="H2ConsoleForm")
public class H2ConsoleForm extends WebForm {
    protected String createWebURL() {
        return String.format("%s/h2/", getBaseUrl());
    }
}
