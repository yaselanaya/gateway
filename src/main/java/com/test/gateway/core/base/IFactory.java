package com.test.gateway.core.base;

import com.test.gateway.core.exception.GatewayException;

import javax.validation.constraints.NotNull;

public interface IFactory<Entity, Resource, Dto extends IDto> {

    Entity from(@NotNull Dto dto) throws GatewayException;

    Resource from(@NotNull Entity entity);

}
