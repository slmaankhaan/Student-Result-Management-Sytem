package com.salmankhan.studentresultmangementsytem.controller;

import com.salmankhan.studentresultmangementsytem.dto.CourseDTO;
import com.salmankhan.studentresultmangementsytem.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing courses.
 */
@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    /**
     * Constructor injection for CourseController.
     *
     * @param courseService The service responsible for course operations.
     */

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Retrieves all courses.
     *
     * @return ResponseEntity containing the list of courses.
     */

    @GetMapping("/allcourses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    /**
     * Adds a new course.
     *
     * @param courseDTO The course to be added.
     * @return ResponseEntity containing the created course.
     */

    @PostMapping("/add")
    public ResponseEntity<CourseDTO> addCourse(@Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO createdCourse = courseService.addCourse(courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    /**
     * Deletes a course by its ID.
     *
     * @param id The ID of the course to be deleted.
     * @return ResponseEntity indicating the success of the operation.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
