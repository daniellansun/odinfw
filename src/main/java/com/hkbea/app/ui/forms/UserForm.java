package com.hkbea.app.ui.forms;

import com.hkbea.app.domain.User;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import static com.hkbea.odinfw.ui.UiUtils.i18n;

@SpringView(name="UserForm")
public class UserForm extends AbstractForm<User> implements View {
    private TextField id;
    private TextField name;
    private PasswordField password;
    private TextField email;

    public UserForm() {
        super(User.class);

        id = new TextField(i18n("text.id"));
        id.setIcon(VaadinIcons.USER);
        id.setRequiredIndicatorVisible(true);

        name = new TextField(i18n("text.name"));
        name.setIcon(VaadinIcons.USER);
        name.setRequiredIndicatorVisible(true);

        password = new PasswordField(i18n("text.password"));
        password.setIcon(VaadinIcons.PASSWORD);
        password.setRequiredIndicatorVisible(true);

        email = new TextField(i18n("text.email"));
        email.setIcon(VaadinIcons.ENVELOPE);
    }

    @Override
    protected void bind() {
        // DateField in Vaadin 8 uses LocalDate by default, the backend
        // uses plain old java.util.Date, thus we need a converter, using
        // built in helper here
//        getBinder()
//                .forMemberField(birthDay)
//                .withConverter(new LocalDateToDateConverter());
        super.bind();
    }

    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new MHorizontalLayout(
                        id,
                        name,
                        password,
                        email
                ).withWidth(""),
                getToolbar()
        ).withWidth("");
    }
}
