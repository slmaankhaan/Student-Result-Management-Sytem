package com.salmankhan.studentresultmangementsytem.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for Result.
 */
@Setter
@Getter
public class ResultDTO {

    private Long courseId;
    private Long studentId;
    private String courseName;
    private String studentName;
    private String score;

    /**
     * Default constructor for ResultDTO.
     */

    public ResultDTO() {
        // Default no-argument constructor
    }

    /**
     * Constructor for ResultDTO with parameters.
     *
     * @param courseId    The ID of the course.
     * @param studentId   The ID of the student.
     * @param courseName  The name of the course.
     * @param studentName The name of the student.
     * @param score       The score of the student in the course.
     */
    public ResultDTO(Long courseId, Long studentId, String courseName, String studentName, String score) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.courseName = courseName;
        this.studentName = studentName;
        this.score = score;
    }
}