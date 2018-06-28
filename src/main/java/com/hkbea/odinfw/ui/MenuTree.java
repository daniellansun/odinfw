package com.hkbea.odinfw.ui;

import com.hkbea.odinfw.domain.FwMenu;
import com.hkbea.odinfw.domain.FwMenuExample;
import com.hkbea.odinfw.repositories.FwMenuMapper;
import com.vaadin.data.TreeData;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;

import java.util.List;

public class MenuTree extends Tree<FwMenu> {
    public MenuTree() {
        List<FwMenu> rootMenuList = addSubMenus(null); // add root menus

        for (FwMenu rootMenu : rootMenuList) {
            addSubMenus(rootMenu);
        }

        this.addItemClickListener(event -> {
            FwMenu item = event.getItem();

            if ("1".equals(item.getLeaf())) {
                if (null != item.getFormName()) {
                    UI.getCurrent().getNavigator().navigateTo(item.getFormName());
                }
            } else {
                addSubMenus(item);
            }
        });
    }

    private List<FwMenu> addSubMenus(FwMenu menu) {
        List<FwMenu> subMenuList = findSubMenus(menu);

        TreeData<FwMenu> treeData = this.getTreeData();

        for (FwMenu subMenu : subMenuList) {
            if (treeData.contains(subMenu)) {
                continue;
            }

            treeData.addItem(menu, subMenu);
        }

        this.setItemIconGenerator(item -> {
            if (!"1".equals(item.getLeaf())) {
                return VaadinIcons.MENU;
            }

            return VaadinIcons.ANGLE_RIGHT;
        });

        this.getDataProvider().refreshAll();

        this.expand(menu);

        return subMenuList;
    }

    /**
     * @param menu
     * @return
     */
    private List<FwMenu> findSubMenus(FwMenu menu) {
        FwMenuMapper fwMenuMapper = UiUtils.getUI().getApplicationContext().getBean(FwMenuMapper.class);
        FwMenuExample fme = new FwMenuExample();

        String parentId;
        if (null == menu) {
            parentId = "0";
        } else {
            parentId = menu.getId();
        }

        fme.createCriteria().andParentIdEqualTo(parentId);

        return fwMenuMapper.selectByExample(fme);
    }
}
