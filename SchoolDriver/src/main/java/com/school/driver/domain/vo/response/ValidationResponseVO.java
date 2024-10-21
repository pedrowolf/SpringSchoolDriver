package com.school.driver.domain.vo.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationResponseVO {

    private Boolean success = Boolean.TRUE;

    private List<String> validationErrorMessages = new ArrayList<>();
}
