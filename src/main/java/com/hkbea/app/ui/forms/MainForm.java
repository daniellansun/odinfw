package com.hkbea.app.ui.forms;

import com.hkbea.odinfw.ui.forms.BaseForm;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

@SpringView(name="MainForm")
public class MainForm extends BaseForm {

    @Override
    protected Component getContentComponent() {
        return new VerticalLayout();
    }

//    private Panel createContentPanel() {
//        Panel contentPanel = new ContentPanel();
//        contentPanel.setId(ID_CONTENT_PANEL);
//
//        return contentPanel;
//    }

}
