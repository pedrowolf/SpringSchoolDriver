package com.school.driver.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RouteStatusEnum {

    INITIALIZED("Iniciada"),
    FINALIZED("Finalizada"),
    DELETED("Removida");

    private String description;
}
