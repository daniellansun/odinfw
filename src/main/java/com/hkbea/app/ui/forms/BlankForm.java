package com.hkbea.app.ui.forms;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;

import javax.annotation.PostConstruct;

@SpringView(name="BlankForm")
public class BlankForm extends FormLayout implements View {
    @PostConstruct
    private void init() {
        this.addComponent(new Label("TODO"));
    }
}
