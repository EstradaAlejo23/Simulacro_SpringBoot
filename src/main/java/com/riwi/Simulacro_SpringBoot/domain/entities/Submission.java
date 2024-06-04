package com.riwi.Simulacro_SpringBoot.domain.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "submision")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    @Column(nullable = false)
    private LocalDateTime submissionDate;
    @Column(nullable = false)
    private BigDecimal grade;

    private User userId;
    private Assignment assignmentId;
}
