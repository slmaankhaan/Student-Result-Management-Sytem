package com.salmankhan.studentresultmangementsytem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity class representing a Student.
 */
@Entity
@Data
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;

    /**
     * Constructor for creating a Student object with all attributes.
     *
     * @param id          The ID of the student.
     * @param firstName   The first name of the student.
     * @param lastName    The last name of the student.
     * @param dateOfBirth The date of birth of the student.
     * @param email       The email address of the student.
     */
    public Student(Long id, String firstName, String lastName, LocalDate dateOfBirth, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }
}
