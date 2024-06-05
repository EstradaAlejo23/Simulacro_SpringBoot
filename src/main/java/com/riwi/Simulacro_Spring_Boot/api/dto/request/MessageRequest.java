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
public class MessageRequest {
    private Long id;
    @NotBlank(message = "El contenido del mensaje es requerido")
    private String content;
    @NotBlank(message = "La fecha del mensaje es requerido")
    private LocalDateTime date;
    private Long senderId;
    private Long receiverId;
    private Long course;
}
