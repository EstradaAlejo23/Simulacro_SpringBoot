package com.riwi.Simulacro_Spring_Boot.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @GetMapping
    public ResponseEntity<Page<AssignmentRSBasic>> getAll(
        @RequestParam (defaultValue = "1") int page,
        @RequestParam (defaultValue = "10") int size){
            return ResponseEntity.ok(this.objAssignmentService.getAll(page - 1, size));
    }

    @PostMapping
    public ResponseEntity<AssignmentRSBasic> create(
        @Validated @RequestBody AssignmentRequest request){
            return ResponseEntity.ok(this.objAssignmentService.create(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable Long id){
        this.objAssignmentService.delete(Long.valueOf(id));
        Map<String,String> response = new HashMap<>();
            response.put("message","Se lemino la lession correctamente");
            return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssignmentRSBasic> update(@PathVariable Long id,
    @Validated @RequestBody AssignmentRequest request){
        return ResponseEntity.ok(this.objAssignmentService.update(id,request));
    }
}