package com.hkbea.odinfw.ui;

import com.hkbea.app.ui.forms.LoginForm;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

@SpringUI
public class OdinUI extends UI implements ApplicationContextAware {
    private static final String ZW = "中文";
    private static final String EN = "English";
    private static final Locale LOCALE_ZH_CN = new Locale("zh", "CN");
    private static final Locale LOCALE_EN_US = new Locale("en", "US");

    @Autowired
    private SpringNavigator navigator;

    @Autowired
    private ResourceBundleMessageSource resourceBundleMessageSource;

    @Autowired
    private ApplicationEventPublisher publisher;

    private ApplicationContext applicationContext;

    @Override
    protected void init(VaadinRequest request) {
        Responsive.makeResponsive(this);

        this.getPage().setTitle(i18n("app.title"));

        Panel contentPanel = createContentPanel();

        VerticalLayout mainLayout = new VerticalLayout(createHeader(), contentPanel, createFooter());

        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(contentPanel, 1);
        mainLayout.setMargin(new MarginInfo(true, false));
        mainLayout.setHeight("100%");

        this.setContent(mainLayout);

        navigator.init(this, contentPanel);
//        navigator.setErrorView(InaccessibleErrorView.class);
    }

    private Panel createContentPanel() {
        Panel contentPanel = new ContentPanel();

        return contentPanel;
    }

    @EventListener
    public void onLoginEvent(LoginForm.LoginEvent event) {
        navigator.navigateTo("MainForm");
    }


    private HorizontalLayout createHeader() {
        HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");

        Label title = new Label("<strong style='font-size: 25px;'>" + i18n("app.title") + "</strong>", ContentMode.HTML);
        titleBar.addComponent(title);
        titleBar.setExpandRatio(title, 1.0f); // Expand

        NativeSelect languageSelect = createLanguageSelect();

//        languageSelect.setSizeUndefined(); // Take minimum space
        titleBar.addComponent(languageSelect);

        return titleBar;
    }

    private HorizontalLayout createFooter() {
        HorizontalLayout footer = new HorizontalLayout();
        footer.setWidth("100%");

        Label copyrightLabel = new Label(i18n("app.copyright", "2012"), ContentMode.HTML);
        copyrightLabel.setWidth(null);
        footer.addComponent(copyrightLabel);
        footer.setComponentAlignment(copyrightLabel, Alignment.MIDDLE_CENTER);
        footer.setExpandRatio(copyrightLabel, 1.0f); // Expand

        return footer;
    }

    private NativeSelect createLanguageSelect() {
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

            UI.getCurrent().getPage().reload();
        });

        return languageSelect;
    }


    public String i18n(String code, String... args) {
        Locale locale = VaadinSession.getCurrent().getLocale();

        return resourceBundleMessageSource.getMessage(code, args, locale);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }
}
