package com.test.gateway.web.gateway.dto;

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
public class GatewayResource extends RepresentationModel<GatewayResource> {

    private Integer id;

    private String uniqueSerialNumber;

    private String name;

    private String ip;

}
