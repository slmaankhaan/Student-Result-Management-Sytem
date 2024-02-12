package com.salmankhan.studentresultmangementsytem.controller;

import com.salmankhan.studentresultmangementsytem.dto.ResultDTO;
import com.salmankhan.studentresultmangementsytem.service.ResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Unit tests for the ResultController class.
 */
@RunWith(MockitoJUnitRunner.class)
class ResultControllerTest {

    @Mock
    private ResultService resultService;

    @InjectMocks
    private ResultController resultController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test for getAllResults method.
     */
    @Test
    public void testGetAllResults() {
        List<ResultDTO> results = new ArrayList<>();
        results.add(new ResultDTO(1L, 1L, "Math", "John", "A"));
        results.add(new ResultDTO(2L, 2L, "Science", "Jane", "B"));
        when(resultService.getAllResults()).thenReturn(results);

        ResponseEntity<List<ResultDTO>> response = resultController.getAllResults();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(results, response.getBody());
        verify(resultService, times(1)).getAllResults();
    }

    /**
     * Test for addResult method.
     */
    @Test
    public void testAddResult() {
        ResultDTO resultDTO = new ResultDTO(3L, 3L, "chemistry", "David", "C");
        when(resultService.addResult(resultDTO)).thenReturn(resultDTO);

        ResponseEntity<ResultDTO> response = resultController.addResult(resultDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(resultDTO, response.getBody());
        verify(resultService, times(1)).addResult(resultDTO);
    }

    /**
     * Test for deleteResult method.
     */
    @Test
    public void testDeleteResult() {
        Long resultId = 1L;

        ResponseEntity<Void> response = resultController.deleteResult(resultId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(resultService, times(1)).deleteResult(resultId);
    }
}