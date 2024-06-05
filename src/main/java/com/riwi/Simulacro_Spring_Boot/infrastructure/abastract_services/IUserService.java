package com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services;

import com.riwi.Simulacro_Spring_Boot.api.dto.request.UserRequest;
import com.riwi.Simulacro_Spring_Boot.api.dto.response.basicResponse.UserRSBasic;

public interface IUserService extends CrudGeneral<UserRequest,UserRSBasic,Long>{
    public UserRSBasic getById(Long id);
}
