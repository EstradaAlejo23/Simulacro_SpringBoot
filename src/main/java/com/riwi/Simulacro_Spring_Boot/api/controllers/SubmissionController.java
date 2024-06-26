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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.Simulacro_Spring_Boot.api.dto.request.SubmissionRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.SubmissionRSBasic;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.ISubMissionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/submission")
@AllArgsConstructor
@Tag(name = "submission")
public class SubmissionController {
    
    @Autowired
    private final ISubMissionService objISubMissionService;

    @Operation(summary = "Insertar mensajes enviados", description = "Agrega nuevos mensajes enviados")
    @PostMapping
    public ResponseEntity<SubmissionRSBasic> insert(@Validated @RequestBody SubmissionRequest request){
        return ResponseEntity.ok(this.objISubMissionService.create(request));
    }

    @Operation(summary = "Lista los mensajes enviados por paginación", description = "Muestra la lista de mensajes enviado paginados en size 10")
    @GetMapping
    public ResponseEntity<Page<SubmissionRSBasic>> getAll(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "1") int size){
        return ResponseEntity.ok(this.objISubMissionService.getAll(page -1, size));
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
    @Operation(summary = "Eliminar", description = "Elimina mensajes enviados por id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String>> delete(
        @PathVariable Long id){
            this.objISubMissionService.delete(Long.valueOf(id));
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
    @Operation(summary = "Actualizar", description = "Actualiza mensajes enviados por id")
    @PutMapping("/{id}")
    public ResponseEntity<SubmissionRSBasic> update(@PathVariable Long id, @Validated @RequestBody SubmissionRequest request){
        return ResponseEntity.ok(this.objISubMissionService.update(id, request));
    }
}
