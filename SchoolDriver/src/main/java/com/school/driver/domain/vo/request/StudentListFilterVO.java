package com.school.driver.domain.vo.request;

import lombok.Data;

@Data
public class StudentListFilterVO extends PageFilterVO{

    private String name;

    private String fatherName;

    private String motherName;

    private String status;
}
