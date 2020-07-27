package com.test.gateway.web.peripheral_device.dto;

import com.test.gateway.core.base.IDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PeripheralDeviceDTO implements IDto {

    private Integer uid;

    private String vendor;

    private String createdDate;

    private Boolean status;

    private Integer gatewayId;

}
