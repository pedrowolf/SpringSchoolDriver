package com.school.driver.infrastructure.service;

import com.school.driver.domain.service.MessageBundleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageBundleServiceImpl implements MessageBundleService {

    private final MessageSource messageSource;

    @Override
    public String getMessage(String message, Object... params) {
        return messageSource.getMessage(message, params, new Locale("pt", "BR"));
    }

    @Override
    public List<String> getMessageList(List<String> messages) {
        return messages.stream().map(m -> messageSource.getMessage(m, null, new Locale("pt", "BR"))).collect(Collectors.toList());
    }
}
