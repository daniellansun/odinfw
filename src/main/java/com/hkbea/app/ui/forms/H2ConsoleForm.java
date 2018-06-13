package com.hkbea.app.ui.forms;

import com.hkbea.odinfw.ui.WebFrame;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.net.URI;

public class H2ConsoleForm extends VerticalLayout {
    public H2ConsoleForm() {
        init();
    }

    private void init() {
        WebFrame webFrame = new WebFrame();
        webFrame.setSizeFull();
        webFrame.setSource(createH2ConsoleLink());
        this.addComponent(webFrame);
        this.setHeight("100%");
        this.setExpandRatio(webFrame, 1);
    }

    private String createH2ConsoleLink() {
        URI uri = UI.getCurrent().getPage().getLocation();
        return String.format("%s:%s/h2", uri.getScheme(), uri.getSchemeSpecificPart());
    }
}
