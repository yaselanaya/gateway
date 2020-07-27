package com.test.gateway.domain.gateway;

import com.test.gateway.core.base.BaseEntitySpecification;
import com.test.gateway.core.filter.SearchCriteria;

import java.util.List;

public class GatewaySpecification extends BaseEntitySpecification<Gateway> {

    public GatewaySpecification(List<SearchCriteria> filters, String search) {
        super(filters, search);
    }
}
