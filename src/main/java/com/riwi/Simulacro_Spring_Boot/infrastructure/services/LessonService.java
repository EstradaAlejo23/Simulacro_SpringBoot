package com.riwi.Simulacro_Spring_Boot.infrastructure.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.Simulacro_Spring_Boot.api.dto.request.LessonRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.CourseRSBasic;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.LessonRSBasic;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.UserRSBasic;
import com.riwi.Simulacro_Spring_Boot.domain.entities.Course;
import com.riwi.Simulacro_Spring_Boot.domain.entities.Lesson;
import com.riwi.Simulacro_Spring_Boot.domain.repository.CoursesRepository;
import com.riwi.Simulacro_Spring_Boot.domain.repository.LessonRepository;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.ILessonService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService{

    @Autowired
    private final LessonRepository objLessonRepository;

    @Autowired
    private final CoursesRepository objCoursesRepository;

    @Override
    public void delete(Long id) {
        this.objLessonRepository.delete(this.find(id));
    }

    @Override
    public LessonRSBasic create(LessonRequest request) {
        Lesson objLesson = this.entityToRequest(request);

        return this.entityToResponse(this.objLessonRepository.save(objLesson));
    }

    @Override
    public LessonRSBasic update(Long id, LessonRequest request) {
        Lesson objLesson = this.find(id);
        Lesson lessonUpdate = this.entityToRequest(request);
        lessonUpdate.setId(objLesson.getId());
        return this.entityToResponse(this.objLessonRepository.save(lessonUpdate));
    }

    @Override
    public Page<LessonRSBasic> getAll(int page, int size) {
        if (page < 0) page = 0;

        PageRequest pagenable = PageRequest.of(page, size);

        return this.objLessonRepository.findAll((pagenable)).map(this::entityToResponse);
        
    }

    public Lesson entityToRequest (LessonRequest request){
        Course course = this.objCoursesRepository.findById(request.getCourseId()).orElseThrow();
        
        return Lesson.builder()
        .id(request.getId())
        .title(request.getTitle())
        .description(request.getDescription())
        .courses(course)
        .build();
    }

    public LessonRSBasic entityToResponse(Lesson entity){
        return LessonRSBasic.builder()
        .id(entity.getId())
        .title(entity.getTitle())
        .description(entity.getDescription())
        .course_id(CourseRSBasic.builder()
            .id(entity.getCourses().getId())
            .nameCourse(entity.getCourses().getNameCourse())
            .description(entity.getCourses().getDescription())
            .intructorId(UserRSBasic.builder()
                .id(entity.getCourses().getInstructor_id().getId())
                .name(entity.getCourses().getInstructor_id().getName())
                .email(entity.getCourses().getInstructor_id().getEmail())
                .password(entity.getCourses().getInstructor_id().getPassword())
                .fullName(entity.getCourses().getInstructor_id().getFullName())
                .role(entity.getCourses().getInstructor_id().getRole())
                .build())
            .build())
        .build();
    }

    public Lesson find(Long id){
        return this.objLessonRepository.findById(id).orElseThrow();
    }

    @Override
    public LessonRSBasic getById(Long id) {
        return this.entityToResponse(this.find(id));
    }
}
