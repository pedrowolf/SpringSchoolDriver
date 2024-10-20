package com.school.driver.infrastructure.util;

import com.school.driver.application.dto.request.PageFilterDTO;
import com.school.driver.application.dto.response.PageResponseDTO;

import java.math.BigDecimal;

public class PageableResponseUtil {

    public static PageResponseDTO builPageResponseDTO(PageFilterDTO filterDTO, Integer totalItems){
        int totalPages = totalItems / filterDTO.getPageSize();
        totalPages = totalItems % filterDTO.getPageSize() == 0 ? totalPages : totalPages + 1;

        return new PageResponseDTO(filterDTO.getPageNumber(), filterDTO.getPageSize(), totalItems, filterDTO.getPageNumber() == 1, totalPages == filterDTO.getPageNumber());
    }
}
