package com.school.driver.domain.model;

import com.school.driver.domain.model.constants.MessageConstants;
import com.school.driver.domain.model.enums.StudentStatus;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Data
public class Student {

    private Long id;

    private String name;

    private String phoneNumber;

    private String fatherName;

    private String motherName;

    private String fatherPhone;

    private String motherPhone;

    private String postalCode;

    private String address;

    private StudentStatus status;

    public String canCreateStudent(){
        if(Objects.nonNull(id)){
            return MessageConstants.MESSAGE_STUDENT_CREATE_WITH_ID_ERROR;
        }
        return canPersistStudent();
    }

    public String canUpdateStudent(){
        if(Objects.isNull(id)){
            return MessageConstants.MESSAGE_STUDENT_UPDATE_WITHOUT_ID_ERROR;
        }
        if(status.equals(StudentStatus.DELETED)){
            return MessageConstants.MESSAGE_STUDENT_STATUS_UPDATE_TO_DELETED_ERROR;
        }
        return canPersistStudent();
    }

    public String canDeleteStudent(){
        if(Objects.isNull(id)){
            return MessageConstants.MESSAGE_STUDENT_UPDATE_WITHOUT_ID_ERROR;
        }
        if(status.equals(StudentStatus.DELETED)){
            return MessageConstants.MESSAGE_STUDENT_ALREADY_DELETED_ERROR;
        }
        return null;
    }

    private String canPersistStudent(){
        if(StringUtils.isBlank(name) || StringUtils.isBlank(fatherName) || StringUtils.isBlank(motherName)
                || StringUtils.isBlank(postalCode) || StringUtils.isBlank(address)){
            return MessageConstants.MESSAGE_STUDENT_CREATE_WITHOUT_NEEDED_FIELDS;
        }
        if(StringUtils.isBlank(motherPhone) && StringUtils.isBlank(fatherPhone)){
            return MessageConstants.MESSAGE_STUDENT_CREATE_WITHOUT_RESPONSIBLE_PHONE;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
