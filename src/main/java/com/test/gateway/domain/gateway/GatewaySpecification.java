package com.test.gateway.domain.gateway;

import com.test.gateway.core.base.BaseEntitySpecification;
import com.test.gateway.core.filter.SearchCriteria;
import com.test.gateway.core.filter.SearchOperation;

import java.util.List;

public class GatewaySpecification extends BaseEntitySpecification<Gateway> {

    public GatewaySpecification(List<SearchCriteria> filters, String search) {
        super(filters, search);
    }

    @Override
    protected void populateSearchCriteria(){

        if (search.isEmpty())
            return;

        searchFilters.add(
                SearchCriteria.builder().field(Gateway_.NAME).value(search).operator(SearchOperation.CONTAINS).build());

        searchFilters.add(
                SearchCriteria.builder().field(Gateway_.UNIQUE_SERIAL_NUMBER).value(search).operator(SearchOperation.CONTAINS).build());
    }
}
