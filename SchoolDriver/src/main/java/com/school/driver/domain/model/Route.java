package com.school.driver.domain.model;

import com.school.driver.domain.model.constants.MessageConstants;
import com.school.driver.domain.model.enums.RouteStatusEnum;
import com.school.driver.domain.model.enums.RouteTypeEnum;
import com.school.driver.domain.vo.response.ValidationResponseVO;
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

    public ValidationResponseVO canInitializeRoute(){
        ValidationResponseVO validationResponseVO = new ValidationResponseVO();
        if(type.equals(RouteTypeEnum.RETURN) && Objects.isNull(linkedRoute)){
            validationResponseVO.setSuccess(Boolean.FALSE);
            validationResponseVO.getValidationErrorMessages().add(MessageConstants.MESSAGE_ROUTE_WITHOUT_LINKED_ROUTE_ERROR);
        }
        return null;
    }

    public ValidationResponseVO canRemoveRoute(){
        ValidationResponseVO validationResponseVO = new ValidationResponseVO();
        if(status.equals(RouteStatusEnum.DELETED)){
            validationResponseVO.setSuccess(Boolean.FALSE);
            validationResponseVO.getValidationErrorMessages().add(MessageConstants.MESSAGE_ROUTE_ALREADY_REMOVED_ERROR);
        }
        if(!CollectionUtils.isEmpty(studentsOnRoute)){
            validationResponseVO.setSuccess(Boolean.FALSE);
            validationResponseVO.getValidationErrorMessages().add(MessageConstants.MESSAGE_ROUTE_CONTAINS_STUDENTS_ERROR);
        }
        return validationResponseVO;
    }

    public ValidationResponseVO canFinalizeRoute(){
        ValidationResponseVO validationResponseVO = new ValidationResponseVO();
        if(status.equals(RouteStatusEnum.FINALIZED)){
            validationResponseVO.setSuccess(Boolean.FALSE);
            validationResponseVO.getValidationErrorMessages().add(MessageConstants.MESSAGE_ROUTE_ALREADY_FINALIZED_ERROR);
        }
        if(status.equals(RouteStatusEnum.DELETED)){
            validationResponseVO.setSuccess(Boolean.FALSE);
            validationResponseVO.getValidationErrorMessages().add(MessageConstants.MESSAGE_ROUTE_REMOVED_ERROR);
        }
        return validationResponseVO;
    }

    public ValidationResponseVO canIncludeStudent(Student student){
        ValidationResponseVO validationResponseVO = new ValidationResponseVO();
        if(!status.equals(RouteStatusEnum.INITIALIZED)){
            validationResponseVO.setSuccess(Boolean.FALSE);
            validationResponseVO.getValidationErrorMessages().add(MessageConstants.MESSAGE_ROUTE_NOT_INICIALIZED_STATUS_ERROR);
        }
        if(studentsOnRoute.contains(student)){
            validationResponseVO.setSuccess(Boolean.FALSE);
            validationResponseVO.getValidationErrorMessages().add(MessageConstants.MESSAGE_ROUTE_STUDENT_ALREADY_ON_ROUTE_ERROR);
        }
        return validationResponseVO;
    }

    public ValidationResponseVO canRemoveStudent(Student student){
        ValidationResponseVO validationResponseVO = new ValidationResponseVO();
        if(!status.equals(RouteStatusEnum.INITIALIZED)){
            validationResponseVO.setSuccess(Boolean.FALSE);
            validationResponseVO.getValidationErrorMessages().add(MessageConstants.MESSAGE_ROUTE_NOT_INICIALIZED_STATUS_ERROR);
        }
        if(!studentsOnRoute.contains(student)){
            validationResponseVO.setSuccess(Boolean.FALSE);
            validationResponseVO.getValidationErrorMessages().add(MessageConstants.MESSAGE_ROUTE_WITHOUT_THIS_STUDENT_ERROR);
        }
        return validationResponseVO;
    }
}
