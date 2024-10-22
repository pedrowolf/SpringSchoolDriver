package com.school.driver.domain.vo.request;


import lombok.*;

import java.time.LocalDate;

@Data
public class RouteListFilterRequestVO extends PageFilterVO {

    private LocalDate routeDate;

    private String status;

}
