package com.school.driver.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StudentStatus {

    ENABLED("Ativo"),
    DISABLED("Inativo"),
    DELETED("Removido");

    private final String description;
}
