package com.hkbea.odinfw.ui.forms;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;


public abstract class QueryForm<T> extends BaseForm {
    @Override
    protected Component getContentComponent() {
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.setMargin(new MarginInfo(false, true, false, true));

        verticalLayout.addComponent(createFieldInputsPanel());

        Grid<T> resultGrid = createResultGrid();
        resultGrid.setWidth("100%");
        resultGrid.setVisible(false);
        verticalLayout.addComponent(resultGrid);

        return verticalLayout;
    }

    private Panel createFieldInputsPanel() {
        Panel fieldInputsPanel = new Panel(i18n("text.inputfields"), createFieldInputsLayout());

        return fieldInputsPanel;
    }

    protected abstract AbstractOrderedLayout createFieldInputsLayout();
    protected abstract Grid<T> createResultGrid();
}
