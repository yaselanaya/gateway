package com.test.gateway.web.peripheral_device.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class PeripheralDeviceResource extends RepresentationModel<PeripheralDeviceResource> {

    private Integer uid;

    private String vendor;

    private String createdDate;

    private Boolean status;

    private Integer gatewayId;
}
