package com.hkbea.odinfw.ui;

import com.vaadin.annotations.Title;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

@Title("Odin Framework")
@SpringUI
public class OdinUI extends UI {
    public static final String ID_CONTENT_PANEL = "contentPanel";
    private static final String ZW = "中文";
    private static final String EN = "English";
    private static final Locale LOCALE_ZH_CN = new Locale("zh", "CN");
    private static final Locale LOCALE_EN_US = new Locale("en", "US");

    @Autowired
    private SpringNavigator navigator;

    @Autowired
    private ResourceBundleMessageSource resourceBundleMessageSource;

    @Override
    protected void init(VaadinRequest request) {
        Responsive.makeResponsive(this);

        HorizontalSplitPanel body = createBody(request);
        navigator.init(this, (Panel) body.getSecondComponent());
//        navigator.setErrorView(InaccessibleErrorView.class);

        VerticalLayout mainLayout = new VerticalLayout(createHeader(request), body, createFooter(request));
        mainLayout.setSizeFull();

        mainLayout.setExpandRatio(body, 1);

        mainLayout.setMargin(new MarginInfo(true, false));

        this.setContent(mainLayout);
        mainLayout.setHeight("100%");
    }

    private HorizontalLayout createHeader(VaadinRequest request) {
        HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");

        Label title = new Label("<strong style='font-size: 25px;'>" + i18n("app.title") + "</strong>", ContentMode.HTML);
        titleBar.addComponent(title);
        titleBar.setExpandRatio(title, 1.0f); // Expand

        NativeSelect languageSelect = createLanguageSelect(request);

//        languageSelect.setSizeUndefined(); // Take minimum space
        titleBar.addComponent(languageSelect);

        return titleBar;
    }

    private HorizontalSplitPanel createBody(VaadinRequest request) {
        HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
        Page currentPage = UI.getCurrent().getPage();
        int width = currentPage.getBrowserWindowWidth();
        int height = currentPage.getBrowserWindowHeight();

        horizontalSplitPanel.setSplitPosition(Math.min(width, height) * 0.39f, Sizeable.Unit.PIXELS, false);
        horizontalSplitPanel.setFirstComponent(createMenuTreePanel(request));
        horizontalSplitPanel.setSecondComponent(createContentPanel(request));

        return horizontalSplitPanel;
    }

    private Panel createMenuTreePanel(VaadinRequest request) {
        MenuTree menuTree = new MenuTree();

        Panel treePanel = new Panel(menuTree);
        treePanel.setHeight("100%");

        return treePanel;
    }

    private Panel createContentPanel(VaadinRequest request) {
        Panel contentPanel = new ContentPanel();
        contentPanel.setId(ID_CONTENT_PANEL);

        return contentPanel;
    }

    private HorizontalLayout createFooter(VaadinRequest request) {
        HorizontalLayout footer = new HorizontalLayout();
        footer.setWidth("100%");

        Label copyrightLabel = new Label(i18n("app.copyright", "2012"), ContentMode.HTML);
        copyrightLabel.setWidth(null);
        footer.addComponent(copyrightLabel);
        footer.setComponentAlignment(copyrightLabel, Alignment.MIDDLE_CENTER);
        footer.setExpandRatio(copyrightLabel, 1.0f); // Expand

        return footer;
    }

    private NativeSelect createLanguageSelect(VaadinRequest request) {
        NativeSelect<String> languageSelect = new NativeSelect<>(i18n("select.language"), Arrays.asList(EN, ZW));
        languageSelect.setWidth("105px");
        languageSelect.setEmptySelectionAllowed(false);

        if (LOCALE_ZH_CN.equals(VaadinSession.getCurrent().getLocale())) {
            languageSelect.setSelectedItem(ZW);
        } else {
            languageSelect.setSelectedItem(EN);
        }

        languageSelect.addSelectionListener(event -> {
            Optional<String> item = event.getSelectedItem();

            VaadinSession session = VaadinSession.getCurrent();
            if (ZW.equals(item.get())) {
                session.setLocale(LOCALE_ZH_CN);
            } else {
                session.setLocale(LOCALE_EN_US);
            }

            this.getPage().reload();
        });

        return languageSelect;
    }


    public String i18n(String code, String... args) {
        Locale locale = VaadinSession.getCurrent().getLocale();

        return resourceBundleMessageSource.getMessage(code, args, locale);
    }
}
