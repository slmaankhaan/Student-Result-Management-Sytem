package com.salmankhan.studentresultmangementsytem.service;

import com.salmankhan.studentresultmangementsytem.dto.CourseDTO;
import com.salmankhan.studentresultmangementsytem.entity.Course;
import com.salmankhan.studentresultmangementsytem.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Unit tests for the CourseService class.
 */
@RunWith(MockitoJUnitRunner.class)
class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test for getAllCourses method.
     */
    @Test
    public void testGetAllCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Math"));
        courses.add(new Course("Science"));
        when(courseRepository.findAll()).thenReturn(courses);


        List<CourseDTO> courseDTOs = courseService.getAllCourses();

        assertEquals(courses.size(), courseDTOs.size());
        assertEquals(courses.stream().map(Course::getName).collect(Collectors.toList()),
                courseDTOs.stream().map(CourseDTO::getName).collect(Collectors.toList()));
        verify(courseRepository, times(1)).findAll();
    }

    /**
     * Test for addCourse method.
     */
    @Test
    public void testAddCourse() {
        CourseDTO courseDTO = new CourseDTO("Math");
        Course course = new Course("Math");
        course.setName(courseDTO.getName());
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        CourseDTO addedCourseDTO = courseService.addCourse(courseDTO);

        assertEquals(courseDTO.getName(), addedCourseDTO.getName());
        verify(courseRepository, times(1)).save(any(Course.class));
    }

    /**
     * Test for deleteCourse method.
     */
    @Test
    public void testDeleteCourse() {
        Long courseId = 1L;
        courseService.deleteCourse(courseId);
        verify(courseRepository, times(1)).deleteById(courseId);
    }
}