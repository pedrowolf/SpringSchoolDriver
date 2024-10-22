package com.school.driver.domain.service;

import java.util.List;

public interface MessageBundleService {

    String getMessage(String message, Object... params);

    List<String> getMessageList(List<String> messages);
}
