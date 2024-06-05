package com.riwi.Simulacro_Spring_Boot.util.messages;

import lombok.Data;

@Data
public class ErrorMessages {
    public static String idNotFound(String entity) {
        // return "No hay registros en la entidad "+entity+ " con el id suministrado";
        final String message = "No hay registros en la entidad %s con el id suministrado";
        return String.format(message, entity);
    }
}
