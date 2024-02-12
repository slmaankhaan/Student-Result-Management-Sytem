package com.salmankhan.studentresultmangementsytem.controller;

import com.salmankhan.studentresultmangementsytem.dto.ResultDTO;
import com.salmankhan.studentresultmangementsytem.service.ResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing results.
 */

@RestController
@RequestMapping("/results")
public class ResultController {
    private final ResultService resultService;

    /**
     * Constructor injection for ResultController.
     *
     * @param resultService The service responsible for result operations.
     */


    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    /**
     * Retrieves all results.
     *
     * @return ResponseEntity containing the list of results.
     */

    @GetMapping("/allresults")
    public ResponseEntity<List<ResultDTO>> getAllResults() {
        List<ResultDTO> results = resultService.getAllResults();
        return ResponseEntity.ok(results);
    }

    /**
     * Adds a new result.
     *
     * @param resultDTO The result to be added.
     * @return ResponseEntity containing the created result.
     */

    @PostMapping("/addresult")
    public ResponseEntity<ResultDTO> addResult(@Valid @RequestBody ResultDTO resultDTO) {
        ResultDTO createdResult = resultService.addResult(resultDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdResult);
    }

    /**
     * Deletes a result by its ID.
     *
     * @param id The ID of the result to be deleted.
     * @return ResponseEntity indicating the success of the operation.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }
}
