package com.example.testproject.dto;

import com.example.testproject.domain.Field_of_studies;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    private String dateOfBirth;
    private String description;
    private String study_start_date;
    private String study_end_date;

    private Field_of_studies field_of_studies;

    private Timestamp created_at;
}
