package com.school.driver.domain.vo.response;

import com.school.driver.domain.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentPaginatedVO {

    private List<Student> students;

    private Integer totalItems;
}
