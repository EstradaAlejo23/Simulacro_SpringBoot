package com.riwi.Simulacro_Spring_Boot.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubmissionRequest {
    private Long id;
    @NotBlank(message = "El contenido de la entrega es requerido")
    private String content;
    @NotBlank(message = "La fecha de la entrega es requerido")
    private LocalDateTime date;
    @NotNull
    @Positive
    private Double grade;
    private Long assignment;
    private Long user;
}
