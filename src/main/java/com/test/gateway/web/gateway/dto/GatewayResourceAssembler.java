package com.test.gateway.web.gateway.dto;

import com.test.gateway.core.base.BaseResourceAssembler;
import com.test.gateway.domain.gateway.Gateway;
import com.test.gateway.domain.gateway.Gateway_;
import com.test.gateway.infraestruture.gateway.GatewayFactory;
import com.test.gateway.web.gateway.GatewayController;
import org.springframework.stereotype.Component;

@Component
public class GatewayResourceAssembler extends BaseResourceAssembler<Gateway, GatewayResource, GatewayDTO, Integer, GatewayFactory, GatewayController> {

    public GatewayResourceAssembler(GatewayFactory factory) {
        super(factory, GatewayController.class, GatewayResource.class, Gateway_.UNIQUE_SERIAL_NUMBER);
    }
}
