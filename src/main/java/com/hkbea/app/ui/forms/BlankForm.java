package com.hkbea.app.ui.forms;

import com.hkbea.odinfw.ui.forms.BaseForm;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;

@SpringView(name="BlankForm")
public class BlankForm extends BaseForm {
    @Override
    protected Component getContentComponent() {
        FormLayout formLayout = new FormLayout();

        formLayout.addComponent(new Label("TODO"));

        return formLayout;
    }
}
