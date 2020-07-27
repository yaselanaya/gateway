package com.test.gateway.gateway;

import com.test.gateway.base.ICrudTest;
import com.test.gateway.core.exception.GatewayException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GatewayTest implements ICrudTest {

    private GatewayImpl gateway;

    GatewayTest() {
        this.gateway = new GatewayImpl();
    }

    @Test
    @Override
    public void findById() {
        gateway.findById();
    }

    @Test
    @Override
    public void findAllPaging() {
        gateway.findAllPaging();
    }

    @Test
    @Override
    public void save() throws GatewayException {
        gateway.save();
    }

    @Test
    @Override
    public void update() throws GatewayException {
        gateway.update();
    }
}
