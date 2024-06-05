package com.riwi.Simulacro_Spring_Boot.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.Simulacro_Spring_Boot.api.dto.request.SubmissionRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.AssignmentRSBasic;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.SubmissionRSBasic;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.UserRSBasic;
import com.riwi.Simulacro_Spring_Boot.domain.entities.Assignment;
import com.riwi.Simulacro_Spring_Boot.domain.entities.Submission;
import com.riwi.Simulacro_Spring_Boot.domain.entities.User;
import com.riwi.Simulacro_Spring_Boot.domain.repository.AssignmentRepository;
import com.riwi.Simulacro_Spring_Boot.domain.repository.SubmissionRepository;
import com.riwi.Simulacro_Spring_Boot.domain.repository.UserRepository;
import com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services.ISubMissionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubMissionService implements ISubMissionService{

    @Autowired
    private final SubmissionRepository objSubmissionRepository;

    @Autowired
    private final UserRepository objUserRepository;

    @Autowired
    private final AssignmentRepository objAssignmentRepository;

    @Override
    public void delete(Long id) {
        this.objSubmissionRepository.delete(this.find(id));
    }

    @Override
    public SubmissionRSBasic create(SubmissionRequest request) {
        Submission objSubmission = this.entityToRequest(request);

        return this.entityToResponse(this.objSubmissionRepository.save(objSubmission));
    }

    @Override
    public SubmissionRSBasic update(Long id, SubmissionRequest request) {
        Submission objSubmission = this.find(id);
        Submission objSubmissionUpdate = this.entityToRequest(request);
        objSubmissionUpdate.setId(objSubmission.getId());
        return this.entityToResponse(this.objSubmissionRepository.save(objSubmissionUpdate));
    }

    @Override
    public Page<SubmissionRSBasic> getAll(int page, int size) {
        if (page <0) page = 0;
        PageRequest paginable = PageRequest.of(page, size);

        return this.objSubmissionRepository.findAll(paginable).map(this::entityToResponse);
    }

    @Override
    public SubmissionRSBasic getById(Long id) {
        return this.entityToResponse(this.find(id));
    }


    public Submission find(Long id){
        return this.objSubmissionRepository.findById(id).orElseThrow();
    }

    public SubmissionRSBasic entityToResponse (Submission entity){
        UserRSBasic userRSBasic = UserRSBasic.builder()
        .id(entity.getUser().getId())
        .name(entity.getUser().getName())
        .email(entity.getUser().getEmail())
        .fullName(entity.getUser().getFullName())
        .role(entity.getUser().getRole())
        .build();

        AssignmentRSBasic assignmentRSBasic = AssignmentRSBasic.builder()
        .id(entity.getAssignment().getId())
        .title(entity.getAssignment().getTitle())
        .description(entity.getAssignment().getDescription())
        .data(entity.getAssignment().getData())
        .build();

        return SubmissionRSBasic.builder()
        .id(entity.getId())
        .content(entity.getContent())
        .date(entity.getDate())
        .grade(entity.getGrade())
        .user(userRSBasic)
        .assignment(assignmentRSBasic)
        .build();
    }


    public Submission entityToRequest (SubmissionRequest request){
        User user = this.objUserRepository.findById(request.getUser()).orElseThrow();
        Assignment assignment = this.objAssignmentRepository.findById(request.getAssignment()).orElseThrow();

        return Submission.builder()
        .id(request.getId())
        .content(request.getContent())
        .date(request.getDate())
        .grade(request.getGrade())
        .user(user)
        .assignment(assignment)
        .build();
    }
}
