package com.salmankhan.studentresultmangementsytem.controller;

import com.salmankhan.studentresultmangementsytem.dto.StudentDTO;
import com.salmankhan.studentresultmangementsytem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing students.
 */

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    /**
     * Constructor injection for StudentController.
     *
     * @param studentService The service responsible for student operations.
     */

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Retrieves all students.
     *
     * @return ResponseEntity containing the list of students.
     */
    @GetMapping("/allstudents")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * Retrieves a student by ID.
     *
     * @param id The ID of the student to retrieve.
     * @return ResponseEntity containing the student information.
     */

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    /**
     * Adds a new student.
     *
     * @param studentDTO The student to be added.
     * @return ResponseEntity containing the created student.
     */

    @PostMapping("/addstudent")
    public ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO createdStudent = studentService.addStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    /**
     * Updates an existing student.
     *
     * @param id         The ID of the student to be updated.
     * @param studentDTO The updated student information.
     * @return ResponseEntity containing the updated student information.
     */

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    /**
     * Deletes a student by ID.
     *
     * @param id The ID of the student to be deleted.
     * @return ResponseEntity indicating the success of the operation.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
