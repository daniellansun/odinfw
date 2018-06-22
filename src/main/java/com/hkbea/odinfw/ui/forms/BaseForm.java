package com.hkbea.odinfw.ui.forms;

import com.hkbea.odinfw.ui.MenuTree;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

public abstract class BaseForm extends VerticalLayout implements View {
    private static final String ATTR_MENU_TREE_PANEL = "__menuTreePanel";

    @PostConstruct
    protected void init() {
        HorizontalSplitPanel body = createBody();
        this.addComponent(body);

        this.setSizeFull();
        this.setExpandRatio(body, 1);
        this.setMargin(new MarginInfo(true, false));
        this.setHeight("100%");
    }

    private HorizontalSplitPanel createBody() {
        HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel();
        Page currentPage = UI.getCurrent().getPage();
        int width = currentPage.getBrowserWindowWidth();
        int height = currentPage.getBrowserWindowHeight();

        horizontalSplitPanel.setSplitPosition(Math.min(width, height) * 0.39f, Sizeable.Unit.PIXELS, false);

        horizontalSplitPanel.setFirstComponent(getOrCreateMenuTree());
        horizontalSplitPanel.setSecondComponent(getContentComponent());

        return horizontalSplitPanel;
    }

    private MenuTree getOrCreateMenuTree() {
        MenuTree menuTree = (MenuTree) VaadinSession.getCurrent().getAttribute(ATTR_MENU_TREE_PANEL);
        if (null == menuTree) {
            menuTree = createMenuTreePanel();
            VaadinSession.getCurrent().setAttribute(ATTR_MENU_TREE_PANEL, menuTree);
        }
        return menuTree;
    }

    private MenuTree createMenuTreePanel() {
        MenuTree menuTree = new MenuTree();

        menuTree.setHeight("100%");

        return menuTree;
    }

    protected abstract Component getContentComponent();
}
