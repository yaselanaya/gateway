package com.test.gateway.web.gateway.dto;

import com.test.gateway.core.base.IDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GatewayDTO implements IDto {

    private Integer id;

    private String uniqueSerialNumber;

    private String name;

    private String ip;

}
