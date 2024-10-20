package com.school.driver.application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageResponseDTO {

    private Integer pageNumber;

    private Integer pageSize;

    private Integer totalItems;

    private Boolean isFirstPage;

    private Boolean isLastPage;
}
