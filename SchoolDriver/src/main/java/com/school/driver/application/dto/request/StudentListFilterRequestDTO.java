package com.school.driver.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentListFilterRequestDTO extends PageFilterDTO{

    private String name;

    private String fatherName;

    private String motherName;

    private String status;

}
