package com.school.driver.infrastructure.model;

import com.school.driver.domain.model.enums.RouteStatusEnum;
import com.school.driver.domain.model.enums.RouteTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TB_ROUTE")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROUTE")
    private Long id;

    @Column(name = "DH_BEGIN", nullable = false)
    private LocalDateTime beginDate;

    @Column(name = "DH_END")
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "ID_ROUTE_LINKED", referencedColumnName = "ID_ROUTE")
    private RouteEntity linkedRoute;

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_ROUTE", nullable = false)
    private RouteTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(name = "DS_STATUS", nullable = false)
    private RouteStatusEnum status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_STUDENT_ROUTE",
        joinColumns = @JoinColumn(name = "ID_ROUTE"),
        inverseJoinColumns = @JoinColumn(name = "ID_STUDENT"))
    private Set<StudentEntity> studentsOnRoute;

}
