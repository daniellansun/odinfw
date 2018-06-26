package com.hkbea.odinfw.ui.forms;

import com.hkbea.odinfw.ui.WebFrame;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.net.URI;

@SpringView(name="H2ConsoleForm")
public class H2ConsoleForm extends BaseForm {
    @Override
    protected Component getContentComponent() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setMargin(false);

        WebFrame webFrame = new WebFrame();
        webFrame.setSizeFull();
        webFrame.setSource(createH2ConsoleLink());
        verticalLayout.addComponent(webFrame);
        verticalLayout.setHeight("100%");
        verticalLayout.setExpandRatio(webFrame, 1);

        return verticalLayout;
    }

    private String createH2ConsoleLink() {
        URI uri = UI.getCurrent().getPage().getLocation();
        return String.format("%s:%s/h2", uri.getScheme(), uri.getSchemeSpecificPart());
    }
}
