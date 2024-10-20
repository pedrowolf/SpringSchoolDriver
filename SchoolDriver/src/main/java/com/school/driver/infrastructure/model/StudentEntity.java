package com.school.driver.infrastructure.model;

import com.school.driver.domain.model.enums.StudentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_STUDENT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_STUDENT")
    private Long id;

    @Column(name = "NM_STUDENT", nullable = false)
    private String name;

    @Column(name = "NR_STUDENT_PHONE")
    private String phoneNumber;

    @Column(name = "NM_FATHER", nullable = false)
    private String fatherName;

    @Column(name = "NM_MOTHER", nullable = false)
    private String motherName;

    @Column(name = "NR_FATHER_PHONE")
    private String fatherPhone;

    @Column(name = "NR_MOTHER_PHONE")
    private String motherPhone;

    @Column(name = "NR_POSTAL_CODE", nullable = false)
    private String postalCode;

    @Column(name = "DS_ADDRESS", nullable = false)
    private String address;

    @Column(name = "DS_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private StudentStatus status;
}
