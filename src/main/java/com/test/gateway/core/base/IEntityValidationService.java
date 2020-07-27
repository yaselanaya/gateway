package com.test.gateway.core.base;

import com.test.gateway.core.exception.ErrorCode;
import com.test.gateway.core.exception.GatewayException;

import java.util.Map;

public interface IEntityValidationService<Entity, DTO extends IDto, Id> {

    /**
     *
     * @param entity
     * @throws GatewayException
     */
    void validateForCreate(Entity entity) throws GatewayException;

    /**
     *
     * @param entity
     * @throws GatewayException
     */
    void validateForCreate(Entity entity, DTO dto) throws GatewayException;

    /**
     *
     * @param entity
     * @throws GatewayException
     */
    void validateForUpdate(Entity entity) throws GatewayException;

    /**
     *
     * @param entity
     * @throws GatewayException
     */
    void validateForUpdate(Entity entity, DTO dto) throws GatewayException;

    /**
     *
     * @param id
     * @throws GatewayException
     */
    void validateForDelete(Id id) throws GatewayException;

    /**
     *
     * @param field
     * @param code
     * @param messageKey
     * @param value
     * @return
     */
    Map<String, Object> createError(String field, ErrorCode code, String messageKey, Object value);
}
