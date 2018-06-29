package com.hkbea.odinfw.generators;

import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyBatisGenerator {
    public static final String GENERATOR_CONFIG_XML_PATH = "/generatorConfig.xml";
    public static final MyBatisGenerator INSTANCE = new MyBatisGenerator();

    private MyBatisGenerator() {}

    public void generate() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        try (InputStream is = MyBatisGenerator.class.getResourceAsStream(GENERATOR_CONFIG_XML_PATH)) {
            List<String> warnings = new ArrayList<>();
            ConfigurationParser cp = new ConfigurationParser(warnings);
            boolean overwrite = true;
            org.mybatis.generator.api.MyBatisGenerator myBatisGenerator = new org.mybatis.generator.api.MyBatisGenerator(cp.parseConfiguration(is), new DefaultShellCallback(overwrite), warnings);
            myBatisGenerator.generate(null);
        }
    }

    public static void main(String[] args) throws InterruptedException, SQLException, InvalidConfigurationException, XMLParserException, IOException {
        MyBatisGenerator.INSTANCE.generate();
    }
}
