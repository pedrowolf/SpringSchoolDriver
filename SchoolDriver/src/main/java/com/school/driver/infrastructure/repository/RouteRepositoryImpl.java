package com.school.driver.infrastructure.repository;

import com.school.driver.domain.mapper.RouteMapper;
import com.school.driver.domain.model.Route;
import com.school.driver.domain.repository.RouteRepository;
import com.school.driver.domain.vo.request.RouteListFilterRequestVO;
import com.school.driver.domain.vo.response.RoutePaginatedVO;
import com.school.driver.infrastructure.model.RouteEntity;
import com.school.driver.infrastructure.repository.jpa.JpaRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RouteRepositoryImpl implements RouteRepository {

    private static final String DATE_FORMATTER = "yyyy-MM-dd";

    private final JpaRouteRepository repository;
    private final RouteMapper mapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 10000)
    @Override
    public Route saveRoute(Route route) {
        RouteEntity entity = mapper.fromRouteDomainToRouteEntity(route);
        return mapper.fromRouteEntityToDomain(repository.save(entity));
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 10000, readOnly = true)
    @Override
    public Optional<Route> findRouteById(Long id) {
        return repository.findById(id).map(mapper::fromRouteEntityToDomain);
    }

    @Override
    public RoutePaginatedVO findRoutesByFilter(RouteListFilterRequestVO listFilterRequestVO) {
        Pageable pageable = PageRequest.of(listFilterRequestVO.getPageNumber() - 1, listFilterRequestVO.getPageSize());
        Page<RouteEntity> page = repository.findAllByFilters(
                Objects.nonNull(listFilterRequestVO.getRouteDate()) ? listFilterRequestVO.getRouteDate().format(DateTimeFormatter.ofPattern(DATE_FORMATTER)) : null,
                listFilterRequestVO.getStatus(),
                pageable);
        return new RoutePaginatedVO(mapper.fromListEntityToListDomain(page.getContent()), (int)page.getTotalElements());
    }
}
