package com.hkbea.odinfw.ui.forms;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;

@SpringView(name="TodoForm")
public class TodoForm extends BaseForm {
    @Override
    protected Component getContentComponent() {
        FormLayout formLayout = new FormLayout();

        formLayout.addComponent(new Label("TODO"));

        return formLayout;
    }
}
