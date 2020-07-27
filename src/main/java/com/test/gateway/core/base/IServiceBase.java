package com.test.gateway.core.base;

import com.test.gateway.core.exception.ErrorCode;
import com.test.gateway.core.exception.GatewayException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface IServiceBase<Entity, Id, Dto extends IDto> {

    Optional<Entity> findById(Id id);

    Collection<Entity> findAll();

    Page<Entity> findAll(Pageable pageable);

    Page<Entity> findAll(Specification<Entity> specification, Pageable pageable);

    Entity save(Dto entity) throws GatewayException;

    Entity update(Dto entity) throws GatewayException;

    void delete(Id id) throws GatewayException;

    default Map<String, Object> createError(String field, ErrorCode code, String message, Object value) {
        return Maps.newHashMap();
    }

}
