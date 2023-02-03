package com.example.testproject.service;

import com.example.testproject.domain.Field_of_studies;
import com.example.testproject.repository.Field_of_studiesRepository;
import org.springframework.stereotype.Service;

@Service
public class FieldService {
    private final Field_of_studiesRepository fieldOfStudiesRepository;

    public FieldService(Field_of_studiesRepository fieldOfStudiesRepository) {
        this.fieldOfStudiesRepository = fieldOfStudiesRepository;
    }

    public Field_of_studies save(Field_of_studies fieldOfStudies) {
        return fieldOfStudiesRepository.save(fieldOfStudies);
    }
}
