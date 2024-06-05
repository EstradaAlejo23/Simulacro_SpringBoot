package com.riwi.Simulacro_Spring_Boot.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.Simulacro_Spring_Boot.api.dto.request.EnrollmentRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.CourseRSBasic;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.EnrollmentRSBasic;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.UserRSBasic;
import com.riwi.Simulacro_Spring_Boot.domain.entities.Course;
import com.riwi.Simulacro_Spring_Boot.domain.entities.Enrollment;
import com.riwi.Simulacro_Spring_Boot.domain.entities.User;
import com.riwi.Simulacro_Spring_Boot.domain.repository.CoursesRepository;
import com.riwi.Simulacro_Spring_Boot.domain.repository.EnrollmentRepository;
import com.riwi.Simulacro_Spring_Boot.domain.repository.UserRepository;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.IEnrollMentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnrollmentService implements IEnrollMentService{
    @Autowired
    private final EnrollmentRepository objEnrollmentRepository ;

    @Autowired
    private final UserRepository objUserRepository;

    @Autowired
    private final CoursesRepository objCoursesRepository;

    @Override
    public void delete(Long id) {
        this.objEnrollmentRepository.delete(this.find(id));
    }

    @Override
    public EnrollmentRSBasic create(EnrollmentRequest request) {
        Enrollment objEnrollment = this.entityToRequest(request);

        return this.entityToResponse(this.objEnrollmentRepository.save(objEnrollment));
    }

    @Override
    public EnrollmentRSBasic update(Long id, EnrollmentRequest request) {
        Enrollment objEnrollment = this.find(id);
        Enrollment objEnrollmentUpdate = this.entityToRequest(request);
        objEnrollmentUpdate.setId(objEnrollment.getId());
        return this.entityToResponse(this.objEnrollmentRepository.save(objEnrollmentUpdate));
    }

    @Override
    public Page<EnrollmentRSBasic> getAll(int page, int size) {
        if (page <0) page = 0;

        PageRequest paginable = PageRequest.of(page, size);

        return this.objEnrollmentRepository.findAll(paginable).map(this::entityToResponse);
    }

    @Override
    public EnrollmentRSBasic getById(Long id) {
        return this.entityToResponse(this.find(id));
    }
    
    public Enrollment find(Long id){
        return this.objEnrollmentRepository.findById(id).orElseThrow();
    }
    
    public Enrollment entityToRequest (EnrollmentRequest request){
        User user = this.objUserRepository.findById(request.getUserId()).orElseThrow();
        Course course = this.objCoursesRepository.findById(request.getCourseId()).orElseThrow();

        return Enrollment.builder()
        .id(request.getId())
        .date(request.getDate())
        .courseEnrollsments(user)
        .courses_id(course)
        .build();
    }

    public EnrollmentRSBasic entityToResponse (Enrollment entity){

        UserRSBasic userRSBasic = UserRSBasic.builder()
        .id(entity.getCourseEnrollsments().getId())
        .name(entity.getCourseEnrollsments().getName())
        .email(entity.getCourseEnrollsments().getEmail())
        .fullName(entity.getCourseEnrollsments().getFullName())
        .role(entity.getCourseEnrollsments().getRole())
        .build();

        CourseRSBasic courseRSBasic = CourseRSBasic.builder()
        .id(entity.getCourses_id().getId())
        .nameCourse(entity.getCourses_id().getNameCourse())
        .description(entity.getCourses_id().getDescription())
        .build();


        return EnrollmentRSBasic.builder()
        .id(entity.getId())
        .date(entity.getDate())
        .course(courseRSBasic)
        .user(userRSBasic)
        .build();
    }
}
