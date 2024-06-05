package com.riwi.Simulacro_Spring_Boot.api.dto.response.erros;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@EqualsAndHashCode(callSuper=false)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsResp  extends BaseErrorResp{
    private List<Map<String,String>> errors;
}
