package com.test.gateway.infraestruture.gateway;

import com.google.common.collect.Lists;
import com.test.gateway.core.base.BaseEntityValidationService;
import com.test.gateway.core.base.BaseServiceUtil;
import com.test.gateway.core.base.IMessages;
import com.test.gateway.core.exception.ErrorCode;
import com.test.gateway.domain.gateway.Gateway;
import com.test.gateway.domain.gateway.Gateway_;
import com.test.gateway.domain.gateway.IGatewayRepository;
import com.test.gateway.web.gateway.dto.GatewayDTO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Validated
public class GatewayValidationService extends BaseEntityValidationService<Gateway, GatewayDTO, Integer> {

    private final IGatewayRepository repository;

    public GatewayValidationService(Validator validator, IMessages messages, BaseServiceUtil serviceUtil,
            IGatewayRepository repository) {
        super(validator, messages, serviceUtil);
        this.repository = repository;
    }

    @Override
    public List<Map<String, Object>> validateDeleteBusinessConstraints(Integer id) {
        List<Map<String, Object>> errors = Lists.newArrayList();

        Gateway gateway = repository.findById(id).orElse(new Gateway());

        if(Objects.isNull(gateway.getId()))
            errors.add(createError(Gateway_.ID, ErrorCode.DOES_NOT_EXIST,
                    "validation.error.peripheral.device.not.exist.gateway", Strings.EMPTY));

        if(Objects.nonNull(gateway.getDevices()) && !gateway.getDevices().isEmpty())
            errors.add(createError(Gateway_.DEVICES, ErrorCode.INTERNAL_ERROR,
                    "validation.error.gateway.has.devices", Strings.EMPTY));

        return errors;
    }

}
