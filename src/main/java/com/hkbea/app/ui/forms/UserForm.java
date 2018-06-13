package com.hkbea.app.ui.forms;

import com.hkbea.app.domain.User;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

import static com.hkbea.odinfw.ui.UiUtils.i18n;

@SpringView(name="UserForm")
public class UserForm extends VerticalLayout implements View {
    private Grid<User> grid = new Grid<>();

    @PostConstruct
    private void init() {
        Panel fieldInputsPanel = createFieldInputsPanel();
        this.addComponent(fieldInputsPanel);

        grid.setWidth("100%");
        grid.setVisible(false);

        grid.addColumn(User::getId).setCaption(i18n("text.id"));
        grid.addColumn(User::getName).setCaption(i18n("text.name"));
        grid.addColumn(User::getPassword).setCaption(i18n("text.password"));

        this.addComponent(grid);
    }

    private Panel createFieldInputsPanel() {
        HorizontalLayout fieldsHL = new HorizontalLayout();

        TextField idTF = new TextField(i18n("text.id"));
        idTF.setIcon(VaadinIcons.USER);
        idTF.setRequiredIndicatorVisible(true);
        fieldsHL.addComponent(idTF);

        TextField nameTF = new TextField(i18n("text.name"));
        nameTF.setIcon(VaadinIcons.USER);
        nameTF.setRequiredIndicatorVisible(true);
        fieldsHL.addComponent(nameTF);

        PasswordField passwordTF = new PasswordField(i18n("text.password"));
        passwordTF.setIcon(VaadinIcons.PASSWORD);
        passwordTF.setRequiredIndicatorVisible(true);
        fieldsHL.addComponent(passwordTF);

        Button queryBtn = new Button(i18n("text.query"));
        queryBtn.addClickListener((Button.ClickListener) event -> {
            grid.setItems(
                    new User("1", "Daniel", "****"),
                    new User("2", "Paul", "****"),
                    new User("3", "John", "****")
            );

            grid.setVisible(true);
        });
        fieldsHL.addComponent(queryBtn);
        fieldsHL.setComponentAlignment(queryBtn, Alignment.BOTTOM_RIGHT);

        Panel fieldInputsPanel = new Panel(fieldsHL);

        fieldsHL.setMargin(true);

        return fieldInputsPanel;
    }
}
