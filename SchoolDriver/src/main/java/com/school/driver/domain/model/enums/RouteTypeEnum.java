package com.school.driver.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RouteTypeEnum {

    OUTWARD("Ida"),
    RETURN("Volta");

    private String description;
}
