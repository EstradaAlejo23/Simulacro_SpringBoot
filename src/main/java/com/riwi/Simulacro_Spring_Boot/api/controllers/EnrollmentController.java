package com.riwi.Simulacro_Spring_Boot.api.controllers;

import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.Simulacro_Spring_Boot.api.dto.request.EnrollmentRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.EnrollmentRSBasic;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.IEnrollMentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/enrollment")
@AllArgsConstructor
@Tag(name = "/enrollment")
public class EnrollmentController {
    @Autowired
    private final IEnrollMentService objEnrollMentService;

    @Operation(summary = "Lista las inscripciones por paginación", description = "Muestra la lista de inscripciones paginados en size 10")
    @GetMapping
    public ResponseEntity<Page<EnrollmentRSBasic>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size){
            return ResponseEntity.ok(this.objEnrollMentService.getAll(page -1, size));
    }

    @Operation(summary = "Inserta inscripciones", description = "Agrega nuevas inscripciones")
    @PostMapping
    public ResponseEntity<EnrollmentRSBasic> insert(
        @Validated @RequestBody EnrollmentRequest request){
            return ResponseEntity.ok(this.objEnrollMentService.create(request));
    }

    @ApiResponse(
            responseCode = "400",
            description = "Cuando el id no es válido",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            }
    )
    @Operation(summary = "Eliminar", description = "Elimina iscripciones por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(
        @PathVariable Long id){
            this.objEnrollMentService.delete(Long.valueOf(id));
            Map<String,String> response = new HashMap<>();
            response.put("message","Se lemino la lession correctamente");
            return ResponseEntity.ok(response);
    }

    @ApiResponse(
            responseCode = "400",
            description = "Cuando el id no es válido",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            }
    )
    @Operation(summary = "Actualizar", description = "Elimina cursos por id")
    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentRSBasic>update(@PathVariable Long id,
    @Validated @RequestBody EnrollmentRequest request){
        return ResponseEntity.ok(this.objEnrollMentService.update(id, request));
    }
}
