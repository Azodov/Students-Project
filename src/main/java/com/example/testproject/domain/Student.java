package com.example.testproject.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    private String dateOfBirth;
    private String description;
    private String study_start_date;
    private String study_end_date;
    @OneToOne
    @JoinColumn(name = "file_storage_id")
    private FileStorage fileStorage;
    @ManyToOne
    @JoinColumn(name = "field_of_studies_id")
    private Field_of_studies field_of_studies;
    @Column(name = "created_at",
            updatable = false,
            insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false)
    @NotNull
    private Timestamp created_at;
}
