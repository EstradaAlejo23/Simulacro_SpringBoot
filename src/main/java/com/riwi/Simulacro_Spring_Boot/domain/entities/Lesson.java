package com.riwi.Simulacro_Spring_Boot.domain.entities;

import java.util.List;

import org.apache.commons.lang3.builder.HashCodeExclude;

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

@Entity(name = "Lessons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courses_id", referencedColumnName = "id")
    private Course courses;

    @ToString.Exclude
    @HashCodeExclude
    @OneToMany(mappedBy = "assigments",cascade = CascadeType.REFRESH)
    private List<Assignment> assigments;
}
