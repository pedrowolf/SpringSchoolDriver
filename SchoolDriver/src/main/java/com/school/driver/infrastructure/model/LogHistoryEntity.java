package com.school.driver.infrastructure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_LOG_API_INTEGRATION")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogHistoryEntity {

    @Id
    @Column(name = "ID_LOG_API_INTEGRATION")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DS_URL_REQUESTED", nullable = false)
    private String urlRequested;

    @Column(name = "DS_PATH_REQUESTED", nullable = false)
    private String pathRequested;

    @Column(name = "DS_RESPONSE")
    private String response;

    @Column(name = "CD_HTTP_STATUS")
    private Integer httpStatusCode;

    @Column(name = "DH_INTEGRATION", nullable = false)
    private LocalDateTime dateHourIntegration;
}
