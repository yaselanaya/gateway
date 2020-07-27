package com.test.gateway.domain.gateway;

import com.test.gateway.core.base.IBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface IGatewayRepository extends JpaRepository<Gateway, Integer>, CrudRepository<Gateway, Integer>, JpaSpecificationExecutor<Gateway>, IBaseRepository<Gateway, Integer> {

    @Query(value = "SELECT COUNT(g.id) FROM Gateway g WHERE g.uniqueSerialNumber = :uniqueSerialNumber AND "
            + "(:gatewayId IS NULL OR g.id <> :gatewayId)")
    BigInteger existsUniqueSerialNumber(@Param("uniqueSerialNumber") String uniqueSerialNumber, @Param("gatewayId") Integer gatewayId);
}
