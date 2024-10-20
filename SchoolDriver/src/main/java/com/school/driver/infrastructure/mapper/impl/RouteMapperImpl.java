package com.school.driver.infrastructure.mapper.impl;

import com.school.driver.application.dto.request.RouteListFilterRequestDTO;
import com.school.driver.application.dto.request.RouteStartRequestDTO;
import com.school.driver.application.dto.response.RouteListItemResponseDTO;
import com.school.driver.application.dto.response.RouteResponseDTO;
import com.school.driver.domain.mapper.RouteMapper;
import com.school.driver.domain.model.Route;
import com.school.driver.domain.vo.request.RouteListFilterRequestVO;
import com.school.driver.infrastructure.mapper.struct.RouteMapperStruct;
import com.school.driver.infrastructure.model.RouteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RouteMapperImpl implements RouteMapper {

    private final RouteMapperStruct routeMapperStruct;

    @Override
    public Route fromRequestStartDtoToRouteDomain(RouteStartRequestDTO requestDTO) {
        return routeMapperStruct.fromRequestStartDtoToRouteDomain(requestDTO);
    }

    @Override
    public Route fromRouteEntityToDomain(RouteEntity entity) {
        return routeMapperStruct.fromEntityToDomain(entity);
    }

    @Override
    public RouteEntity fromRouteDomainToRouteEntity(Route route) {
        return routeMapperStruct.fromDomainToEntity(route);
    }

    @Override
    public List<Route> fromListEntityToListDomain(List<RouteEntity> routeEntities) {
        return routeMapperStruct.fromEntityListToDomainList(routeEntities);
    }

    @Override
    public RouteResponseDTO fromRouteDomainToResponseDto(Route route) {
        return routeMapperStruct.fromRouteDomainToResponseDto(route);
    }

    @Override
    public List<RouteListItemResponseDTO> fromRouteDomainListToResponseDtoList(List<Route> routes) {
        return routeMapperStruct.fromRouteDomainListToResponseDtoList(routes);
    }

    @Override
    public RouteListFilterRequestVO fromListRequestDtoToRequestVo(RouteListFilterRequestDTO dto) {
        return routeMapperStruct.fromListRequestFilterDtoToVo(dto);
    }
}
