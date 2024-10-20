package com.school.driver.domain.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PageFilterVO {

    private Integer pageNumber;

    private Integer pageSize;
}
