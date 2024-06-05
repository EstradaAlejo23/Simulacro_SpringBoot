package com.riwi.Simulacro_Spring_Boot.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonRequest {
    private Long id;
    @NotBlank(message = "El titulo de la lesion debe ser requerido")
    private String title;
    @NotBlank(message = "La descrion de la lesion debe ser requerido")
    private String description;
    private Long courseId;
}
