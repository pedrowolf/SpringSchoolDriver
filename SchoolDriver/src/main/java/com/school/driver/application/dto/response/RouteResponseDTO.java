package com.school.driver.application.dto.response;

import com.school.driver.domain.model.enums.RouteStatusEnum;
import com.school.driver.domain.model.enums.RouteTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class RouteResponseDTO {

    private Long id;

    private LocalDateTime beginDate;

    private LocalDateTime endDate;

    private RouteTypeEnum type;

    private Long linkedRouteId;

    private RouteStatusEnum status;

    private Set<StudentResponseDTO> studentsOnRoute;

    public String getType() {
        return type.getDescription();
    }

    public String getStatus() {
        return status.getDescription();
    }
}
