package com.hkbea.app.ui.forms;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

import javax.annotation.PostConstruct;

@SpringView(name="UserForm")
public class UserForm extends FormLayout implements View {
    @PostConstruct
    private void init() {
        this.setMargin(true);
        this.setSpacing(true);
        TextField nameTF = new TextField("Name");
        nameTF.setIcon(VaadinIcons.USER);
        nameTF.setRequiredIndicatorVisible(true);
        this.addComponent(nameTF);

        TextField emailTF = new TextField("Email");
        emailTF.setIcon(VaadinIcons.ENVELOPE);
        this.addComponent(emailTF);
// normally comes from validation by Binder
//        emailTF.setComponentError(new UserError("Doh!"));
    }
}
