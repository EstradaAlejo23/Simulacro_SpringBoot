package com.riwi.Simulacro_Spring_Boot.infrastructure.abastract_services;

import org.springframework.data.domain.Page;

public interface CrudGeneral<RQ, RS, ID> {
    public void delete(ID id);

    public RS create(RQ request);

    public RS update(ID id, RQ request);

    public Page<RS> getAll(int page, int size);

}
