package com.hkbea.odinfw.ui.forms;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Grid;

import java.util.List;

public abstract class BlankQueryForm<T, S> extends QueryForm<T, S> {
    @Override
    protected AbstractOrderedLayout createFieldInputsLayout() {
        return null;
    }

    @Override
    protected Grid<T> createResultGrid() {
        return null;
    }

    @Override
    protected S createFilterExample() {
        return null;
    }

    @Override
    protected long getTotalCount(S example) {
        return 0;
    }

    @Override
    protected List<T> getResultList(S example) {
        return null;
    }
}
