package com.school.driver.infrastructure.service;

import com.school.driver.domain.service.MessageBundleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageBundleServiceImpl implements MessageBundleService {

    private final MessageSource messageSource;

    @Override
    public String getMessage(String message, Object... params) {
        return messageSource.getMessage(message, params, new Locale("pt", "BR"));
    }
}
