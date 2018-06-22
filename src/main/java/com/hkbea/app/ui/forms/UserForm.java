package com.hkbea.app.ui.forms;

import com.hkbea.app.domain.User;
import com.hkbea.app.repositories.UserRepository;
import com.hkbea.odinfw.ui.forms.BaseForm;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.hkbea.odinfw.ui.UiUtils.i18n;

@SpringView(name="UserForm")
public class UserForm extends BaseForm {
    private Grid<User> grid = new Grid<>();

    @Autowired
    private UserRepository userRepository;

    @Override
    protected Component getContentComponent() {
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.setMargin(new MarginInfo(false, true, false, true));

        Panel fieldInputsPanel = createFieldInputsPanel();
        verticalLayout.addComponent(fieldInputsPanel);

        grid.setId("");
        grid.setWidth("100%");
        grid.setVisible(false);

        grid.addColumn(User::getId).setCaption(i18n("text.id"));
        grid.addColumn(User::getName).setCaption(i18n("text.name"));
        grid.addColumn(User::getPassword).setCaption(i18n("text.password"));

        verticalLayout.addComponent(grid);

        return verticalLayout;
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

//        PasswordField passwordTF = new PasswordField(i18n("text.password"));
//        passwordTF.setIcon(VaadinIcons.PASSWORD);
//        passwordTF.setRequiredIndicatorVisible(true);
//        fieldsHL.addComponent(passwordTF);

        Button queryBtn = new Button(i18n("text.query"));
        queryBtn.addClickListener((Button.ClickListener) event -> {
            List<User> userList = userRepository.selectAll(new User(idTF.getValue(), nameTF.getValue(), null));

            grid.setItems(userList);
            grid.setVisible(true);
        });
        fieldsHL.addComponent(queryBtn);
        fieldsHL.setComponentAlignment(queryBtn, Alignment.BOTTOM_RIGHT);

        Panel fieldInputsPanel = new Panel(fieldsHL);

        fieldsHL.setMargin(true);

        return fieldInputsPanel;
    }
}
