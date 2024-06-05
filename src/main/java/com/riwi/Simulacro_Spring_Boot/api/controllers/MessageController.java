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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.Simulacro_Spring_Boot.api.dto.request.MessageRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.MessageRSBasic;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.IMessageService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/messege")
@AllArgsConstructor
@Tag(name = "messege")
public class MessageController {
    
    @Autowired
    private IMessageService objIMessageService;

    @GetMapping
    public ResponseEntity<Page<MessageRSBasic>> getAll(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "1") int size){
        return ResponseEntity.ok(this.objIMessageService.getAll(page -1, size));
    }

    @PostMapping
    public ResponseEntity<MessageRSBasic> create(@Validated @RequestBody MessageRequest request){        
        return ResponseEntity.ok(this.objIMessageService.create(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> delete(@PathVariable Long id){
        this.objIMessageService.delete(Long.valueOf(id));
        Map<String,String> response = new HashMap<>();
            response.put("message","Se lemino la lession correctamente");
            return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageRSBasic> update(@PathVariable Long id,
    @Validated @org.springframework.web.bind.annotation.RequestBody MessageRequest request){
        return ResponseEntity.ok(this.objIMessageService.update(id, request));
    }
}
