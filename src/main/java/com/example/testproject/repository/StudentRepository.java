package com.example.testproject.repository;

import com.example.testproject.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.fileStorage.id = ?1")
    public Student findByFileStorageId(Long id);
}