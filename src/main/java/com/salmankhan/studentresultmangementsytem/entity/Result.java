package com.salmankhan.studentresultmangementsytem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class representing a Result.
 */
@Getter
@Setter
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Student student;

    private String score;
}