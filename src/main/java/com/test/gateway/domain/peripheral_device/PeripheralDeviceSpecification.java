package com.test.gateway.domain.peripheral_device;

import com.test.gateway.core.base.BaseEntitySpecification;
import com.test.gateway.core.filter.SearchCriteria;

import java.util.List;

public class PeripheralDeviceSpecification extends BaseEntitySpecification<PeripheralDevice> {

    public PeripheralDeviceSpecification(List<SearchCriteria> filters, String search) {
        super(filters, search);
    }
}
