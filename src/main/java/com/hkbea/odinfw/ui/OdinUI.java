package com.hkbea.odinfw.ui;

import com.vaadin.annotations.Title;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.Arrays;

@Title("Odin Framework")
@SpringUI
public class OdinUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        Responsive.makeResponsive(this);

        HorizontalSplitPanel body = createBody(request);
        VerticalLayout mainLayout = new VerticalLayout(createHeader(request), body, createFooter(request));
        mainLayout.setSizeFull();

        mainLayout.setExpandRatio(body, 1);

//        mainLayout.setMargin(new MarginInfo(true, false));

        this.setContent(mainLayout);
        mainLayout.setHeight("100%");
    }

    private HorizontalLayout createHeader(VaadinRequest request) {
        HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");

        Label title = new Label("<strong style='font-size: 25px;'>Odin Framework Demo</strong>", ContentMode.HTML);
        titleBar.addComponent(title);
        titleBar.setExpandRatio(title, 1.0f); // Expand

        Label titleComment = new Label("for BEA");
        titleComment.setSizeUndefined(); // Take minimum space
        titleBar.addComponent(titleComment);

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
        Tree<String> tree = new Tree<>("Hardware Inventory");

        TreeData<String> data = new TreeData<>();
        data.addItems(null, "Instrument Management", "System Administration")
                .addItems("Instrument Management", "Manage Instruments")
                .addItems("System Administration", "Manage Users", "Manage Roles");

        tree.setDataProvider(new TreeDataProvider<>(data));
        tree.expand("Instrument Management", "System Administration");
        tree.setItemIconGenerator(item -> {
            if (Arrays.asList("Instrument Management", "System Administration").contains(item)) {
                return VaadinIcons.MENU;
            }

            return VaadinIcons.ANGLE_RIGHT;
        });

        Panel treePanel = new Panel(tree);
        treePanel.setHeight("100%");

        return treePanel;
    }

    private Panel createContentPanel(VaadinRequest request) {
        Panel contentPanel = new Panel();
        contentPanel.setSizeFull();

        return contentPanel;
    }

    private HorizontalLayout createFooter(VaadinRequest request) {
        HorizontalLayout footer = new HorizontalLayout();
        footer.setWidth("100%");

        Label copyrightLabel = new Label("Copyright ©2012 The Bank of East Asia (China) Limited", ContentMode.HTML);
        copyrightLabel.setWidth(null);
        footer.addComponent(copyrightLabel);
        footer.setComponentAlignment(copyrightLabel, Alignment.MIDDLE_CENTER);
        footer.setExpandRatio(copyrightLabel, 1.0f); // Expand

        return footer;
    }
}
