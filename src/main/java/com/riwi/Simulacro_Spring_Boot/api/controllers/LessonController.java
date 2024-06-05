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

import com.riwi.Simulacro_Spring_Boot.api.dto.request.LessonRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.LessonRSBasic;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.ICourseService;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.ILessonService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/lesson")
@AllArgsConstructor
@Tag(name = "lesson")
public class LessonController {
    @Autowired
    private final ICourseService objICourseService;
    
    @Autowired
    private final ILessonService objILessonService;

    @PostMapping
    public ResponseEntity<LessonRSBasic> insert(
        @Validated @RequestBody LessonRequest request){
            return ResponseEntity.ok(this.objILessonService.create(request));
    }

    @GetMapping
    public ResponseEntity<Page<LessonRSBasic>> getall(
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size){
            return ResponseEntity.ok(this.objILessonService.getAll(page -1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<LessonRSBasic> getbyid(@PathVariable Long id){
        return ResponseEntity.ok(this.objILessonService.getById(id));
    }
    // @GetMapping(path = "/{id}")
    // public ResponseEntity<CourseRSBasic> getLessonByCourse(@PathVariable Long id){
    //     return ResponseEntity.ok(this.objICourseService.)
    // }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String>> delete(
        @PathVariable Long id){
            this.objILessonService.delete(Long.valueOf(id));
            Map<String,String> response = new HashMap<>();
            response.put("message","Se lemino la lession correctamente");
            return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<LessonRSBasic> update(
        @PathVariable Long id,
        @Validated @RequestBody LessonRequest request){
            return ResponseEntity.ok(this.objILessonService.update(id, request));
        }
}
