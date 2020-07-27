package com.test.gateway.web.peripheral_device.dto;

import com.test.gateway.core.base.BaseResourceAssembler;
import com.test.gateway.domain.peripheral_device.PeripheralDevice;
import com.test.gateway.domain.peripheral_device.PeripheralDevice_;
import com.test.gateway.infraestruture.peripheral_device.PeripheralDeviceFactory;
import com.test.gateway.web.peripheral_device.PeripheralDeviceController;
import org.springframework.stereotype.Component;

@Component
public class PeripheralDeviceResourceAssembler extends
        BaseResourceAssembler<PeripheralDevice, PeripheralDeviceResource, PeripheralDeviceDTO, Integer, PeripheralDeviceFactory, PeripheralDeviceController> {

    public PeripheralDeviceResourceAssembler(PeripheralDeviceFactory factory) {
        super(factory, PeripheralDeviceController.class, PeripheralDeviceResource.class, PeripheralDevice_.UID);
    }
}
