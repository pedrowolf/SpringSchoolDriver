package com.school.driver.domain.mapper;

import com.school.driver.domain.vo.response.PostalCodeResponseVO;
import com.school.driver.infrastructure.dto.response.PostalCodeResponseDTO;

public interface PostalCodeMapper {

    PostalCodeResponseDTO fromVoToDto(PostalCodeResponseVO vo);

    PostalCodeResponseVO fromDtoToVo(PostalCodeResponseDTO dto);
}
