package com.hkbea.app.ui.forms;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.PostConstruct;

@SpringView(name="")
public class LoginForm extends Panel implements View {
    @Autowired
    public ApplicationEventPublisher publisher;

    @PostConstruct
    private void init() {
        FormLayout formLayout = new FormLayout();

        TextField idTF = new TextField("ID");
        idTF.setIcon(VaadinIcons.USER);
        idTF.setRequiredIndicatorVisible(true);
        formLayout.addComponent(idTF);

        PasswordField passwordTF = new PasswordField("Password");
        passwordTF.setIcon(VaadinIcons.PASSWORD);
        passwordTF.setRequiredIndicatorVisible(true);
        formLayout.addComponent(passwordTF);

        Button loginBtn = new Button("Login");

        loginBtn.addClickListener(event -> {
            publisher.publishEvent(new LoginEvent(this));
        });

        formLayout.addComponent(loginBtn);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(formLayout);

        this.setSizeFull();
        this.setWidth("100%");
        this.setHeight("100%");
//        this.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);
        horizontalLayout.setExpandRatio(formLayout, 1);
        horizontalLayout.setMargin(new MarginInfo(true, false, true, false));

        this.setContent(horizontalLayout);
    }

    public static class LoginEvent extends ApplicationEvent {
        public LoginEvent(Component source) {
            super(source);
        }
    }
}
