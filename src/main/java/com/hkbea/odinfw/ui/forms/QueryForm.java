package com.hkbea.odinfw.ui.forms;

import com.hkbea.odinfw.addons.paginator.PaginationResource;
import com.hkbea.odinfw.addons.paginator.Paginator;
import com.hkbea.odinfw.ui.PaginableGridLayout;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Value;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


public abstract class QueryForm<T, S> extends BaseForm {
    @Value("${paginator.default.limit}")
    private Integer paginatorDefaultLimit;

    private PaginableGridLayout paginableGridLayout;
    private Grid<T> resultGrid;
    private Paginator paginator;

    @Override
    protected Component getContentComponent() {
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.setMargin(new MarginInfo(false, true, false, true));

        verticalLayout.addComponent(createFieldInputsPanel());

        resultGrid = createResultGrid();

        if (null != resultGrid) {
            resultGrid.setWidth("100%");
            resultGrid.setHeightByRows(paginatorDefaultLimit);

            paginator = createPaginator();

            paginableGridLayout = new PaginableGridLayout<>(resultGrid, paginator);
            paginableGridLayout.setMargin(false);
            paginableGridLayout.setVisible(false);

            verticalLayout.addComponent(paginableGridLayout);
        }

        return verticalLayout;
    }

    protected void showQueryResult() {
        if (null != paginator) {
            paginator.fireFirstButtonClickedEvent();
        }

        if (null != paginableGridLayout) {
            paginableGridLayout.setVisible(true);
        }
    }

    private Panel createFieldInputsPanel() {
        Panel fieldInputsPanel = new Panel(createFieldInputsTitle(), createFieldInputsLayout());

        return fieldInputsPanel;
    }

    protected String createFieldInputsTitle() {
        return i18n("text.inputfields");
    }

    protected abstract AbstractOrderedLayout createFieldInputsLayout();
    protected abstract Grid<T> createResultGrid();
    protected Paginator createPaginator() {
        long totalCnt = getTotalCount(createFilterExample());
        final Paginator paginator = Paginator.createPaginator(totalCnt, 1, paginatorDefaultLimit);

        paginator.addPageChangeListener(event -> {
            S ude = createFilterExample();
            paginator.setTotalCount(getTotalCount(ude));

            setOffset(event, ude);
            setLimit(event, ude);

            List<T> userDatList = getResultList(ude);
            resultGrid.setItems(userDatList);
            resultGrid.scrollToStart();
        });

        return paginator;
    }

    private void setLimit(PaginationResource event, S ude) {
        try {
            ude.getClass().getDeclaredMethod("setLimit", Integer.class).invoke(ude, event.limit());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace(); // The exception should never occur!
        }
    }

    private void setOffset(PaginationResource event, S ude) {
        try {
            ude.getClass().getDeclaredMethod("setOffset", Integer.class).invoke(ude, event.pageIndex() * event.limit());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace(); // The exception should never occur!
        }
    }

    protected abstract S createFilterExample();
    protected abstract long getTotalCount(S example);
    protected abstract List<T> getResultList(S example);
}
