package com.salmankhan.studentresultmangementsytem.repository;

import com.salmankhan.studentresultmangementsytem.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Result entities.
 */
public interface ResultRepository extends JpaRepository<Result, Long> {
}
