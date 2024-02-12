package com.salmankhan.studentresultmangementsytem.repository;

import com.salmankhan.studentresultmangementsytem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Course entities.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
