package com.school.driver.domain.model;

import com.school.driver.domain.model.constants.MessageConstants;
import com.school.driver.domain.model.enums.RouteStatusEnum;
import com.school.driver.domain.model.enums.RouteTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    private Long id;

    private LocalDateTime beginDate;

    private LocalDateTime endDate;

    private Route linkedRoute;

    private RouteTypeEnum type;

    private RouteStatusEnum status;

    private Set<Student> studentsOnRoute = new HashSet<>();

    public Route(Long id){
        this.id = id;
    }

    public String canInitializeRoute(){
        if(type.equals(RouteTypeEnum.RETURN) && Objects.isNull(linkedRoute)){
            return MessageConstants.MESSAGE_ROUTE_WITHOUT_LINKED_ROUTE_ERROR;
        }
        return null;
    }

    public String canRemoveRoute(){
        if(status.equals(RouteStatusEnum.DELETED)){
            return MessageConstants.MESSAGE_ROUTE_ALREADY_REMOVED_ERROR;
        }
        if(!CollectionUtils.isEmpty(studentsOnRoute)){
            return  MessageConstants.MESSAGE_ROUTE_CONTAINS_STUDENTS_ERROR;
        }
        return null;
    }

    public String canFinalizeRoute(){
        if(status.equals(RouteStatusEnum.FINALIZED)){
            return MessageConstants.MESSAGE_ROUTE_ALREADY_FINALIZED_ERROR;
        }
        if(status.equals(RouteStatusEnum.DELETED)){
            return MessageConstants.MESSAGE_ROUTE_REMOVED_ERROR;
        }
        return null;
    }

    public String canIncludeStudent(Student student){
        if(!status.equals(RouteStatusEnum.INITIALIZED)){
            return MessageConstants.MESSAGE_ROUTE_NOT_INICIALIZED_STATUS_ERROR;
        }
        if(studentsOnRoute.contains(student)){
            return MessageConstants.MESSAGE_ROUTE_STUDENT_ALREADY_ON_ROUTE_ERROR;
        }
        return null;
    }

    public String canRemoveStudent(Student student){
        if(!status.equals(RouteStatusEnum.INITIALIZED)){
            return MessageConstants.MESSAGE_ROUTE_NOT_INICIALIZED_STATUS_ERROR;
        }
        if(!studentsOnRoute.contains(student)){
            return MessageConstants.MESSAGE_ROUTE_WITHOUT_THIS_STUDENT_ERROR;
        }
        return null;
    }
}
