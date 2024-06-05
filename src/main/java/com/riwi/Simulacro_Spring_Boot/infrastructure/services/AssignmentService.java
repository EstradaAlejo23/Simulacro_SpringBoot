package com.riwi.Simulacro_Spring_Boot.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.Simulacro_Spring_Boot.api.dto.request.AssignmentRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.AssignmentRSBasic;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.CourseRSBasic;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.LessonRSBasic;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.UserRSBasic;
import com.riwi.Simulacro_Spring_Boot.domain.entities.Assignment;
import com.riwi.Simulacro_Spring_Boot.domain.entities.Lesson;
import com.riwi.Simulacro_Spring_Boot.domain.repository.AssignmentRepository;
import com.riwi.Simulacro_Spring_Boot.domain.repository.LessonRepository;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.IAssignmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssignmentService implements IAssignmentService{
    @Autowired
    private final LessonRepository objLessonRepository;

    @Autowired
    private final AssignmentRepository objAssignmentRepository;

    @Override
    public void delete(Long id) {
        this.objAssignmentRepository.delete(this.find(id));
    }

    @Override
    public AssignmentRSBasic create(AssignmentRequest request) {
        Assignment objAssignment = this.entityToRequest(request);

        return this.entityToResponse((this.objAssignmentRepository.save(objAssignment)));
    }

    @Override
    public AssignmentRSBasic update(Long id, AssignmentRequest request) {
        Assignment objAssignment = this.find(id);
        Assignment objAssignmentUpdate = this.entityToRequest(request);
        objAssignmentUpdate.setId(objAssignment.getId());
        return this.entityToResponse(this.objAssignmentRepository.save(objAssignmentUpdate));
    }

    @Override
    public Page<AssignmentRSBasic> getAll(int page, int size) {
        if (page < 0 ) page = 0;

        PageRequest paginable = PageRequest.of(page, size);

        return this.objAssignmentRepository.findAll((paginable)).map(this::entityToResponse);
    }

    @Override
    public AssignmentRSBasic getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    public Assignment find(Long id){
        return this.objAssignmentRepository.findById(id).orElseThrow();
    }

    public Assignment entityToRequest (AssignmentRequest request){
        Lesson lesson = this.objLessonRepository.findById(request.getLessonId()).orElseThrow();

        return Assignment.builder()
        .id(request.getId())
        .title(request.getTitle())
        .description(request.getDescription())
        .data(request.getData())
        .assigments(lesson)
        .build();
    }

    public AssignmentRSBasic entityToResponse (Assignment entity){

        UserRSBasic userRSBasic = UserRSBasic.builder()
        .id(entity.getAssigments().getCourses().getInstructor_id().getId())
        .name(entity.getAssigments().getCourses().getInstructor_id().getName())
        .email(entity.getAssigments().getCourses().getInstructor_id().getEmail())
        .fullName(entity.getAssigments().getCourses().getInstructor_id().getFullName())
        .role(entity.getAssigments().getCourses().getInstructor_id().getRole())
        .build();

        CourseRSBasic courseRSBasic = CourseRSBasic.builder()
        .id(entity.getAssigments().getCourses().getId())
        .nameCourse(entity.getAssigments().getCourses().getNameCourse())
        .description(entity.getAssigments().getCourses().getDescription())
        .intructorId(userRSBasic)
        .build();


        return AssignmentRSBasic.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .description(entity.getDescription())
        .data(entity.getData())
        .lesson(LessonRSBasic.builder()
            .id(entity.getAssigments().getId())
            .title(entity.getAssigments().getTitle())
            .description(entity.getAssigments().getDescription())
            .course_id(courseRSBasic)
            .build())
        .build();

    }
    

    
}
