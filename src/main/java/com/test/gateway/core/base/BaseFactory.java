package com.test.gateway.core.base;

import com.test.gateway.core.exception.GatewayException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
public class BaseFactory<Entity, Resource, Dto extends IDto> implements IFactory<Entity, Resource, Dto> {

    protected final ModelMapper mapper;

    protected IMessages messages;

    protected final Class<Entity> entityClass;

    protected final Class<Resource> resourceClass;

    public BaseFactory(Class<Entity> entityClass, Class<Resource> resourceClass, IMessages messages) {
        this.messages = messages;
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.entityClass = entityClass;
        this.resourceClass = resourceClass;
        configureDtoMapper();
        configureEntityMapper();
    }

    public Entity from(Dto dto) throws GatewayException {

        Entity entity;

        try {
            entity = entityClass.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            String msg = messages.getMessage("default.error.map.dto.entity.resource",
                    new Object[] { entityClass.toString(), resourceClass.toString() });
            throw new GatewayException(HttpStatus.UNPROCESSABLE_ENTITY, msg);
        }

        this.mapper.map(dto, entity);

        // Do anything else
        dtoToEntityActions(entity, dto);

        return entity;
    }

    public Resource from(Entity entity) {

        Resource resource;

        try {
            resource = resourceClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error(e.getMessage(), e);
            return null;
        }

        this.mapper.map(entity, resource);

        // Do anything else
        entityToResourceActions(resource, entity);

        return resource;
    }

    /**
     *
     * @param getter
     *            function to get the value.
     * @param setter
     *            function to set to null the field.
     */
    public void setEntityToNullIfEmpty(Supplier<Object> getter, Consumer<String> setter) {
        if (Objects.nonNull(getter.get()) && Strings.isEmpty(getter.get().toString()))
            setter.accept(null);
    }

    /**
     * Implement specific logic relative to the mapping
     *
     * @param entity
     * @param dto
     */
    protected void dtoToEntityActions(Entity entity, Dto dto) {
        // Implement specific logic for the process.
    }

    /**
     * Implement specific logic relative to the mapping
     *
     * @param resource
     * @param entity
     */
    protected void entityToResourceActions(Resource resource, Entity entity) {
        // Implement specific logic for the process.
    }

    /**
     * Configure model mapper
     */
    protected void configureDtoMapper() {
        // Configure mapper for the process of dto mapping.
    }

    /**
     * Configure model mapper
     */
    protected void configureEntityMapper() {
        // Configure mapper for the process of entity mapping.
    }

    /**
     * Apply the {@code formatter} and {@code setter} functions if the getter function does not contains a null value
     *
     * @param getter
     * @param formatter
     * @param setter
     */
    protected void setLocalDateIfNotNull(Supplier<String> getter, final DateTimeFormatter formatter,
            Consumer<LocalDate> setter) {
        Optional.ofNullable(getter.get()).map(currentDateTime -> LocalDate.parse(currentDateTime, formatter))
                .ifPresent(setter);
    }

    /**
     * Apply the {@code formatter} and {@code setter} functions if the getter function does not contains a null value
     *
     * @param getter
     * @param formatter
     * @param setter
     */
    protected void setFormattedDateOrEmpty(Supplier<LocalDate> getter, final DateTimeFormatter formatter,
            Consumer<String> setter) {
        String date = Optional.ofNullable(getter.get()).map(currentDate -> currentDate.format(formatter))
                .orElse(Strings.EMPTY);
        setter.accept(date);
    }

    /**
     *
     * @param getter
     * @param setter
     * @param repository
     * @param <Id>
     * @param <Entity>
     * @param <Repository>
     */
    protected <Id, Entity, Repository extends IBaseRepository<Entity, Id>> void setEntityIfNotNull(Supplier<Id> getter,
            Repository repository, Consumer<Entity> setter) {
        Optional.ofNullable(getter.get()).map(repository::findById).filter(Optional::isPresent).map(Optional::get)
                .ifPresent(setter);
    }
}
