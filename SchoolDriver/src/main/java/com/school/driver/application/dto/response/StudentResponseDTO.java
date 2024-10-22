package com.school.driver.application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.school.driver.domain.model.enums.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentResponseDTO {

    private Long id;

    private String name;

    private String phoneNumber;

    private String fatherName;

    private String motherName;

    private String fatherPhone;

    private String motherPhone;

    private AddressResponseDTO address;

    private StudentStatus status;

    public String getStatus() {
        return Objects.nonNull(status) ? status.getDescription() : null;
    }
}
