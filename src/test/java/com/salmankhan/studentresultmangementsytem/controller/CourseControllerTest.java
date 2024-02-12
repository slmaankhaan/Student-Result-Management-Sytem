package com.salmankhan.studentresultmangementsytem.controller;

import com.salmankhan.studentresultmangementsytem.dto.CourseDTO;
import com.salmankhan.studentresultmangementsytem.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Unit tests for the CourseController class.
 */

@RunWith(MockitoJUnitRunner.class)
class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test for getAllCourses method.
     */
    @Test
    public void testGetAllCourses() {
        List<CourseDTO> courses = new ArrayList<>();
        courses.add(new CourseDTO("Math"));
        courses.add(new CourseDTO("Science"));
        when(courseService.getAllCourses()).thenReturn(courses);

        ResponseEntity<List<CourseDTO>> response = courseController.getAllCourses();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(courses, response.getBody());
        verify(courseService, times(1)).getAllCourses();
    }

    /**
     * Test for addCourse method.
     */
    @Test
    public void testAddCourse() {
        CourseDTO courseDTO = new CourseDTO("English");
        when(courseService.addCourse(courseDTO)).thenReturn(courseDTO);

        ResponseEntity<CourseDTO> response = courseController.addCourse(courseDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(courseDTO, response.getBody());
        verify(courseService, times(1)).addCourse(courseDTO);
    }

    /**
     * Test for deleteCourse method.
     */
    @Test
    public void testDeleteCourse() {
        Long courseId = 1L;

        ResponseEntity<Void> response = courseController.deleteCourse(courseId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(courseService, times(1)).deleteCourse(courseId);
    }
}