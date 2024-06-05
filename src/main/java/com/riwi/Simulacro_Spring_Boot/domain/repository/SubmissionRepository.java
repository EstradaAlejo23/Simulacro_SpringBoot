package com.riwi.Simulacro_Spring_Boot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riwi.Simulacro_Spring_Boot.domain.entities.Submission;

public interface SubmissionRepository extends JpaRepository<Submission,Long>{
    
}
