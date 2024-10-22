package com.school.driver.infrastructure.repository.jpa;

import com.school.driver.infrastructure.model.LogHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaLogHistory extends JpaRepository<LogHistoryEntity, Long> {
}
