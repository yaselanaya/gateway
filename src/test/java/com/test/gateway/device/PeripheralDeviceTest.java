package com.test.gateway.device;

import com.test.gateway.base.ICrudTest;
import com.test.gateway.core.exception.GatewayException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PeripheralDeviceTest implements ICrudTest {

    private PeripheralDeviceImpl peripheralDevice;

    PeripheralDeviceTest() {
        this.peripheralDevice = new PeripheralDeviceImpl();
    }

    @Test
    @Override
    public void findById() {
        peripheralDevice.findById();
    }

    @Test
    @Override
    public void findAllPaging() {
        peripheralDevice.findAllPaging();
    }

    @Test
    @Override
    public void save() throws GatewayException {
        peripheralDevice.save();
    }

    @Test
    @Override
    public void update() throws GatewayException {
        peripheralDevice.update();
    }
}
