package com.example.testproject.rest;

import com.example.testproject.domain.Field_of_studies;
import com.example.testproject.service.FieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
