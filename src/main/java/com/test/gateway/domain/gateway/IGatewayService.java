package com.test.gateway.domain.gateway;

import com.test.gateway.core.base.IServiceBase;
import com.test.gateway.core.validation.FieldValueExists;
import com.test.gateway.web.gateway.dto.GatewayDTO;

public interface IGatewayService extends IServiceBase<Gateway, Integer, GatewayDTO>, FieldValueExists {
}
