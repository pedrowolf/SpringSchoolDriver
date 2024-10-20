package com.school.driver.domain.repository;

import com.school.driver.domain.model.Route;
import com.school.driver.domain.vo.request.RouteListFilterRequestVO;
import com.school.driver.domain.vo.response.RoutePaginatedVO;

import java.util.Optional;

public interface RouteRepository {

    Route saveRoute(Route route);

    Optional<Route> findRouteById(Long id);

    RoutePaginatedVO findRoutesByFilter(RouteListFilterRequestVO listFilterRequestVO);
}
