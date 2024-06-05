package com.riwi.Simulacro_Spring_Boot.domain.entities;

import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "Courses")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCourse;
    private String description;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private User instructor_id;

    @ToString.Exclude
    @OneToMany(mappedBy = "courses_id", fetch = FetchType.LAZY,cascade =  CascadeType.REFRESH)
    private List<Enrollment> enrollments;

    @ToString.Exclude
    @OneToMany(mappedBy = "courses",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Lesson> lessons;

    @ToString.Exclude
    @OneToMany(mappedBy = "courses",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Message> messages;
}
