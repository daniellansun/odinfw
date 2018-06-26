package com.hkbea.odinfw.ui.forms;

import com.hkbea.odinfw.ui.WebFrame;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.net.URI;

public abstract class WebForm extends BaseForm {
    @Override
    protected Component getContentComponent() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setMargin(false);

        WebFrame webFrame = new WebFrame();
        webFrame.setSizeFull();
        webFrame.setSource(createWebURL());
        verticalLayout.addComponent(webFrame);
        verticalLayout.setHeight("100%");
        verticalLayout.setExpandRatio(webFrame, 1);

        return verticalLayout;
    }

    protected String getBaseUrl() {
        URI uri = UI.getCurrent().getPage().getLocation();
        String urlStr = String.format("%s:%s", uri.getScheme(), uri.getSchemeSpecificPart());

        if (urlStr.endsWith("/")) {
            urlStr = urlStr.substring(0, urlStr.length() - 1);
        }

        return urlStr;
    }

    protected abstract String createWebURL();
}
