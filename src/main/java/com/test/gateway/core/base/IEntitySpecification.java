package com.test.gateway.core.base;

import org.springframework.data.jpa.domain.Specification;

public interface IEntitySpecification<Entity> {

    Specification<Entity> toSpecification();

}
