package com.school.driver.domain.model;

import com.school.driver.domain.model.constants.MessageConstants;
import com.school.driver.domain.vo.response.ValidationResponseVO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


@Data
public class Address {

    private Long id;

    private String postalCode;

    private String city;

    private String streetAddress;

    private String neighborhoodAddress;

    private String state;

    public ValidationResponseVO canPersistAddress(){
        ValidationResponseVO validationResponseVO = new ValidationResponseVO();
        if(StringUtils.isBlank(postalCode) || StringUtils.isBlank(city) || StringUtils.isBlank(streetAddress) || StringUtils.isBlank(neighborhoodAddress)
                || StringUtils.isBlank(state)){
            validationResponseVO.setSuccess(Boolean.FALSE);
            validationResponseVO.getValidationErrorMessages().add(MessageConstants.MESSAGE_ADDRESS_WITHOUT_NEEDED_FIELDS_ERROR);
        }
        return validationResponseVO;
    }
}
