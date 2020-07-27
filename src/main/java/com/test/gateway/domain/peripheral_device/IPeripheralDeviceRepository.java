package com.test.gateway.domain.peripheral_device;

import com.test.gateway.core.base.IBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPeripheralDeviceRepository
        extends JpaRepository<PeripheralDevice, Integer>, CrudRepository<PeripheralDevice, Integer>,
        JpaSpecificationExecutor<PeripheralDevice>, IBaseRepository<PeripheralDevice, Integer> {
}
