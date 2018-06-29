package com.hkbea.odinfw.ui;

import com.hkbea.odinfw.addons.paginator.Paginator;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

public class PaginableGridLayout<T> extends VerticalLayout {
    private Grid<T> grid;
    private Paginator paginator;

    public PaginableGridLayout(Grid<T> grid, Paginator paginator) {
        this.grid = grid;
        this.paginator = paginator;

        grid.setWidth("100%");

        this.addComponent(grid);
        this.addComponent(paginator);
    }
}
