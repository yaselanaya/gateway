package com.test.gateway.infraestruture.peripheral_device;

import com.google.common.collect.Lists;
import com.test.gateway.core.base.BaseEntityValidationService;
import com.test.gateway.core.base.BaseServiceUtil;
import com.test.gateway.core.base.IMessages;
import com.test.gateway.core.exception.ErrorCode;
import com.test.gateway.domain.peripheral_device.PeripheralDevice;
import com.test.gateway.domain.peripheral_device.PeripheralDevice_;
import com.test.gateway.web.peripheral_device.dto.PeripheralDeviceDTO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
@Validated
public class PeripheralDeviceValidationService extends BaseEntityValidationService<PeripheralDevice, PeripheralDeviceDTO, Integer> {

    public PeripheralDeviceValidationService(Validator validator, IMessages messages, BaseServiceUtil serviceUtil) {
        super(validator, messages, serviceUtil);
    }

    @Override
    protected List<Map<String, Object>> validateCommonBusinessConstraints(PeripheralDevice device) {
        List<Map<String, Object>> errors = Lists.newArrayList();

        // Check if the the gateway is null
        if (Objects.isNull(device.getGateway()))
            errors.add(createError(PeripheralDevice_.GATEWAY, ErrorCode.DOES_NOT_EXIST,
                    "validation.error.peripheral.device.not.exist.gateway", Strings.EMPTY));

        // Check if the gateway has more than 10 devices
        Set<PeripheralDevice> devices = Objects.nonNull(device.getGateway()) ? device.getGateway().getDevices() : null;
        if (Objects.nonNull(devices) && devices.size() >= 10)
            errors.add(createError(PeripheralDevice_.GATEWAY, ErrorCode.DOES_NOT_EXIST,
                    "validation.error.peripheral.device.not.exist.max", Strings.EMPTY));


            return errors;
    }

}
