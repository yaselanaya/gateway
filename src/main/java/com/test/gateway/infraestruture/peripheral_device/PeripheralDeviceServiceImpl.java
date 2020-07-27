package com.test.gateway.infraestruture.peripheral_device;

import com.test.gateway.core.base.ServiceBaseImpl;
import com.test.gateway.domain.gateway.IGatewayService;
import com.test.gateway.domain.peripheral_device.IPeripheralDeviceRepository;
import com.test.gateway.domain.peripheral_device.IPeripheralDeviceService;
import com.test.gateway.domain.peripheral_device.PeripheralDevice;
import com.test.gateway.web.peripheral_device.dto.PeripheralDeviceDTO;
import com.test.gateway.web.peripheral_device.dto.PeripheralDeviceResource;
import org.springframework.stereotype.Service;

@Service
public class PeripheralDeviceServiceImpl extends
        ServiceBaseImpl<PeripheralDevice, Integer, PeripheralDeviceDTO, PeripheralDeviceResource, PeripheralDeviceFactory, IPeripheralDeviceRepository>
        implements IPeripheralDeviceService {

    private final IGatewayService gatewayService;

    public PeripheralDeviceServiceImpl(IPeripheralDeviceRepository repository, PeripheralDeviceFactory factory,
            PeripheralDeviceValidationService validator, IGatewayService gatewayService) {
        super(repository, factory, validator);
        this.gatewayService = gatewayService;
    }

    @Override
    public void actionsBeforeValidateForCreate(PeripheralDevice device, PeripheralDeviceDTO dto) {
        // Set the gateway
        setGatewayToDevice(device, dto.getGatewayId());
    }

    @Override
    public void actionsBeforeValidateForUpdate(PeripheralDevice device, PeripheralDeviceDTO dto) {
        // Set the gateway
        setGatewayToDevice(device, dto.getGatewayId());
    }

    private void setGatewayToDevice(PeripheralDevice device, Integer gatewayId) {
        // Set the gateway
        gatewayService.findById(gatewayId).ifPresent(device::setGateway);
    }
}
