package com.riwi.Simulacro_Spring_Boot.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentRequest {
    private Long id;
    //@NotBlank(message = "El titulo de la asignacion es requerido")
    private String title;
    //@NotBlank(message = "La descricion de la asignacion es requerido")
    private String description;
    //@NotBlank(message = "La fecha de la asignacion es requerido")
    private LocalDateTime data;

    private Long lessonId;
    private Long submissionId;
}
