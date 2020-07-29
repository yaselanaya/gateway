package com.test.gateway.domain.peripheral_device;

import com.test.gateway.core.base.BaseEntitySpecification;
import com.test.gateway.core.filter.SearchCriteria;
import com.test.gateway.core.filter.SearchOperation;
import com.test.gateway.domain.gateway.Gateway;
import com.test.gateway.domain.gateway.Gateway_;
import com.test.gateway.infraestruture.peripheral_device.PeripheralDeviceConstants;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class PeripheralDeviceSpecification extends BaseEntitySpecification<PeripheralDevice> {

    public PeripheralDeviceSpecification(List<SearchCriteria> filters, String search) {
        super(filters, search);
    }

    @Override
    protected Specification<PeripheralDevice> buildSpecification(SearchCriteria criteria) {
        String field = criteria.getField();
        Object value = criteria.getValue();

        if(PeripheralDeviceConstants.FILTER_GATEWAY_ID.equals(field))
            return byGateway(Integer.valueOf(value.toString()));

        return super.buildSpecification(criteria);
    }

    @Override
    protected void populateSearchCriteria(){

        if (search.isEmpty())
            return;

        searchFilters.add(
                SearchCriteria.builder().field(PeripheralDevice_.VENDOR).value(search).operator(SearchOperation.CONTAINS).build());
    }

    private static Specification<PeripheralDevice> byGateway(Integer gatewayId){
        return (root, query, builder) -> builder.equal(root.get(PeripheralDevice_.gateway).get(Gateway_.ID), gatewayId);
    }
}
