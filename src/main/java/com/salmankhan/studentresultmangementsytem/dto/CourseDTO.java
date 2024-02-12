package com.salmankhan.studentresultmangementsytem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for Course.
 */
@Getter
@Setter
public class CourseDTO {
    @NotBlank
    private String name;

    /**
     * Default constructor for CourseDTO.
     */
    public CourseDTO() {
        // Default no-argument constructor
    }

    /**
     * Constructor for CourseDTO with name parameter.
     *
     * @param name The name of the course.
     */
    public CourseDTO(String name) {
        this.name = name;
    }

}
