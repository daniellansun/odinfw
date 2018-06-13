package com.hkbea.app.ui.forms;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import javax.annotation.PostConstruct;

import static com.hkbea.odinfw.ui.UiUtils.i18n;

@SpringView(name="UserForm")
public class UserForm extends HorizontalLayout implements View {
    @PostConstruct
    private void init() {
        this.setMargin(true);
        this.setSpacing(true);

        TextField idTF = new TextField(i18n("text.id"));
        idTF.setIcon(VaadinIcons.USER);
        idTF.setRequiredIndicatorVisible(true);
        this.addComponent(idTF);

        TextField nameTF = new TextField(i18n("text.name"));
        nameTF.setIcon(VaadinIcons.USER);
        nameTF.setRequiredIndicatorVisible(true);
        this.addComponent(nameTF);

        PasswordField passwordTF = new PasswordField(i18n("text.password"));
        passwordTF.setIcon(VaadinIcons.PASSWORD);
        passwordTF.setRequiredIndicatorVisible(true);
        this.addComponent(passwordTF);

        TextField emailTF = new TextField(i18n("text.email"));
        emailTF.setIcon(VaadinIcons.ENVELOPE);
        this.addComponent(emailTF);
// normally comes from validation by Binder
//        emailTF.setComponentError(new UserError("Doh!"));
    }
}
