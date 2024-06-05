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

import com.riwi.Simulacro_Spring_Boot.api.dto.request.CourseRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.CourseRSBasic;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.ICourseService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
@Tag(name = "course")
public class CourseController {
    @Autowired
    private ICourseService objCourseService;

    @GetMapping
    public ResponseEntity <Page<CourseRSBasic>> getAll(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size){
            return ResponseEntity.ok(this.objCourseService.getAll(page -1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseRSBasic> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.objCourseService.getById(id));
    }


    @PostMapping
    public ResponseEntity<CourseRSBasic> insert(
        @Validated
        @RequestBody CourseRequest request){
            return ResponseEntity.ok(this.objCourseService.create(request));
        }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable String id){
        this.objCourseService.delete(Long.valueOf(id));
        Map<String,String> response = new HashMap<>();
        response.put("messages", "Se elimino el curso correctamente");
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CourseRSBasic> update(@Validated @RequestBody CourseRequest request,
    @PathVariable Long id){
        return ResponseEntity.ok(this.objCourseService.update(id, request));
    }
}
