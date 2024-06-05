package com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services;

import com.riwi.Simulacro_Spring_Boot.api.dto.request.EnrollmentRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.EnrollmentRSBasic;

public interface IEnrollMentService extends CrudGeneral<EnrollmentRequest,EnrollmentRSBasic,Long>{
    public EnrollmentRSBasic getById(Long id);
}
