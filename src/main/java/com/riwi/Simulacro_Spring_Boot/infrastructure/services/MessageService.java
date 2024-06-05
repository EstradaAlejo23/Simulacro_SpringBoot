package com.riwi.Simulacro_Spring_Boot.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.Simulacro_Spring_Boot.api.dto.request.MessageRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.CourseRSBasic;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.MessageRSBasic;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.UserRSBasic;
import com.riwi.Simulacro_Spring_Boot.domain.entities.Course;
import com.riwi.Simulacro_Spring_Boot.domain.entities.Message;
import com.riwi.Simulacro_Spring_Boot.domain.entities.User;
import com.riwi.Simulacro_Spring_Boot.domain.repository.CoursesRepository;
import com.riwi.Simulacro_Spring_Boot.domain.repository.MessageRepository;
import com.riwi.Simulacro_Spring_Boot.domain.repository.UserRepository;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.IMessageService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService implements IMessageService{
    @Autowired
    private final MessageRepository objMessageRepository;

    @Autowired
    private final UserRepository objUserRepository;

    @Autowired
    private final CoursesRepository objCoursesRepository;

    @Override
    public void delete(Long id) {
        this.objMessageRepository.delete(this.find(id));
    }

    @Override
    public MessageRSBasic create(MessageRequest request) {
        Message objMessage = this.EntityToRequest(request);

        return this.entitytoResponse(this.objMessageRepository.save(objMessage));
    }

    @Override
    public MessageRSBasic update(Long id, MessageRequest request) {
        Message objMessage = this.find(id);
        Message objMessageUpdate = this.EntityToRequest(request);
        objMessageUpdate.setId(objMessage.getId());
        return this.entitytoResponse(this.objMessageRepository.save(objMessageUpdate));
    }

    @Override
    public Page<MessageRSBasic> getAll(int page, int size) {
        if (page <0) page = 0;

        PageRequest paginable = PageRequest.of(page, size);

        return this.objMessageRepository.findAll(paginable).map(this::entitytoResponse);
    }

    @Override
    public MessageRSBasic getById(Long id) {
        return this.entitytoResponse(this.find(id));
    }

    public Message find(Long id){
        return this.objMessageRepository.findById(id).orElseThrow();
    }

    public Message EntityToRequest (MessageRequest request){
        User user = this.objUserRepository.findById(request.getReceiverId()).orElseThrow();
        User user2 =this.objUserRepository.findById(request.getSenderId()).orElseThrow();
        Course courses = this.objCoursesRepository.findById(request.getCourse()).orElseThrow();

        return Message.builder()
        .id(request.getId())
        .content(request.getContent())
        .date(request.getDate())
        .senderId(user2)
        .receiverId(user)
        .courses(courses)
        .build();
    }

    public MessageRSBasic entitytoResponse (Message entity){

        UserRSBasic userReceiver = UserRSBasic.builder()
        .id(entity.getSenderId().getId())
        .name(entity.getSenderId().getName())
        .email(entity.getSenderId().getEmail())
        .fullName(entity.getSenderId().getFullName())
        .role(entity.getSenderId().getRole())
        .build();


        UserRSBasic userSender = UserRSBasic.builder()
        .id(entity.getSenderId().getId())
        .name(entity.getSenderId().getName())
        .email(entity.getSenderId().getEmail())
        .fullName(entity.getSenderId().getFullName())
        .role(entity.getSenderId().getRole())
        .build();

        CourseRSBasic courseRSBasic = CourseRSBasic.builder()
        .id(entity.getCourses().getId())
        .nameCourse(entity.getCourses().getNameCourse())
        .description(entity.getCourses().getDescription())
        .build();

        return MessageRSBasic.builder()
        .id(entity.getId())
        .content(entity.getContent())
        .date(entity.getDate())
        .userSender(userSender)
        .userReceiver(userReceiver)
        .course(courseRSBasic)
        .build();
    }
    
}
