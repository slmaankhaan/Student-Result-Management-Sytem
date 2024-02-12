package com.salmankhan.studentresultmangementsytem.service;

import com.salmankhan.studentresultmangementsytem.dto.ResultDTO;
import com.salmankhan.studentresultmangementsytem.entity.Course;
import com.salmankhan.studentresultmangementsytem.entity.Result;
import com.salmankhan.studentresultmangementsytem.entity.Student;
import com.salmankhan.studentresultmangementsytem.repository.CourseRepository;
import com.salmankhan.studentresultmangementsytem.repository.ResultRepository;
import com.salmankhan.studentresultmangementsytem.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing results.
 */

@Service
public class ResultService {
    private final ResultRepository resultRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    /**
     * Constructor injection of repositories.
     *
     * @param resultRepository  The ResultRepository to be injected.
     * @param courseRepository  The CourseRepository to be injected.
     * @param studentRepository The StudentRepository to be injected.
     */

    @Autowired
    public ResultService(ResultRepository resultRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.resultRepository = resultRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    /**
     * Retrieves all results and converts them to DTOs.
     *
     * @return List of ResultDTOs representing all results.
     */

    public List<ResultDTO> getAllResults() {
        List<Result> results = resultRepository.findAll();
        return results.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new result.
     *
     * @param resultDTO The ResultDTO containing result details.
     * @return The ResultDTO of the newly created result.
     */

    public ResultDTO addResult(ResultDTO resultDTO) {
        Course course = courseRepository.findById(resultDTO.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + resultDTO.getCourseId()));

        Student student = studentRepository.findById(resultDTO.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + resultDTO.getStudentId()));

        Result result = new Result();
        result.setCourse(course);
        result.setStudent(student);
        result.setScore(resultDTO.getScore());

        Result savedResult = resultRepository.save(result);
        return convertToDTO(savedResult);
    }

    /**
     * Deletes a result by ID.
     *
     * @param id The ID of the result to be deleted.
     */

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }

    /**
     * Converts a Result entity to a ResultDTO.
     *
     * @param result The Result entity to be converted.
     * @return The corresponding ResultDTO.
     */

    private ResultDTO convertToDTO(Result result) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCourseId(result.getCourse().getId());
        resultDTO.setStudentId(result.getStudent().getId());
        resultDTO.setScore(result.getScore());
        return resultDTO;
    }
}
