package com.school.driver.infrastructure.mapper.impl;

import com.school.driver.domain.mapper.PostalCodeMapper;
import com.school.driver.domain.vo.response.PostalCodeResponseVO;
import com.school.driver.infrastructure.dto.response.PostalCodeResponseDTO;
import com.school.driver.infrastructure.mapper.struct.PostalCodeMapperStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostalCodeMapperImpl implements PostalCodeMapper {

    private final PostalCodeMapperStruct mapper;

    @Override
    public PostalCodeResponseDTO fromVoToDto(PostalCodeResponseVO vo) {
        return mapper.fromVoToDto(vo);
    }

    @Override
    public PostalCodeResponseVO fromDtoToVo(PostalCodeResponseDTO dto) {
        return mapper.fromDtoToVo(dto);
    }
}
