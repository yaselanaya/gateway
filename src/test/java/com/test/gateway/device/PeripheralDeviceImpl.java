package com.test.gateway.device;

import com.test.gateway.base.ICrudTest;
import com.test.gateway.core.exception.GatewayException;
import com.test.gateway.domain.gateway.Gateway;
import com.test.gateway.domain.gateway.IGatewayService;
import com.test.gateway.domain.peripheral_device.IPeripheralDeviceRepository;
import com.test.gateway.domain.peripheral_device.IPeripheralDeviceService;
import com.test.gateway.domain.peripheral_device.PeripheralDevice;
import com.test.gateway.infraestruture.peripheral_device.PeripheralDeviceFactory;
import com.test.gateway.infraestruture.peripheral_device.PeripheralDeviceServiceImpl;
import com.test.gateway.infraestruture.peripheral_device.PeripheralDeviceValidationService;
import com.test.gateway.web.peripheral_device.dto.PeripheralDeviceDTO;
import org.apache.commons.lang3.math.NumberUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PeripheralDeviceImpl implements ICrudTest {

    private IPeripheralDeviceRepository repository;

    private PeripheralDeviceFactory factory;

    private PeripheralDeviceValidationService validation;

    private IGatewayService gatewayService;

    private IPeripheralDeviceService service;

    PeripheralDeviceImpl() {
        this.repository = Mockito.mock(IPeripheralDeviceRepository.class);
        this.factory = Mockito.mock(PeripheralDeviceFactory.class);
        this.validation = Mockito.mock(PeripheralDeviceValidationService.class);
        this.gatewayService = Mockito.mock(IGatewayService.class);
        this.service = new PeripheralDeviceServiceImpl(repository, factory, validation, gatewayService);
    }

    @Override
    public void findById() {
        PeripheralDevice entity = buildEntity();

        when(service.findById(entity.getUid())).thenReturn(Optional.of(entity));

        Optional<PeripheralDevice> found = service.findById(entity.getUid());

        assertTrue(found.isPresent(), "The entity was not found.");
        Assertions.assertSame(found.get(), entity, "The entity returned was not the same as the entity mocked.");
    }

    @Override
    public void findAllPaging() {
        Gateway gateway = Gateway.builder().id(NumberUtils.INTEGER_ONE).uniqueSerialNumber("DT25354").name("gateway")
                .ip("192.168.10.250").build();

        PeripheralDevice first = PeripheralDevice.builder().uid(NumberUtils.INTEGER_ONE).createdDate(LocalDate.now())
                .vendor("first vendor").status(Boolean.TRUE).gateway(gateway).build();

        PeripheralDevice second = PeripheralDevice.builder().uid(2).createdDate(LocalDate.now()).vendor("second vendor")
                .status(Boolean.TRUE).gateway(gateway).build();

        when(repository.findAll()).thenReturn(Lists.newArrayList(first, second));

        Collection<PeripheralDevice> devices = service.findAll();

        assertEquals(devices.size(), 2, "The size of the list should be 2.");
    }

    @Override
    public void save() throws GatewayException {
        PeripheralDevice mockSave = buildEntity();

        PeripheralDeviceDTO dto = buildDto();

        when(factory.from(any(PeripheralDeviceDTO.class))).thenReturn(mockSave);

        when(repository.save(any(PeripheralDevice.class))).thenReturn(mockSave);

        PeripheralDevice saved = service.save(dto);

        assertNotNull(saved, "Entity saved should not be null.");
        assertEquals(saved, mockSave, "The entity returned was not the same as the entity mocked.");
    }

    @Override
    public void update() throws GatewayException {
        PeripheralDevice mockUpdate = buildEntity();

        PeripheralDeviceDTO dto = buildDto();

        when(factory.from(any(PeripheralDeviceDTO.class))).thenReturn(mockUpdate);

        when(repository.save(any(PeripheralDevice.class))).thenReturn(mockUpdate);

        PeripheralDevice updated = service.update(dto);

        assertNotNull(updated, "Entity updated should not be null.");
        assertEquals(updated, mockUpdate, "The entity returned was not the same as the entity mocked.");
    }

    private PeripheralDevice buildEntity() {

        Gateway gateway = Gateway.builder().id(NumberUtils.INTEGER_ONE).uniqueSerialNumber("DT25354").name("gateway")
                .ip("192.168.10.250").build();

        return PeripheralDevice.builder().uid(NumberUtils.INTEGER_ONE).createdDate(LocalDate.now()).vendor("vendor")
                .status(Boolean.TRUE).gateway(gateway).build();
    }

    private PeripheralDeviceDTO buildDto() {
        return PeripheralDeviceDTO.builder().createdDate("2020-07-27").vendor("vendor").status(Boolean.TRUE).build();
    }
}
