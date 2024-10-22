package com.school.driver.infrastructure.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostalCodeResponseDTO {

    @JsonProperty("cep")
    private String postalCode;

    @JsonProperty("localidade")
    private String city;

    @JsonProperty("logradouro")
    private String streetAddress;

    @JsonProperty("bairro")
    private String neighborhoodAddress;

    @JsonProperty("uf")
    private String state;
}
