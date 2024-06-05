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
public class EnrollmentRequest {
    private Long id;
    //@NotBlank(message = "La fecha de matricula debe ser requerida")
    private LocalDateTime date;
    private Long userId;
    private Long courseId;
}
