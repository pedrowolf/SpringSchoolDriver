package com.school.driver.domain.service.impl;

import com.school.driver.domain.exception.BaseSchoolDriverException;
import com.school.driver.domain.model.Route;
import com.school.driver.domain.model.Student;
import com.school.driver.domain.model.constants.MessageConstants;
import com.school.driver.domain.model.enums.RouteStatusEnum;
import com.school.driver.domain.repository.RouteRepository;
import com.school.driver.domain.service.MessageBundleService;
import com.school.driver.domain.service.RouteDomainService;
import com.school.driver.domain.service.StudentDomainService;
import com.school.driver.domain.vo.request.RouteListFilterRequestVO;
import com.school.driver.domain.vo.response.RoutePaginatedVO;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class RouteDomainServiceImpl implements RouteDomainService {

    private final RouteRepository routeRepository;
    private final StudentDomainService studentDomainService;
    private final MessageBundleService messageBundleService;

    public RouteDomainServiceImpl(RouteRepository routeRepository, StudentDomainService studentDomainService, MessageBundleService messageBundleService) {
        this.routeRepository = routeRepository;
        this.studentDomainService = studentDomainService;
        this.messageBundleService = messageBundleService;
    }

    @Override
    public Route initializeRoute(Route route) {
        String validationError = route.canInitializeRoute();
        if(Objects.nonNull(validationError)){
            throw new BaseSchoolDriverException(messageBundleService.getMessage(validationError), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        route.setBeginDate(LocalDateTime.now());
        route.setStatus(RouteStatusEnum.INITIALIZED);
        return routeRepository.saveRoute(route);
    }

    @Override
    public void removeRoute(Long id) {
        Route route = findRoute(id);
        String validationError = route.canRemoveRoute();
        if(Objects.nonNull(validationError)){
            throw new BaseSchoolDriverException(messageBundleService.getMessage(validationError), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        route.setStatus(RouteStatusEnum.DELETED);
        routeRepository.saveRoute(route);
    }

    @Override
    public void finalizeRoute(Long id) {
        Route route = findRoute(id);
        String validationError = route.canFinalizeRoute();
        if(Objects.nonNull(validationError)){
            throw new BaseSchoolDriverException(messageBundleService.getMessage(validationError), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        route.setStatus(RouteStatusEnum.FINALIZED);
        route.setEndDate(LocalDateTime.now());
        routeRepository.saveRoute(route);
    }

    @Override
    public void includeStudent(Long routeId, Long studentId) {
        Route route = findRoute(routeId);
        Student student = studentDomainService.findById(studentId);
        String validationError = route.canIncludeStudent(student);
        if(Objects.nonNull(validationError)){
            throw new BaseSchoolDriverException(messageBundleService.getMessage(validationError), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        route.getStudentsOnRoute().add(student);
        routeRepository.saveRoute(route);
    }

    @Override
    public void removeStudent(Long routeId, Long studentId) {
        Route route = findRoute(routeId);
        Student student = studentDomainService.findById(studentId);
        String validationError = route.canRemoveStudent(student);
        if(Objects.nonNull(validationError)){
            throw new BaseSchoolDriverException(messageBundleService.getMessage(validationError), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        route.getStudentsOnRoute().remove(student);
        routeRepository.saveRoute(route);
    }

    @Override
    public Route findRoute(Long id) {
        return routeRepository.findRouteById(id).orElseThrow(() -> new BaseSchoolDriverException(messageBundleService.getMessage(MessageConstants.MESSAGE_ROUTE_NOT_FOUND_ERROR), HttpStatus.NOT_FOUND));
    }

    @Override
    public RoutePaginatedVO findRoutesByFilter(RouteListFilterRequestVO listFilterRequestVO) {
        return routeRepository.findRoutesByFilter(listFilterRequestVO);
    }
}
