package com.riwi.Simulacro_Spring_Boot.api.dto.response.erros;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseErrorResp {
    private String status;
    private Integer code;
    @Builder.Default
    private LocalDateTime  date = LocalDateTime.now();
}
