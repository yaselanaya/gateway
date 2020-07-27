package com.test.gateway.infraestruture.gateway;

import com.test.gateway.core.base.ServiceBaseImpl;
import com.test.gateway.domain.gateway.Gateway;
import com.test.gateway.domain.gateway.Gateway_;
import com.test.gateway.domain.gateway.IGatewayRepository;
import com.test.gateway.domain.gateway.IGatewayService;
import com.test.gateway.web.gateway.dto.GatewayDTO;
import com.test.gateway.web.gateway.dto.GatewayResource;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class GatewayServiceImpl extends ServiceBaseImpl<Gateway, Integer, GatewayDTO, GatewayResource, GatewayFactory, IGatewayRepository> implements
        IGatewayService {

    protected GatewayServiceImpl(IGatewayRepository repository, GatewayFactory factory, GatewayValidationService validator) {
        super(repository, factory, validator);
    }

    @Override
    public boolean fieldValueExists(Object value, String fieldName, Object idValue) {

        if (Gateway_.UNIQUE_SERIAL_NUMBER.equals(fieldName))
            return !BigInteger.ZERO.equals(repository.existsUniqueSerialNumber(value.toString(), (Integer) idValue));

        return false;
    }
}
