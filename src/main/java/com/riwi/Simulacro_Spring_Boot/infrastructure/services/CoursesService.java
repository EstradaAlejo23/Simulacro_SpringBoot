package com.riwi.Simulacro_Spring_Boot.infrastructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.Simulacro_Spring_Boot.api.dto.request.CourseRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.CourseRSBasic;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.LessonRSBasic;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.UserRSBasic;
import com.riwi.Simulacro_Spring_Boot.domain.entities.Course;
import com.riwi.Simulacro_Spring_Boot.domain.entities.User;
import com.riwi.Simulacro_Spring_Boot.domain.repository.CoursesRepository;
import com.riwi.Simulacro_Spring_Boot.domain.repository.UserRepository;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.ICourseService;
import com.riwi.Simulacro_Spring_Boot.util.exceptions.BadRequestException;
import com.riwi.Simulacro_Spring_Boot.util.messages.ErrorMessages;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CoursesService implements ICourseService{
    @Autowired
    private final UserRepository objUserRepository;
    @Autowired
    private final CoursesRepository objCoursesRepository;

    

    @Override
    public void delete(Long id) {
        this.objCoursesRepository.delete(this.find(id));
    }

    @Override
    public CourseRSBasic create(CourseRequest request) {
        Course objCourse = this.courseRequest(request);

        return this.entityToResponse(this.objCoursesRepository.save(objCourse));
    }

    @Override
    public CourseRSBasic update(Long id, CourseRequest request) {
        Course course = this.find(id);
        Course courseUpdate = this.courseRequest(request);
        courseUpdate.setId(course.getId());
        return this.entityToResponse(this.objCoursesRepository.save(courseUpdate));
    }

    @Override
    public Page<CourseRSBasic> getAll(int page, int size) {
        if (page < 0) page = 0;

        PageRequest paginable = PageRequest.of(page, size);

        return this.objCoursesRepository.findAll(paginable).map(this::entityToResponse);
    }

    private Course find(Long id){
        return this.objCoursesRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Course")));
    }
    

    public Course courseRequest(CourseRequest request){
        User user = this.objUserRepository.findById(request.getIntructorId()).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("Instructor")));
        if (user.getRole().name().equals("STUDENT")) {
            throw new BadRequestException("El id ingresado debe ser de un instructor!");
        }
        return Course.builder()
        .nameCourse(request.getName())
        .instructor_id(user)
        .description(request.getDescription())
        .build();
    }

    public CourseRSBasic entityToResponse (Course entity){
        UserRSBasic user = new UserRSBasic();
        BeanUtils.copyProperties(entity.getInstructor_id(), user);

        return CourseRSBasic.builder()
        .id(entity.getId())
        .nameCourse(entity.getNameCourse())
        .description(entity.getDescription())
        .intructorId(user)
        .build();
    }

    @Override
    public CourseRSBasic getById(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public LessonRSBasic getByCourse(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByCourse'");
    }
}
