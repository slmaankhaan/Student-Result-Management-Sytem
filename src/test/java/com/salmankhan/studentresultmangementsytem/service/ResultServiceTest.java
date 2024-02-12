package com.salmankhan.studentresultmangementsytem.service;

import com.salmankhan.studentresultmangementsytem.dto.ResultDTO;
import com.salmankhan.studentresultmangementsytem.entity.Course;
import com.salmankhan.studentresultmangementsytem.entity.Result;
import com.salmankhan.studentresultmangementsytem.entity.Student;
import com.salmankhan.studentresultmangementsytem.repository.CourseRepository;
import com.salmankhan.studentresultmangementsytem.repository.ResultRepository;
import com.salmankhan.studentresultmangementsytem.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Unit tests for the ResultService class.
 */
@RunWith(MockitoJUnitRunner.class)
class ResultServiceTest {
    @Mock
    private CourseRepository courseRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ResultRepository resultRepository;

    @InjectMocks
    private ResultService resultService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test for addResult method with valid course and student IDs.
     */

    @Test
    public void testAddResult() {
        Long courseId = 1L;
        Long studentId = 1L;
        String score = "A";

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCourseId(courseId);
        resultDTO.setStudentId(studentId);
        resultDTO.setScore(score);

        Course course = new Course();
        course.setId(courseId);

        Student student = new Student();
        student.setId(studentId);

        Result result = new Result();
        result.setCourse(course);
        result.setStudent(student);
        result.setScore(score);

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(resultRepository.save(any(Result.class))).thenReturn(result);

        ResultDTO addedResultDTO = resultService.addResult(resultDTO);

        assertEquals(resultDTO.getCourseId(), addedResultDTO.getCourseId());
        assertEquals(resultDTO.getStudentId(), addedResultDTO.getStudentId());
        assertEquals(resultDTO.getScore(), addedResultDTO.getScore());
        verify(courseRepository, times(1)).findById(courseId);
        verify(studentRepository, times(1)).findById(studentId);
        verify(resultRepository, times(1)).save(any(Result.class));
    }

    /**
     * Test for addResult method with invalid course ID.
     */
    @Test
    public void testAddResultWithInvalidCourseId() {
        Long courseId = 2L;
        Long studentId = 2L;
        String score = "B";

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCourseId(courseId);
        resultDTO.setStudentId(studentId);
        resultDTO.setScore(score);

        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> resultService.addResult(resultDTO));
        verify(courseRepository, times(1)).findById(courseId);
        verify(studentRepository, never()).findById(anyLong());
        verify(resultRepository, never()).save(any(Result.class));
    }

    /**
     * Test for addResult method with invalid student ID.
     */
    @Test
    public void testAddResultWithInvalidStudentId() {
        Long courseId = 3L;
        Long studentId = 3L;
        String score = "C";

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCourseId(courseId);
        resultDTO.setStudentId(studentId);
        resultDTO.setScore(score);

        Course course = new Course();
        course.setId(courseId);

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> resultService.addResult(resultDTO));
        verify(courseRepository, times(1)).findById(courseId);
        verify(studentRepository, times(1)).findById(studentId);
        verify(resultRepository, never()).save(any(Result.class));
    }
}