package com.riwi.Simulacro_SpringBoot.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "course")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String courseName;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "instructor_id",referencedColumnName = "id")
    private User instructor;

    @ManyToOne
    private User user;
}
