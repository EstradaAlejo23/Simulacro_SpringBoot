package com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services;

import com.riwi.Simulacro_Spring_Boot.api.dto.request.LessonRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.LessonRSBasic;

public interface ILessonService extends CrudGeneral<LessonRequest,LessonRSBasic,Long>{
    public LessonRSBasic getById(Long id);
}
