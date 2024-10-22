package com.school.driver.domain.vo.response;

import lombok.Data;

@Data
public class PostalCodeResponseVO {

    private String postalCode;

    private String city;

    private String streetAddress;

    private String neighborhoodAddress;

    private String state;
}
