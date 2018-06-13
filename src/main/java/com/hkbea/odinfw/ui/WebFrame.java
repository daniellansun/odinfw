package com.hkbea.odinfw.ui;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;

public class WebFrame extends BrowserFrame {
    public WebFrame() {}

    public void setSource(String url) {
        super.setSource(new ExternalResource(url));
    }
}
