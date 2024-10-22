package com.school.driver.infrastructure.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.driver.domain.exception.BaseSchoolDriverException;
import com.school.driver.domain.mapper.PostalCodeMapper;
import com.school.driver.domain.model.constants.MessageConstants;
import com.school.driver.domain.service.MessageBundleService;
import com.school.driver.domain.service.PostalCodeService;
import com.school.driver.domain.vo.response.PostalCodeResponseVO;
import com.school.driver.infrastructure.config.ConfigProperties;
import com.school.driver.infrastructure.dto.response.PostalCodeResponseDTO;
import com.school.driver.infrastructure.model.LogHistoryEntity;
import com.school.driver.infrastructure.repository.jpa.JpaLogHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostalCodeServiceImpl implements PostalCodeService {

    private final RestTemplate restTemplate;

    private final PostalCodeMapper postalCodeMapper;
    private final ObjectMapper objectMapper;

    private final MessageBundleService messageBundleService;

    private final JpaLogHistory logRepository;

    private final ConfigProperties configProperties;

    @Override
    public PostalCodeResponseVO findPostalCode(String postalCode){
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", configProperties.getApiKey());
        try {
            ResponseEntity<String> response = restTemplate.exchange(configProperties.getPostalCodeUrl()
                            .concat(configProperties.getPostalCodePath())
                            .concat("/")
                            .concat(postalCode),
                    HttpMethod.GET, new HttpEntity<>(headers), String.class);
            PostalCodeResponseDTO dto = objectMapper.readValue(response.getBody(), PostalCodeResponseDTO.class);
            saveLog(response.getBody(), response.getStatusCode().value(), postalCode);
            return postalCodeMapper.fromDtoToVo(dto);
        } catch (HttpClientErrorException | JsonProcessingException e) {
            if(e instanceof HttpClientErrorException) {
                HttpClientErrorException ex = (HttpClientErrorException) e;
                saveLog(ex.getResponseBodyAsString(), ex.getStatusCode().value(), postalCode);
                if (ex.getStatusCode().is4xxClientError()) {
                    throw new BaseSchoolDriverException(messageBundleService.getMessage(MessageConstants.MESSAGE_POSTAL_CODE_API_NOT_FOUND_ERROR), HttpStatus.NOT_FOUND);
                }
                throw new BaseSchoolDriverException(messageBundleService.getMessage(MessageConstants.MESSAGE_POSTAL_CODE_API_FIND_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                throw new BaseSchoolDriverException(messageBundleService.getMessage(MessageConstants.MESSAGE_POSTAL_CODE_API_FIND_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private void saveLog(String responseBody, Integer responseStatusCode, String postalCode) {
        LogHistoryEntity log = new LogHistoryEntity();
        log.setUrlRequested(configProperties.getPostalCodeUrl());
        log.setPathRequested(configProperties.getPostalCodePath().replace("{postalCodeValue}", postalCode));
        log.setDateHourIntegration(LocalDateTime.now());
        log.setResponse(Objects.nonNull(responseBody) ? responseBody : null);
        log.setHttpStatusCode(Objects.nonNull(responseStatusCode) ? responseStatusCode : null);
        logRepository.save(log);
    }
}
