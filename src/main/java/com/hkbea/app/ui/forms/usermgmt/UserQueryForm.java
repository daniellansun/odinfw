package com.hkbea.app.ui.forms.usermgmt;

import com.hkbea.app.domain.User;
import com.hkbea.app.repositories.UserRepository;
import com.hkbea.odinfw.ui.forms.QueryForm;
import com.vaadin.annotations.PropertyId;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringView(name="UserQueryForm")
public class UserQueryForm extends QueryForm<User> {
    @PropertyId("id")
    private TextField idTF = new TextField(i18n("text.id"));
    @PropertyId("name")
    private TextField nameTF = new TextField(i18n("text.name"));

    private Grid<User> grid = new Grid<>();

    @Autowired
    private UserRepository userRepository;

    @Override
    protected AbstractOrderedLayout createFieldInputsLayout() {
        HorizontalLayout fieldsHL = new HorizontalLayout();
        fieldsHL.setMargin(true);

        idTF.setIcon(VaadinIcons.USER);
        idTF.setRequiredIndicatorVisible(true);
        fieldsHL.addComponent(idTF);

        nameTF.setIcon(VaadinIcons.USER);
        nameTF.setRequiredIndicatorVisible(true);
        fieldsHL.addComponent(nameTF);

        Binder<User> binder = new Binder<>(User.class);
        binder.bindInstanceFields(this);

        final User user = new User();
//        user.id = "hello";
//        user.name = "world";

        binder.setBean(user);

//        PasswordField passwordTF = new PasswordField(i18n("text.password"));
//        passwordTF.setIcon(VaadinIcons.PASSWORD);
//        passwordTF.setRequiredIndicatorVisible(true);
//        fieldsHL.addComponent(passwordTF);

        Button queryBtn = new Button(i18n("text.query"));
        queryBtn.addClickListener((Button.ClickListener) event -> {
            List<User> userList = userRepository.selectAll(user);

            grid.setItems(userList);
            grid.setVisible(true);
        });
        fieldsHL.addComponent(queryBtn);
        fieldsHL.setComponentAlignment(queryBtn, Alignment.BOTTOM_RIGHT);

        return fieldsHL;
    }

    @Override
    protected Grid<User> createResultGrid() {
        grid.addColumn(User::getId).setCaption(i18n("text.id"));
        grid.addColumn(User::getName).setCaption(i18n("text.name"));
        grid.addColumn(User::getPassword).setCaption(i18n("text.password"));

        return grid;
    }
}
