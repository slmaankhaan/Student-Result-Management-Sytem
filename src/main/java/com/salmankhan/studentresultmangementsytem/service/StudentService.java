package com.salmankhan.studentresultmangementsytem.service;

import com.salmankhan.studentresultmangementsytem.dto.StudentDTO;
import com.salmankhan.studentresultmangementsytem.entity.Student;
import com.salmankhan.studentresultmangementsytem.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing students.
 */
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    /**
     * Constructor injection of StudentRepository.
     *
     * @param studentRepository The StudentRepository to be injected.
     */

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Retrieves all students and converts them to DTOs.
     *
     * @return List of StudentDTOs representing all students.
     */
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a student by ID.
     *
     * @param id The ID of the student to retrieve.
     * @return The StudentDTO representing the student with the given ID.
     * @throws EntityNotFoundException if the student with the given ID is not found.
     */
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
        return convertToDTO(student);
    }

    /**
     * Adds a new student.
     *
     * @param studentDTO The StudentDTO containing student details.
     * @return The StudentDTO of the newly created student.
     */
    public StudentDTO addStudent(StudentDTO studentDTO) {
        validateDateOfBirth(studentDTO.getDateOfBirth());

        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setEmail(studentDTO.getEmail());

        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }

    /**
     * Updates an existing student.
     *
     * @param id         The ID of the student to update.
     * @param studentDTO The StudentDTO containing updated student details.
     * @return The StudentDTO of the updated student.
     * @throws EntityNotFoundException if the student with the given ID is not found.
     */
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        validateDateOfBirth(studentDTO.getDateOfBirth());

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));

        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setEmail(studentDTO.getEmail());

        Student updatedStudent = studentRepository.save(student);
        return convertToDTO(updatedStudent);
    }

    /**
     * Deletes a student by ID.
     *
     * @param id The ID of the student to delete.
     */
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(
                student.getFirstName(),
                student.getLastName(),
                student.getDateOfBirth(),
                student.getEmail()
        );
    }

    /**
     * Validates the date of birth of a student.
     *
     * @param dateOfBirth The date of birth to be validated.
     * @throws IllegalArgumentException if the date of birth is null or the student is less than 10 years old.
     */
    protected void validateDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now().minusYears(10))) {
            throw new IllegalArgumentException("Date of birth must be provided and the student must be at least 10 years old.");
        }
    }
}
