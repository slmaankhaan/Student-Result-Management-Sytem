package com.salmankhan.studentresultmangementsytem.service;

import com.salmankhan.studentresultmangementsytem.dto.CourseDTO;
import com.salmankhan.studentresultmangementsytem.entity.Course;
import com.salmankhan.studentresultmangementsytem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing courses.
 */
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    /**
     * Constructor injection of CourseRepository.
     *
     * @param courseRepository The CourseRepository to be injected.
     */

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Retrieves all courses and converts them to DTOs.
     *
     * @return List of CourseDTOs representing all courses.
     */

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    /**
     * Adds a new course.
     *
     * @param courseDTO The CourseDTO containing course details.
     * @return The CourseDTO of the newly created course.
     */
    public CourseDTO addCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());

        Course savedCourse = courseRepository.save(course);
        return convertToDTO(savedCourse);
    }

    /**
     * Deletes a course by ID.
     *
     * @param id The ID of the course to be deleted.
     */
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    /**
     * Converts a Course entity to a CourseDTO.
     *
     * @param course The Course entity to be converted.
     * @return The corresponding CourseDTO.
     */

    private CourseDTO convertToDTO(Course course) {
        return new CourseDTO(course.getName());
    }
}
