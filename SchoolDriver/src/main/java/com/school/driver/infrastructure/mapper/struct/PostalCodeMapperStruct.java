package com.school.driver.infrastructure.mapper.struct;

import com.school.driver.domain.vo.response.PostalCodeResponseVO;
import com.school.driver.infrastructure.dto.response.PostalCodeResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostalCodeMapperStruct {

    PostalCodeResponseDTO fromVoToDto(PostalCodeResponseVO vo);

    PostalCodeResponseVO fromDtoToVo(PostalCodeResponseDTO dto);
}
