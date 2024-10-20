package com.school.driver.domain.vo.response;

import com.school.driver.domain.model.Route;
import com.school.driver.domain.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoutePaginatedVO {

    private List<Route> routes;

    private Integer totalItems;
}
