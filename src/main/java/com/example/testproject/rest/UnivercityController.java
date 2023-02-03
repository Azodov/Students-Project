package com.example.testproject.rest;

import com.example.testproject.domain.University;
import com.example.testproject.service.UniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UnivercityController {
    private final UniversityService universityService;

    public UnivercityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping("/university")
    public ResponseEntity<University> saveUniversity(@RequestBody University university) {
        return new ResponseEntity<>(universityService.save(university), HttpStatus.CREATED);
    }
}
