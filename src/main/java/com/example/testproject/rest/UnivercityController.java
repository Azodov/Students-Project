package com.example.testproject.rest;

import com.example.testproject.domain.University;
import com.example.testproject.service.UniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/university/{id}")
    public ResponseEntity<University> getUniversity(@PathVariable Long id) {
        return new ResponseEntity<>(universityService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/university")
    public ResponseEntity<?> getAllUniversities() {
        return new ResponseEntity<>(universityService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/university")
    public ResponseEntity<?> updateUniversity(@RequestBody University university) {
        universityService.update(university);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
