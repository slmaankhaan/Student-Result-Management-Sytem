package com.salmankhan.studentresultmangementsytem.controller;

import com.salmankhan.studentresultmangementsytem.dto.StudentDTO;
import com.salmankhan.studentresultmangementsytem.service.StudentService;
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
 * Unit tests for the StudentController class.
 */
@RunWith(MockitoJUnitRunner.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test for getAllStudents method.
     */
    @Test
    public void testGetAllStudents() {
        List<StudentDTO> students = new ArrayList<>();
        students.add(new StudentDTO());
        students.add(new StudentDTO());
        when(studentService.getAllStudents()).thenReturn(students);

        ResponseEntity<List<StudentDTO>> response = studentController.getAllStudents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(students, response.getBody());
        verify(studentService, times(1)).getAllStudents();
    }

    /**
     * Test for getStudentById method.
     */
    @Test
    public void testGetStudentById() {
        Long studentId = 1L;
        StudentDTO studentDTO = new StudentDTO();
        when(studentService.getStudentById(studentId)).thenReturn(studentDTO);

        ResponseEntity<StudentDTO> response = studentController.getStudentById(studentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(studentDTO, response.getBody());
        verify(studentService, times(1)).getStudentById(studentId);
    }

    /**
     * Test for addStudent method.
     */
    @Test
    public void testAddStudent() {
        StudentDTO studentDTO = new StudentDTO();
        when(studentService.addStudent(studentDTO)).thenReturn(studentDTO);

        ResponseEntity<StudentDTO> response = studentController.addStudent(studentDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(studentDTO, response.getBody());
        verify(studentService, times(1)).addStudent(studentDTO);
    }

    /**
     * Test for updateStudent method.
     */
    @Test
    public void testUpdateStudent() {
        Long studentId = 2L;
        StudentDTO studentDTO = new StudentDTO();
        when(studentService.updateStudent(studentId, studentDTO)).thenReturn(studentDTO);

        ResponseEntity<StudentDTO> response = studentController.updateStudent(studentId, studentDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(studentDTO, response.getBody());
        verify(studentService, times(1)).updateStudent(studentId, studentDTO);
    }

    /**
     * Test for deleteStudent method.
     */
    @Test
    public void testDeleteStudent() {
        Long studentId = 3L;

        ResponseEntity<Void> response = studentController.deleteStudent(studentId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(studentService, times(1)).deleteStudent(studentId);
    }
}