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

import com.riwi.Simulacro_Spring_Boot.api.dto.request.UserRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.UserRSBasic;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.IUserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "user")
public class UserController {
    @Autowired
    private final IUserService objIuserService;

    @GetMapping
    public ResponseEntity<Page<UserRSBasic>> get (@RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(objIuserService.getAll(page -1,size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserRSBasic> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.objIuserService.getById(Long.valueOf(id)));
    }

    @PostMapping
    public ResponseEntity<UserRSBasic> insert (
        @Validated
        @RequestBody UserRequest request){
            return ResponseEntity.ok(this.objIuserService.create(request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable String id){
        this.objIuserService.delete(Long.valueOf(id));
        Map<String,String> response = new HashMap<>();
        response.put("messages", "Se elimino el usuario correctamente");
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserRSBasic> update(@PathVariable Long id,
        @Validated @RequestBody UserRequest request){
            return ResponseEntity.ok(this.objIuserService.update(id, request));
        }
}
