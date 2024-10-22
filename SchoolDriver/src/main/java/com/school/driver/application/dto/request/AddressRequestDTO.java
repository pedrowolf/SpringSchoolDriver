package com.school.driver.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.school.driver.domain.model.constants.MessageConstants;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressRequestDTO {

    private Long id;

    @NotBlank(message = MessageConstants.MESSAGE_ADDRESS_POSTAL_CODE_NEEDED)
    private String postalCode;

    @NotBlank(message = MessageConstants.MESSAGE_ADDRESS_CITY_NEEDED)
    private String city;

    @NotBlank(message = MessageConstants.MESSAGE_ADDRESS_STREET_NEEDED)
    private String streetAddress;

    @NotBlank(message = MessageConstants.MESSAGE_ADDRESS_NEIGHBORHOOD_NEEDED)
    private String neighborhoodAddress;

    @NotBlank(message = MessageConstants.MESSAGE_ADDRESS_STATE_NEEDED)
    private String state;
}
