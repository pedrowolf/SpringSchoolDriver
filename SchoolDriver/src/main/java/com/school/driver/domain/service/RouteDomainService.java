package com.school.driver.domain.service;

import com.school.driver.domain.model.Route;
import com.school.driver.domain.vo.request.RouteListFilterRequestVO;
import com.school.driver.domain.vo.response.RoutePaginatedVO;

public interface RouteDomainService {

    Route initializeRoute(Route route);

    void removeRoute(Long id);

    void finalizeRoute(Long id);

    void includeStudent(Long routeId, Long studentId);

    void removeStudent(Long routeId, Long studentId);

    Route findRoute(Long id);

    RoutePaginatedVO findRoutesByFilter(RouteListFilterRequestVO listFilterRequestVO);
}
