package com.hkbea.odinfw.ui;

import com.vaadin.annotations.Title;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Title("Odin Framework Demo")
@SpringUI
public class OdinUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        Responsive.makeResponsive(this);

        Panel body = createBody(request);
        VerticalLayout mainLayout = new VerticalLayout(createHeader(request), body);
        mainLayout.setSizeFull();

        mainLayout.setExpandRatio(body, 1);

        this.setContent(mainLayout);
        mainLayout.setHeight("100%");
    }

    private Panel createHeader(VaadinRequest request) {
        return new Panel(new Label("Odin Framework"));
    }

    private Panel createBody(VaadinRequest request) {
        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.setHeight("100%");

        contentLayout.addComponent(new Button("Click me2", e -> Notification.show("Hello, Odin Framework!")));

        final Panel contentPanel = new Panel(contentLayout);
        contentPanel.setSizeFull();

        return contentPanel;
    }
}
