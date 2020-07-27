package com.test.gateway.base;

import com.test.gateway.core.exception.GatewayException;

public interface ICrudTest {

    void findById();

    void findAllPaging();

    void save() throws GatewayException;

    void update() throws GatewayException;

}
