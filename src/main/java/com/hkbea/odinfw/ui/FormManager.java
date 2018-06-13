package com.hkbea.odinfw.ui;

import com.vaadin.ui.Component;

public class FormManager {
    public static final FormManager INSTANCE = new FormManager();

    private FormManager() {}

    public Component createForm(String shortName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (Component) Class.forName("com.hkbea.app.ui.forms." + shortName).newInstance();
    }
}
