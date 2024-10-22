package com.school.driver.infrastructure.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ConfigProperties {

    @Value("${postal.code.api.key}")
    private String apiKey;

    @Value("${postal.code.url}")
    private String postalCodeUrl;

    @Value("${postal.code.path}")
    private String postalCodePath;
}
