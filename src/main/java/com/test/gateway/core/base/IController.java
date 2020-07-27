package com.test.gateway.core.base;

import com.test.gateway.core.exception.GatewayException;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IController<Resource, Dto extends IDto, Id> {

    ResponseEntity<Resource> findById(Id id);

    ResponseEntity<PagedModel<Resource>> findAllPaging(HttpServletRequest request) throws GatewayException;

    ResponseEntity<Resource> save(Dto dto) throws GatewayException;

    ResponseEntity<Resource> update(Dto dto) throws GatewayException;

    ResponseEntity<HttpStatus> delete(Id id) throws GatewayException;
}
