package com.school.driver.domain.mapper;

import com.school.driver.application.dto.request.RouteListFilterRequestDTO;
import com.school.driver.application.dto.request.RouteStartRequestDTO;
import com.school.driver.application.dto.response.RouteListItemResponseDTO;
import com.school.driver.application.dto.response.RouteResponseDTO;
import com.school.driver.domain.model.Route;
import com.school.driver.domain.vo.request.RouteListFilterRequestVO;
import com.school.driver.infrastructure.model.RouteEntity;

import java.util.List;

public interface RouteMapper {

    Route fromRequestStartDtoToRouteDomain(RouteStartRequestDTO requestDTO);

    Route fromRouteEntityToDomain(RouteEntity entity);

    RouteEntity fromRouteDomainToRouteEntity(Route route);

    List<Route> fromListEntityToListDomain(List<RouteEntity> routeEntities);

    RouteResponseDTO fromRouteDomainToResponseDto(Route route);

    List<RouteListItemResponseDTO> fromRouteDomainListToResponseDtoList(List<Route> routes);

    RouteListFilterRequestVO fromListRequestDtoToRequestVo(RouteListFilterRequestDTO dto);
}
