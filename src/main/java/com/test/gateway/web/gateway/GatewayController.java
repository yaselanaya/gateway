package com.test.gateway.web.gateway;

import com.test.gateway.core.base.BaseController;
import com.test.gateway.core.base.CoreConstants;
import com.test.gateway.core.base.IEntitySpecification;
import com.test.gateway.core.base.IMessages;
import com.test.gateway.core.exception.GatewayException;
import com.test.gateway.core.filter.SearchCriteria;
import com.test.gateway.core.util.BeanUtil;
import com.test.gateway.domain.gateway.Gateway;
import com.test.gateway.domain.gateway.GatewaySpecification;
import com.test.gateway.domain.gateway.Gateway_;
import com.test.gateway.domain.gateway.IGatewayService;
import com.test.gateway.infraestruture.gateway.GatewayConstants;
import com.test.gateway.infraestruture.gateway.GatewayFactory;
import com.test.gateway.web.gateway.dto.GatewayDTO;
import com.test.gateway.web.gateway.dto.GatewayResource;
import com.test.gateway.web.gateway.dto.GatewayResourceAssembler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.config.HateoasAwareSpringDataWebConfiguration;
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
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = GatewayConstants.BASE_PATH)
@Api(tags = { "Gateway Controller" })
@SwaggerDefinition(tags = { @Tag(name = "Gateway Controller", description = "Operations pertaining to gateways") })
public class GatewayController extends
        BaseController<Gateway, GatewayDTO, Integer, GatewayResource, GatewayFactory, GatewayController, IGatewayService, GatewayResourceAssembler> {

    protected GatewayController(IGatewayService service, GatewayResourceAssembler assembler, IMessages messages) {
        super(service, assembler, messages);
    }

    @Override
    protected String getIdField() {
        return Gateway_.ID;
    }

    @Override
    protected IEntitySpecification<Gateway> getSpecification(List<SearchCriteria> filters, String search) {
        return new GatewaySpecification(filters, search);
    }

    @ApiOperation(value = "Retrieve a gateway by id.", authorizations = { @Authorization(value = "apiKey") })
    @GetMapping(path = GatewayConstants.MAPPING_GET_BY_ID)
    @Override
    public ResponseEntity<GatewayResource> findById(@PathVariable Integer id) {
        return super.findById(id);
    }

    @ApiOperation(value = "List gateways by pagination.", authorizations = { @Authorization(value = "apiKey") })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", dataType = "CustomParamPageable", paramType = "query", value = CoreConstants.CUSTOM_PAGEABLE_SWAGGER_FILTERS_SORTS_SEARCH_OPTIONS, example = CoreConstants.CUSTOM_PAGEABLE_SWAGGER_EXAMPLE) })
    @GetMapping
    @Override
    public ResponseEntity<PagedModel<GatewayResource>> findAllPaging(HttpServletRequest request) throws GatewayException {
        return super.findAllPaging(request);
    }

    @ApiOperation(value = "Create a gateway.", authorizations = { @Authorization(value = "apiKey") })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<GatewayResource> save(@RequestBody GatewayDTO dto) throws GatewayException {
        return super.save(dto);
    }

    @ApiOperation(value = "Update a gateway.", authorizations = { @Authorization(value = "apiKey") })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<GatewayResource> update(@RequestBody GatewayDTO dto) throws GatewayException {
        return super.update(dto);
    }

    @ApiOperation(value = "Delete a gateway.", authorizations = { @Authorization(value = "apiKey") })
    @DeleteMapping(path = GatewayConstants.MAPPING_GET_BY_ID)
    @Override
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) throws GatewayException {
        return super.delete(id);
    }
}
