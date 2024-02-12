package com.salmankhan.studentresultmangementsytem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for Student.
 */
@Getter
@Setter
public class StudentDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Past
    private LocalDate dateOfBirth;
    @Email
    private String email;

    /**
     * Constructor for StudentDTO with parameters.
     *
     * @param firstName   The first name of the student.
     * @param lastName    The last name of the student.
     * @param dateOfBirth The date of birth of the student.
     * @param email       The email address of the student.
     */

    public StudentDTO(String firstName, String lastName, LocalDate dateOfBirth, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    /**
     * Default constructor for StudentDTO.
     */
    public StudentDTO() {
        // Default constructor
    }
}
