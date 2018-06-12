package com.hkbea.odinfw.ui;

import com.vaadin.data.TreeData;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MenuTree extends Tree<MenuTree.Menu> {
    public MenuTree() {
        List<Menu> rootMenuList = addSubMenus(null); // add root menus

        if (rootMenuList.size() > 0) {
            addSubMenus(rootMenuList.get(0)); // add sub menus for the first root by default
        }

        this.addItemClickListener(event -> {
            Menu item = event.getItem();

            if (item.isLeaf()) {
                Optional<Component> optionalContentPanel = UiUtils.findComponentById(OdinUI.ID_CONTENT_PANEL);

                optionalContentPanel.ifPresent(component -> {
                    if (component instanceof Panel) {
                        ((Panel) component).setContent(new Label("Hello, world!" + System.nanoTime()));
                    } else {
                        throw new IllegalStateException("The content panel should be instanceof Panel, but it is not");
                    }
                });
            } else {
                addSubMenus(item);
            }
        });
    }

    private List<Menu> addSubMenus(Menu menu) {
        List<Menu> subMenuList = findSubMenus(menu);

        TreeData<Menu> treeData = this.getTreeData();

        for (Menu subMenu : subMenuList) {
            if (treeData.contains(subMenu)) {
                continue;
            }

            treeData.addItem(menu, subMenu);
        }

        this.setItemIconGenerator(item -> {
            if (!item.isLeaf()) {
                return VaadinIcons.MENU;
            }

            return VaadinIcons.ANGLE_RIGHT;
        });

        this.getDataProvider().refreshAll();

        this.expand(menu);

        return subMenuList;
    }

    /**
     * TODO menu data should be loaded from DB
     * @param menu
     * @return
     */
    private List<Menu> findSubMenus(Menu menu) {
        if (null == menu) {
            return Arrays.asList(
                    new MenuTree.Menu("1", "Instrument Management", false, null),
                    new MenuTree.Menu("2", "System Administration", false, null)
            );
        }

        if ("1".equals(menu.getId())) {
            return Arrays.asList(
                    new MenuTree.Menu("11", "Manage Instruments", true, menu.getId())
            );
        }

        if ("2".equals(menu.getId())) {
            return Arrays.asList(
                    new MenuTree.Menu("21", "Manage Users", true, menu.getId()),
                    new MenuTree.Menu("22", "Manage Roles", true, menu.getId())
            );
        }

        return Collections.emptyList();
    }

    public static class Menu {
        private String id;
        private String text;
        private boolean leaf;
        private String parentId;

        public Menu(String id, String text, boolean leaf, String parentId) {
            this.id = id;
            this.text = text;
            this.leaf = leaf;
            this.parentId = parentId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isLeaf() {
            return leaf;
        }

        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Menu menu = (Menu) o;
            return Objects.equals(id, menu.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
