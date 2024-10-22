package com.school.driver.application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.school.driver.domain.model.constants.MessageConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressResponseDTO {

    private Long id;

    private String postalCode;

    private String city;

    private String streetAddress;

    private String neighborhoodAddress;

    private String state;
}
