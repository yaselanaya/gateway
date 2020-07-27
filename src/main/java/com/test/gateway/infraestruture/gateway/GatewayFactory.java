package com.test.gateway.infraestruture.gateway;

import com.test.gateway.core.base.BaseFactory;
import com.test.gateway.core.base.IMessages;
import com.test.gateway.domain.gateway.Gateway;
import com.test.gateway.web.gateway.dto.GatewayDTO;
import com.test.gateway.web.gateway.dto.GatewayResource;
import org.springframework.stereotype.Component;

@Component
public class GatewayFactory extends BaseFactory<Gateway, GatewayResource, GatewayDTO> {

    public GatewayFactory(IMessages messages) {
        super(Gateway.class, GatewayResource.class, messages);
    }
}
