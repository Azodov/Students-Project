package com.example.testproject.rest;

import com.example.testproject.domain.Field_of_studies;
import com.example.testproject.service.FieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FieldController {
    private final FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @PostMapping("/field")
    public ResponseEntity<Field_of_studies> saveField(@RequestBody Field_of_studies field) {
        return new ResponseEntity<>(fieldService.save(field), HttpStatus.CREATED);
    }

    @PutMapping("/field")
    public ResponseEntity<Field_of_studies> updateField(@RequestBody Field_of_studies field) {
        return new ResponseEntity<>(fieldService.update(field), HttpStatus.OK);
    }

    @GetMapping("/field/{id}")
    public ResponseEntity<Field_of_studies> getField(@PathVariable Long id) {
        return new ResponseEntity<>(fieldService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/field")
    public ResponseEntity<?> getAllFields() {
        return new ResponseEntity<>(fieldService.findAll(), HttpStatus.OK);
    }
}
