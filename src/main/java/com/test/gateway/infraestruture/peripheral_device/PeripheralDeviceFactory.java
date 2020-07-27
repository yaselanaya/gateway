package com.test.gateway.infraestruture.peripheral_device;

import com.test.gateway.core.base.BaseFactory;
import com.test.gateway.core.base.IMessages;
import com.test.gateway.domain.peripheral_device.PeripheralDevice;
import com.test.gateway.web.peripheral_device.dto.PeripheralDeviceDTO;
import com.test.gateway.web.peripheral_device.dto.PeripheralDeviceResource;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class PeripheralDeviceFactory extends BaseFactory<PeripheralDevice, PeripheralDeviceResource, PeripheralDeviceDTO> {

    public PeripheralDeviceFactory(IMessages messages) {
        super(PeripheralDevice.class, PeripheralDeviceResource.class, messages);
    }

    @Override
    protected void dtoToEntityActions(PeripheralDevice entity, PeripheralDeviceDTO dto){
        setLocalDateIfNotNull(dto::getCreatedDate, DateTimeFormatter.ISO_DATE, entity::setCreatedDate);
    }

    @Override
    protected void entityToResourceActions(PeripheralDeviceResource resource, PeripheralDevice entity){

        resource.setGatewayId(entity.getGateway().getId());

        setFormattedDateOrEmpty(entity::getCreatedDate, DateTimeFormatter.ISO_DATE,
                resource::setCreatedDate);

    }


}
