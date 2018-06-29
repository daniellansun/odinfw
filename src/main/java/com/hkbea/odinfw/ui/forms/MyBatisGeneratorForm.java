package com.hkbea.odinfw.ui.forms;

import com.hkbea.odinfw.generators.MyBatisGenerator;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;

import java.io.IOException;
import java.sql.SQLException;

@SpringView(name="MyBatisGeneratorForm")
public class MyBatisGeneratorForm extends BlankQueryForm<Object, Object> {
    @Override
    protected AbstractOrderedLayout createFieldInputsLayout() {
        HorizontalLayout fieldsHL = new HorizontalLayout();

        Button genBtn = new Button("Generate");
        genBtn.addClickListener(event -> {
            try {
                MyBatisGenerator.INSTANCE.generate();
            } catch (SQLException | IOException | InterruptedException | InvalidConfigurationException | XMLParserException e) {
                Notification.show("Failed to generate MyBatis mappers: " + e.getMessage(), Notification.Type.TRAY_NOTIFICATION);
                e.printStackTrace();
            }
        });
        fieldsHL.addComponent(genBtn);

        return fieldsHL;
    }

    protected String createFieldInputsTitle() {
        return "Generate MyBatis Mappers";
    }
}
