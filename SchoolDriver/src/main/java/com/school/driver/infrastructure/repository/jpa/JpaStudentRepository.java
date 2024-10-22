package com.school.driver.infrastructure.repository.jpa;

import com.school.driver.domain.model.Student;
import com.school.driver.infrastructure.model.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaStudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query(value =  "select * from TB_STUDENT st " +
                    "where (:name is null or lower(st.NM_STUDENT) like lower(:name)||'%') " +
                    "and (:fatherName is null or lower(st.NM_FATHER) like lower(:fatherName)||'%') " +
                    "and (:motherName is null or lower(st.NM_MOTHER) like lower(:motherName)||'%') " +
                    "and (:status is null or st.DS_STATUS = :status) ",
            countQuery = "select count(*) from TB_STUDENT st " +
                    "where (:name is null or lower(st.NM_STUDENT) like lower(:name)||'%') " +
                    "and (:fatherName is null or lower(st.NM_FATHER) like lower(:fatherName)||'%') " +
                    "and (:motherName is null or lower(st.NM_MOTHER) like lower(:motherName)||'%') " +
                    "and (:status is null or st.DS_STATUS = :status) ",
            nativeQuery = true)
    Page<StudentEntity> findAllByFilters(@Param("name") String name,
                                   @Param("fatherName") String fatherName,
                                   @Param("motherName") String motherName,
                                   @Param("status") String status,
                                   Pageable pageable);
}
