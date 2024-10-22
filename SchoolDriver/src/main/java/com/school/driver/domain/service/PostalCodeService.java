package com.school.driver.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.school.driver.domain.vo.response.PostalCodeResponseVO;

public interface PostalCodeService {

    PostalCodeResponseVO findPostalCode(String postalCode) throws JsonProcessingException;
}
