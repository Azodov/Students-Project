package com.example.testproject.service;

import com.example.testproject.domain.Student;
import com.example.testproject.dto.StudentDto;
import com.example.testproject.repository.StudentRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements StudentServiceImpl{
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> studentDtos = studentRepository.findAll();
        List<StudentDto> studentDtos1 = new ArrayList<>();
        for (Student student : studentDtos) {
            StudentDto studentDto = new StudentDto();
            studentDto.setId(student.getId());
            studentDto.setFirstName(student.getFirstName());
            studentDto.setLastName(student.getLastName());
            studentDto.setMiddleName(student.getMiddleName());
            studentDto.setGender(student.getGender());
            studentDto.setDescription(student.getDescription());
            studentDto.setField_of_studies(student.getField_of_studies());
            studentDto.setDateOfBirth(student.getDateOfBirth());
            studentDto.setStudy_start_date(student.getStudy_start_date());
            studentDto.setStudy_end_date(student.getStudy_end_date());
            studentDto.setCreated_at(student.getCreated_at());
            studentDtos1.add(studentDto);
        }
        return studentDtos1;
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void update(Student student) {
        studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    public List<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable).getContent();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
}
