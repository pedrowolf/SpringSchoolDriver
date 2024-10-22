package com.school.driver.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.school.driver.domain.model.constants.MessageConstants;
import com.school.driver.domain.model.enums.RouteTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteStartRequestDTO {

    private Long linkedRouteId;

    @NotNull(message = MessageConstants.MESSAGE_ROUTE_TYPE_NEEDED)
    private RouteTypeEnum type;
}
