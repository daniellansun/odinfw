package com.hkbea.odinfw.ui;

import com.vaadin.ui.UI;

/**
 * Utilities for handling UI
 */
public class UiUtils {
    public static OdinUI getUI() {
        return (OdinUI) UI.getCurrent();
    }

    public static String i18n(String code, String... args) {
        return getUI().i18n(code, args);
    }

    private UiUtils() {}
}
