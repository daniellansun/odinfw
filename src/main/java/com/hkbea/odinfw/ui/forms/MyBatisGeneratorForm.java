package com.hkbea.odinfw.ui.forms;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringView(name="MyBatisGeneratorForm")
public class MyBatisGeneratorForm extends QueryForm<Object> {
    @Override
    protected AbstractOrderedLayout createFieldInputsLayout() {
        HorizontalLayout fieldsHL = new HorizontalLayout();

        Button genBtn = new Button("Generate");
        genBtn.addClickListener(event -> {
            try (InputStream is = MyBatisGeneratorForm.class.getResourceAsStream("/generatorConfig.xml")) {
                List<String> warnings = new ArrayList<>();
                ConfigurationParser cp = new ConfigurationParser(warnings);
                Configuration config = cp.parseConfiguration(is);
                boolean overwrite = true;
                DefaultShellCallback callback = new DefaultShellCallback(overwrite);
                MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
                myBatisGenerator.generate(null);
            } catch (SQLException | IOException | InterruptedException | InvalidConfigurationException | XMLParserException e) {
                e.printStackTrace();
            }
        });
        fieldsHL.addComponent(genBtn);

        return fieldsHL;
    }

    @Override
    protected String createFieldInputsTitle() {
        return null;
    }

    @Override
    protected Grid<Object> createResultGrid() {
        return null;
    }
}
