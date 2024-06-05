package com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services;

import com.riwi.Simulacro_Spring_Boot.api.dto.request.AssignmentRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.AssignmentRSBasic;

public interface IAssignmentService extends CrudGeneral<AssignmentRequest,AssignmentRSBasic,Long>{
    public AssignmentRSBasic getById(Long id);
}
