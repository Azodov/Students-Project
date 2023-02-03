package com.example.testproject.service;

import com.example.testproject.domain.Student;
import com.example.testproject.dto.StudentDto;

import java.util.List;

public interface StudentServiceImpl {
    Student save(Student student);

    List<StudentDto> findAll();

    void delete(Long id);

    void update(Student student);
}
