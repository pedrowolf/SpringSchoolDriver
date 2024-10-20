package com.school.driver.application.dto.response;

import com.school.driver.domain.model.enums.RouteStatusEnum;
import com.school.driver.domain.model.enums.RouteTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RouteListItemResponseDTO {

    private Long id;

    private LocalDateTime beginDate;

    private LocalDateTime endDate;

    private Long linkedRouteId;

    private RouteTypeEnum type;

    private RouteStatusEnum status;

    public String getType() {
        return type.getDescription();
    }

    public String getStatus() {
        return status.getDescription();
    }
}
