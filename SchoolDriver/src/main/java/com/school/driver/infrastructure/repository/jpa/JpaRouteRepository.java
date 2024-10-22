package com.school.driver.infrastructure.repository.jpa;

import com.school.driver.infrastructure.model.RouteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRouteRepository extends JpaRepository<RouteEntity, Long> {

    @Query(value =  "select * from TB_ROUTE rt " +
                    "where (:date is null or to_char(rt.DH_BEGIN, 'YYYY-MM-DD') = :date) " +
                    "and (:status is null or rt.DS_STATUS = :status)",
            countQuery = "select count(*) from TB_STUDENT st " +
                    "where (:date is null or to_char(rt.DH_BEGIN, 'YYYY-MM-DD') = :date) " +
                    "and (:status is null or rt.DS_STATUS = :status)",
            nativeQuery = true)
    Page<RouteEntity> findAllByFilters(@Param("date") String date,
                                   @Param("status") String status,
                                   Pageable pageable);
}
