package com.hkbea.odinfw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class OdinConfiguration {
    private static final String MESSAGE_SOURCE_BASENAME = "messages/messages";

    @Bean
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();

        rbms.setBasename(MESSAGE_SOURCE_BASENAME);

        return rbms;
    }

}
