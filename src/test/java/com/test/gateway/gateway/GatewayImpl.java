package com.test.gateway.gateway;

import com.test.gateway.base.ICrudTest;
import com.test.gateway.core.exception.GatewayException;
import com.test.gateway.domain.gateway.Gateway;
import com.test.gateway.domain.gateway.IGatewayRepository;
import com.test.gateway.domain.gateway.IGatewayService;
import com.test.gateway.infraestruture.gateway.GatewayFactory;
import com.test.gateway.infraestruture.gateway.GatewayServiceImpl;
import com.test.gateway.infraestruture.gateway.GatewayValidationService;
import com.test.gateway.web.gateway.dto.GatewayDTO;
import org.apache.commons.lang3.math.NumberUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GatewayImpl implements ICrudTest {

    private IGatewayRepository repository;

    private GatewayFactory factory;

    private GatewayValidationService validation;

    private IGatewayService service;

    GatewayImpl() {
        this.repository = Mockito.mock(IGatewayRepository.class);
        this.factory = Mockito.mock(GatewayFactory.class);
        this.validation = Mockito.mock(GatewayValidationService.class);
        this.service = new GatewayServiceImpl(repository, factory, validation);
    }

    @Override
    public void findById() {
        Gateway entity = buildEntity();

        when(service.findById(entity.getId())).thenReturn(Optional.of(entity));

        Optional<Gateway> found = service.findById(entity.getId());

        assertTrue(found.isPresent(), "The entity was not found.");
        Assertions.assertSame(found.get(), entity, "The entity returned was not the same as the entity mocked.");
    }

    @Override
    public void findAllPaging() {

        Gateway first = Gateway.builder().id(NumberUtils.INTEGER_ONE).uniqueSerialNumber("DT25354").name("first gateway")
                .ip("192.168.10.250").devices(Collections.emptySet()).build();

        Gateway second = Gateway.builder().id(2).uniqueSerialNumber("DT25399").name("second gateway").ip("192.168.45.50")
                .devices(Collections.emptySet()).build();

        when(repository.findAll()).thenReturn(Lists.newArrayList(first, second));

        Collection<Gateway> gateways = service.findAll();

        assertEquals(gateways.size(), 2, "The size of the list should be 2.");
    }

    @Override
    public void save() throws GatewayException {

        Gateway mockSave = buildEntity();

        GatewayDTO dto = buildDto();

        when(factory.from(any(GatewayDTO.class))).thenReturn(mockSave);

        when(repository.save(any(Gateway.class))).thenReturn(mockSave);

        Gateway saved = service.save(dto);

        assertNotNull(saved, "Entity saved should not be null.");
        assertEquals(saved, mockSave, "The entity returned was not the same as the entity mocked.");

    }

    @Override
    public void update() throws GatewayException {
        Gateway mockUpdate = buildEntity();

        GatewayDTO dto = buildDto();

        when(factory.from(any(GatewayDTO.class))).thenReturn(mockUpdate);

        when(repository.save(any(Gateway.class))).thenReturn(mockUpdate);

        Gateway updated = service.update(dto);

        assertNotNull(updated, "Entity updated should not be null.");
        assertEquals(updated, mockUpdate, "The entity returned was not the same as the entity mocked.");
    }

    private Gateway buildEntity() {
        return Gateway.builder().id(NumberUtils.INTEGER_ONE).uniqueSerialNumber("DT25354").name("gateway").ip("192.168.10.250")
                .devices(Collections.emptySet()).build();
    }

    private GatewayDTO buildDto() {
        return GatewayDTO.builder().uniqueSerialNumber("DT25354").name("gateway").ip("192.168.10.250").build();
    }
}
