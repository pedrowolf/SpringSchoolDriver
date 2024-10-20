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
public class StudentCreateRequestDTO {

    @NotBlank(message = MessageConstants.MESSAGE_STUDENT_NAME_NEEDED)
    private String name;

    private String phoneNumber;

    @NotBlank(message = MessageConstants.MESSAGE_FATHER_NAME_NEEDED)
    private String fatherName;

    @NotBlank(message = MessageConstants.MESSAGE_MOTHER_NAME_NEEDED)
    private String motherName;

    private String fatherPhone;

    private String motherPhone;

    @NotBlank(message = MessageConstants.MESSAGE_STUDENT_POSTAL_CODE_NEEDED)
    private String postalCode;

    @NotBlank(message = MessageConstants.MESSAGE_STUDENT_ADDRESS_NEEDED)
    private String address;
}
