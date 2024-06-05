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

import com.riwi.Simulacro_Spring_Boot.api.dto.request.AssignmentRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.AssignmentRSBasic;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.IAssignmentService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/assigment")
@AllArgsConstructor
@Tag(name = "assigment")
public class AssigmentController {

    @Autowired
    private IAssignmentService objAssignmentService;

    @Operation(summary = "Lista las asignaciones por paginacion", description = "Muestra la lista de asignaciones paginadas en size 10")
    @GetMapping
    public ResponseEntity<Page<AssignmentRSBasic>> getAll(
        @RequestParam (defaultValue = "1") int page,
        @RequestParam (defaultValue = "10") int size){
            return ResponseEntity.ok(this.objAssignmentService.getAll(page - 1, size));
    }

    @Operation(summary = "Inserta asignaciones", description = "Agrega nuevas asignaciones")
    @PostMapping
    public ResponseEntity<AssignmentRSBasic> create(
        @Validated @RequestBody AssignmentRequest request){
            return ResponseEntity.ok(this.objAssignmentService.create(request));
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
    @Operation(summary = "Eliminar", description = "Elimina asignaciones por id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable Long id){
        this.objAssignmentService.delete(Long.valueOf(id));
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
    @Operation(summary = "Actualizar", description = "Actualiza asignaciones por id")
    @PutMapping("/{id}")
    public ResponseEntity<AssignmentRSBasic> update(@PathVariable Long id,
    @Validated @RequestBody AssignmentRequest request){
        return ResponseEntity.ok(this.objAssignmentService.update(id,request));
    }
}