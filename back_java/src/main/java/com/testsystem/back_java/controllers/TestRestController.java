package com.testsystem.back_java.controllers;

import com.testsystem.back_java.dto.CategoryDto;
import com.testsystem.back_java.dto.TestDto;
import com.testsystem.back_java.models.Category;
import com.testsystem.back_java.models.Test;
import com.testsystem.back_java.services.impl.TestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
@Slf4j
@RestController
@RequestMapping(value = "/test")
public class TestRestController {
    private  final TestServiceImpl testService;

    public TestRestController(TestServiceImpl testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Secured({"Admin_Role", "Teacher_Role"})
    public ResponseEntity<TestDto> createCategory(@RequestBody TestDto testDto) throws Exception {
        Test newTest = testService.convertTestDtoToEntity(testDto);
        Test saveTest = testService.saveTest(newTest);
        TestDto saveTestDto = new TestDto(saveTest);

        return ResponseEntity.ok(saveTestDto);
    }

}
