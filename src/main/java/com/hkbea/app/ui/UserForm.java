package com.hkbea.app.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.UserError;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class UserForm extends FormLayout {
    public UserForm() {
        init();
    }

    private void init() {
        TextField tf1 = new TextField("Name");
        tf1.setIcon(VaadinIcons.USER);
        tf1.setRequiredIndicatorVisible(true);
        this.addComponent(tf1);

        TextField tf2 = new TextField("Street address");
        tf2.setIcon(VaadinIcons.ROAD);
        this.addComponent(tf2);

        TextField tf3 = new TextField("Postal code");
        tf3.setIcon(VaadinIcons.ENVELOPE);
        this.addComponent(tf3);
// normally comes from validation by Binder
        tf3.setComponentError(new UserError("Doh!"));
    }
}
