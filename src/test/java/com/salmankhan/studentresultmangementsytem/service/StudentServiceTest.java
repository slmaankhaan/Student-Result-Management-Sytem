package com.salmankhan.studentresultmangementsytem.service;

import com.salmankhan.studentresultmangementsytem.dto.StudentDTO;
import com.salmankhan.studentresultmangementsytem.entity.Student;
import com.salmankhan.studentresultmangementsytem.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Unit tests for the StudentService class.
 */
@RunWith(MockitoJUnitRunner.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test for getAllStudents method.
     */
    @Test
    public void testGetAllStudents() {
        List<Student> students = createSampleStudents();
        when(studentRepository.findAll()).thenReturn(students);

        List<StudentDTO> studentDTOs = studentService.getAllStudents();

        assertEquals(students.size(), studentDTOs.size());
        verify(studentRepository, times(1)).findAll();
    }

    /**
     * Test for getStudentById method.
     */
    @Test
    public void testGetStudentById() {
        Long studentId = 1L;
        Student student = createSampleStudent();
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        StudentDTO studentDTO = studentService.getStudentById(studentId);

        assertStudentDTOEqualsStudent(studentDTO, student);
        verify(studentRepository, times(1)).findById(studentId);
    }

    /**
     * Test for addStudent method.
     */
    @Test
    public void testAddStudent() {
        StudentDTO studentDTO = createSampleStudentDTO();
        Student student = createSampleStudent();
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDTO addedStudentDTO = studentService.addStudent(studentDTO);

        assertStudentDTOEqualsStudent(addedStudentDTO, student);
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    /**
     * Test for updateStudent method.
     */

    @Test
    public void testUpdateStudent() {
        Long studentId = 1L;
        StudentDTO studentDTO = createSampleStudentDTO();
        Student student = createSampleStudent();
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDTO updatedStudentDTO = studentService.updateStudent(studentId, studentDTO);

        assertStudentDTOEqualsStudent(updatedStudentDTO, student);
        verify(studentRepository, times(1)).findById(studentId);
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    /**
     * Test for deleteStudent method.
     */
    @Test
    public void testDeleteStudent() {
        Long studentId = 1L;

        studentService.deleteStudent(studentId);

        verify(studentRepository, times(1)).deleteById(studentId);
    }

    /**
     * Test for getStudentById method when entity is not found.
     */

    @Test
    public void testGetStudentById_ThrowsEntityNotFoundException() {
        Long studentId = 1L;
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> studentService.getStudentById(studentId));
        verify(studentRepository, times(1)).findById(studentId);
    }
    /**
     * Test for validateDateOfBirth method with valid date of birth.
     */
    @Test
    public void testValidateDateOfBirth_ValidDate() {
        LocalDate dateOfBirth = LocalDate.now().minusYears(10);
        studentService.validateDateOfBirth(dateOfBirth);
    }

    /**
     * Test for validateDateOfBirth method with null date of birth.
     */
    @Test
    public void testValidateDateOfBirth_NullDateOfBirth() {
        assertThrows(IllegalArgumentException.class, () -> studentService.validateDateOfBirth(null));
    }

    /**
     * Test for validateDateOfBirth method with invalid date of birth (less than 10 years old).
     */
    @Test
    public void testValidateDateOfBirth_InvalidDateOfBirth() {
        LocalDate dateOfBirth = LocalDate.now().minusYears(5);
        assertThrows(IllegalArgumentException.class, () -> studentService.validateDateOfBirth(dateOfBirth));
    }


    /**
     * Helper method to create a list of sample students.
     */
    private List<Student> createSampleStudents() {
        List<Student> students = new ArrayList<>();
        students.add(createSampleStudent());
        return students;
    }

    /**
     * Helper method to create a sample student.
     */
    private Student createSampleStudent() {
        return new Student(1L, "Amir", "Khan", LocalDate.of(1990, 1, 1), "amir.khan@gmail.com");
    }

    /**
     * Helper method to create a sample student DTO.
     */
    private StudentDTO createSampleStudentDTO() {
        return new StudentDTO("Ali", "Hassan", LocalDate.of(1990, 1, 1), "ali.hasan@example.com");
    }

    /**
     * Helper method to assert that a student DTO is equal to a student entity.
     */
    private void assertStudentDTOEqualsStudent(StudentDTO studentDTO, Student student) {
        assertEquals(student.getFirstName(), studentDTO.getFirstName());
        assertEquals(student.getLastName(), studentDTO.getLastName());
        assertEquals(student.getDateOfBirth(), studentDTO.getDateOfBirth());
        assertEquals(student.getEmail(), studentDTO.getEmail());
    }
}