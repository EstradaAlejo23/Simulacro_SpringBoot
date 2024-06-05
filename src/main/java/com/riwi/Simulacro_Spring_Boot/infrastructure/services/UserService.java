package com.riwi.Simulacro_Spring_Boot.infrastructure.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.Simulacro_Spring_Boot.api.dto.request.UserRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.UserRSBasic;
import com.riwi.Simulacro_Spring_Boot.domain.entities.User;
import com.riwi.Simulacro_Spring_Boot.domain.repository.UserRepository;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.IUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService{
    @Autowired
    private final UserRepository objUserRepository;

    @Override
    public void delete(Long id) {
        User objUser = this.find(Long.valueOf(id));

        this.objUserRepository.delete(objUser);
    }

    @Override
    public UserRSBasic create(UserRequest request) {
        User objUser = this.userRequest(request,new User());

        return this.entityToResponse(this.objUserRepository.save(objUser));
    }

    @Override
    public UserRSBasic update(Long id, UserRequest request) {
        User objUser = this.find(Long.valueOf(id));
        User objUserUpdate = this.userRequest(request, objUser);

        return this.entityToResponse(this.objUserRepository.save(objUserUpdate));
    }

    @Override
    public Page<UserRSBasic> getAll(int page, int size) {
        if (page <0) page = 0;
        PageRequest paginable = PageRequest.of(page, size);

        return this.objUserRepository.findAll(paginable).map(this::entityToResponse);
    }

    public UserRSBasic getById(Long id){
        return this.entityToResponse(this.find(Long.valueOf(id)));
    }

    private User find(Long id){
        return this.objUserRepository.findById(id).orElseThrow();
    }


    private UserRSBasic entityToResponse(User entity){
        UserRSBasic response = new UserRSBasic();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    private User userRequest(UserRequest request,User user){
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setFullName(request.getFullName());

        return user;
    }

    // private UserRSBasic entityToResponse(User entity){
    //     return UserRSBasic.builder()
    //     .id(entity.getId())
    //     .name(entity.getName())
    //     .email(entity.getEmail())
    //     .fullName(entity.getFullName())
    //     .role(entity.getRole())
    //     .build();
    // }

    // private User userRequest (UserRequest request){
    //     return User.builder()
    //     .name(request.getName())
    //     .email(request.getEmail())
    //     .password(request.getPassword())
    //     .fullName(request.getFullName())
    //     .role(request.getRole())
    //     .build();
    // }
    
}
