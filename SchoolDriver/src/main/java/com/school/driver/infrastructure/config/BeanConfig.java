package com.school.driver.infrastructure.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.school.driver.domain.repository.StudentRepository;
import com.school.driver.domain.service.MessageBundleService;
import com.school.driver.domain.service.RouteDomainService;
import com.school.driver.domain.service.StudentDomainService;
import com.school.driver.domain.service.impl.RouteDomainServiceImpl;
import com.school.driver.domain.service.impl.StudentDomainServiceImpl;
import com.school.driver.infrastructure.repository.RouteRepositoryImpl;
import com.school.driver.infrastructure.repository.StudentRepositoryImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class BeanConfig {

    @Bean
    public StudentDomainService studentDomainService(StudentRepositoryImpl repository,
                                                     MessageBundleService messageBundleService){
        return new StudentDomainServiceImpl(repository, messageBundleService);
    }

    @Bean
    @DependsOn("studentDomainService")
    public RouteDomainService routeDomainService(RouteRepositoryImpl repository,
                                                 StudentDomainService studentDomainService,
                                                 MessageBundleService messageBundleService){
        return new RouteDomainServiceImpl(repository, studentDomainService, messageBundleService);
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new StdSerializer<LocalDateTime>(LocalDateTime.class) {
            @Override
            public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(localDateTime));
            }
        });
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }
}
