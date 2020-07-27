package com.test.gateway.web.peripheral_device;

import com.test.gateway.core.base.BaseController;
import com.test.gateway.core.base.CoreConstants;
import com.test.gateway.core.base.IEntitySpecification;
import com.test.gateway.core.base.IMessages;
import com.test.gateway.core.exception.GatewayException;
import com.test.gateway.core.filter.SearchCriteria;
import com.test.gateway.domain.peripheral_device.IPeripheralDeviceService;
import com.test.gateway.domain.peripheral_device.PeripheralDevice;
import com.test.gateway.domain.peripheral_device.PeripheralDeviceSpecification;
import com.test.gateway.domain.peripheral_device.PeripheralDevice_;
import com.test.gateway.infraestruture.peripheral_device.PeripheralDeviceConstants;
import com.test.gateway.infraestruture.peripheral_device.PeripheralDeviceFactory;
import com.test.gateway.web.peripheral_device.dto.PeripheralDeviceDTO;
import com.test.gateway.web.peripheral_device.dto.PeripheralDeviceResource;
import com.test.gateway.web.peripheral_device.dto.PeripheralDeviceResourceAssembler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = PeripheralDeviceConstants.BASE_PATH)
@Api(tags = { "Peripheral Device Controller" })
@SwaggerDefinition(tags = { @Tag(name = "Peripheral Device Controller", description = "Operations pertaining to devices") })
public class PeripheralDeviceController extends
        BaseController<PeripheralDevice, PeripheralDeviceDTO, Integer, PeripheralDeviceResource, PeripheralDeviceFactory, PeripheralDeviceController, IPeripheralDeviceService, PeripheralDeviceResourceAssembler> {

    protected PeripheralDeviceController(IPeripheralDeviceService service,
            PeripheralDeviceResourceAssembler assembler, IMessages messages) {
        super(service, assembler, messages);
    }

    @Override
    protected String getIdField() {
        return PeripheralDevice_.UID;
    }

    @Override
    protected IEntitySpecification<PeripheralDevice> getSpecification(List<SearchCriteria> filters, String search) {
        return new PeripheralDeviceSpecification(filters, search);
    }

    @ApiOperation(value = "Retrieve a peripheral device by id.", authorizations = { @Authorization(value = "apiKey") })
    @GetMapping(path = PeripheralDeviceConstants.MAPPING_GET_BY_ID)
    @Override
    public ResponseEntity<PeripheralDeviceResource> findById(@PathVariable Integer id) {
        return super.findById(id);
    }

    @ApiOperation(value = "List peripheral devices by pagination.", authorizations = { @Authorization(value = "apiKey") })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", dataType = "CustomParamPageable", paramType = "query", value = CoreConstants.CUSTOM_PAGEABLE_SWAGGER_FILTERS_SORTS_SEARCH_OPTIONS, example = CoreConstants.CUSTOM_PAGEABLE_SWAGGER_EXAMPLE) })
    @GetMapping
    @Override
    public ResponseEntity<PagedModel<PeripheralDeviceResource>> findAllPaging(HttpServletRequest request) throws GatewayException {
        return super.findAllPaging(request);
    }

    @ApiOperation(value = "Create a peripheral device.", authorizations = { @Authorization(value = "apiKey") })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<PeripheralDeviceResource> save(@RequestBody PeripheralDeviceDTO dto) throws GatewayException {
        return super.save(dto);
    }

    @ApiOperation(value = "Update a peripheral device.", authorizations = { @Authorization(value = "apiKey") })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<PeripheralDeviceResource> update(@RequestBody PeripheralDeviceDTO dto) throws GatewayException {
        return super.update(dto);
    }

    @ApiOperation(value = "Delete a peripheral device.", authorizations = { @Authorization(value = "apiKey") })
    @DeleteMapping(path = PeripheralDeviceConstants.MAPPING_GET_BY_ID)
    @Override
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) throws GatewayException {
        return super.delete(id);
    }
}
