package com.school.driver.infrastructure.mapper.struct;

import com.school.driver.application.dto.request.RouteListFilterRequestDTO;
import com.school.driver.application.dto.request.RouteStartRequestDTO;
import com.school.driver.application.dto.response.RouteListItemResponseDTO;
import com.school.driver.application.dto.response.RouteResponseDTO;
import com.school.driver.application.dto.response.StudentResponseDTO;
import com.school.driver.domain.model.Route;
import com.school.driver.domain.model.Student;
import com.school.driver.domain.vo.request.RouteListFilterRequestVO;
import com.school.driver.infrastructure.model.RouteEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface RouteMapperStruct {

    RouteEntity fromDomainToEntity(Route route);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "beginDate", source = "beginDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "linkedRoute", source = "linkedRoute")
    @Mapping(target = "studentsOnRoute", source = "studentsOnRoute")
    Route fromEntityToDomain(RouteEntity entity);

//    @IterableMapping(qualifiedByName = "fromStudentRouteEntityListToStudentDomainList")
//    List<Student> fromStudentRouteEntityListToStudentDomainList(List<StudentRouteEntity> studentRouteEntities);
//
//    @Named("fromStudentRouteEntityListToStudentDomainList")
//    @Mapping(target = "id", source = "student.id")
//    @Mapping(target = "name", source = "student.name")
//    @Mapping(target = "phoneNumber", source = "student.phoneNumber")
//    @Mapping(target = "fatherName", source = "student.fatherName")
//    @Mapping(target = "motherName", source = "student.motherName")
//    @Mapping(target = "fatherPhone", source = "student.fatherPhone")
//    @Mapping(target = "motherPhone", source = "student.motherPhone")
//    @Mapping(target = "postalCode", source = "student.postalCode")
//    @Mapping(target = "address", source = "student.address")
//    @Mapping(target = "status", source = "student.status")
//    Student fromStudentRouteEntityToStudentDomain(StudentRouteEntity studentRouteEntity);

    @IterableMapping(qualifiedByName = "fromEntityListToDomain")
    List<Route> fromEntityListToDomainList(List<RouteEntity> entities);

    @Named("fromEntityListToDomain")
    @Mapping(target = "studentsOnRoute", ignore = true)
    Route fromEntityListToDomain(RouteEntity routeEntity);

    @Mapping(target = "type", source = "type")
    @Mapping(target = "linkedRoute", source = "linkedRouteId", qualifiedByName = "buildLinkedRoute")
    @Mapping(target = "beginDate", expression = "java(java.time.LocalDateTime.now())")
    Route fromRequestStartDtoToRouteDomain(RouteStartRequestDTO requestDTO);

    @Named("buildLinkedRoute")
    default Route buildLinkedRoute(Long linkedRouteId){
        return Objects.nonNull(linkedRouteId) ? new Route(linkedRouteId) : null;
    }

    @Mapping(source = "id", target = "id")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "beginDate", source = "beginDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "linkedRouteId", source = "linkedRoute.id")
    @Mapping(target = "studentsOnRoute", source = "studentsOnRoute", qualifiedByName = "fromStudentDomainListToResponseDtoList")
    RouteResponseDTO fromRouteDomainToResponseDto(Route route);

    @IterableMapping(qualifiedByName = "fromStudentDomainListToResponseDtoList")
    List<StudentResponseDTO> fromStudentDomainListToResponseDtoList(List<Student> students);

    @Named("fromStudentDomainListToResponseDtoList")
    StudentResponseDTO fromStudentDomainToResponseDto(Student student);

    @IterableMapping(qualifiedByName = "fromRouteDomainListToResponseDtoList")
    List<RouteListItemResponseDTO> fromRouteDomainListToResponseDtoList(List<Route> routes);

    @Named("fromRouteDomainListToResponseDtoList")
    @Mapping(source = "id", target = "id")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "beginDate", source = "beginDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "linkedRouteId", source = "linkedRoute.id")
    RouteListItemResponseDTO fromRouteDomainToResponseDetailDto(Route route);

    RouteListFilterRequestVO fromListRequestFilterDtoToVo(RouteListFilterRequestDTO dto);
}
