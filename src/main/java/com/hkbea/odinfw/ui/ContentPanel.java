package com.hkbea.odinfw.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;

@SpringViewDisplay
public class ContentPanel extends Panel implements ViewDisplay {
    public ContentPanel() {
        setSizeFull();
    }

    @Override
    public void showView(View view) {
        setContent((Component) view);
    }
}
