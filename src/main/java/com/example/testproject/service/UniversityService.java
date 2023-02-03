package com.example.testproject.service;

import com.example.testproject.domain.University;
import com.example.testproject.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {
    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public University save(University university) {
        return universityRepository.save(university);
    }

    public University findById(Long id) {
        return universityRepository.findById(id).orElseThrow();
    }


    public List<University> findAll() {
        return universityRepository.findAll();
    }

    public void update(University university) {
        universityRepository.save(university);
    }
}
