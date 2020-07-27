package com.test.gateway.core.base;

import com.google.common.collect.Lists;
import com.test.gateway.core.exception.ErrorCode;
import com.test.gateway.core.exception.GatewayException;
import com.test.gateway.core.exception.GatewayValidationException;
import com.test.gateway.core.validation.Common;
import com.test.gateway.core.validation.OnCreate;
import com.test.gateway.core.validation.OnUpdate;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Validated
public class BaseEntityValidationService<Entity, DTO extends IDto, Id>
        implements IEntityValidationService<Entity, DTO, Id> {

    private final Validator validator;

    protected final IMessages messages;

    protected final BaseServiceUtil serviceUtil;

    public BaseEntityValidationService(Validator validator, IMessages messages, BaseServiceUtil serviceUtil) {
        this.validator = validator;
        this.messages = messages;
        this.serviceUtil = serviceUtil;
    }

    /**
     *
     * @param entity
     * @throws GatewayValidationException
     */
    public void validateForCreate(Entity entity) throws GatewayValidationException {
        List<Map<String, Object>> errors = validateForCreateCommonLogic(entity);

        if (!errors.isEmpty())
            throw new GatewayValidationException(HttpStatus.UNPROCESSABLE_ENTITY, errors);
    }

    @Override
    public void validateForCreate(Entity entity, DTO dto) throws GatewayValidationException {
        List<Map<String, Object>> errors = validateForCreateCommonLogic(entity);
        errors.addAll(validateBusinessConstraintsDTO(dto));

        if (!errors.isEmpty())
            throw new GatewayValidationException(HttpStatus.UNPROCESSABLE_ENTITY, errors);
    }

    @Override
    public void validateForUpdate(Entity entity) throws GatewayValidationException {
        List<Map<String, Object>> errors = validateForUpdateCommonLogic(entity);

        if (!errors.isEmpty())
            throw new GatewayValidationException(HttpStatus.UNPROCESSABLE_ENTITY, errors);
    }

    @Override
    public void validateForUpdate(Entity entity, DTO dto) throws GatewayValidationException {
        List<Map<String, Object>> errors = validateForUpdateCommonLogic(entity);
        errors.addAll(validateBusinessConstraintsDTO(dto));

        if (!errors.isEmpty())
            throw new GatewayValidationException(HttpStatus.UNPROCESSABLE_ENTITY, errors);
    }

    @Override
    public void validateForDelete(Id id) throws GatewayValidationException {
        List<Map<String, Object>> errors = validateDeleteBusinessConstraints(id);

        if (!errors.isEmpty())
            throw new GatewayValidationException(HttpStatus.UNPROCESSABLE_ENTITY, errors);
    }

    @Override
    public Map<String, Object> createError(String field, ErrorCode code, String messageKey, Object value) {
        return serviceUtil.createError(field, code, messages.getMessage(messageKey), value);
    }

    /**
     *
     * @param entity
     * @param groups
     * @throws GatewayValidationException
     */
    protected void validateStructure(Entity entity, Class... groups) throws GatewayValidationException {
        List<Map<String, Object>> errors = new ArrayList<>();
        Set<ConstraintViolation<Entity>> validate = validator.validate(entity, groups);

        if (validate.isEmpty())
            return;

        validate.stream()
                .map(entityConstraintViolation -> Pair.of(entityConstraintViolation.getPropertyPath().toString(),
                        entityConstraintViolation.getMessage()))
                .forEach(error -> errors.add(createError(getField(error), ErrorCode.INVALID_VALUE, error.getSecond(),
                        getPropertyValue(entity, error.getFirst()))));

        throw new GatewayValidationException(HttpStatus.BAD_REQUEST, errors);
    }

    /**
     *
     * @param entity
     * @return
     */
    protected List<Map<String, Object>> validateCommonBusinessConstraints(Entity entity) {
        return Lists.newArrayList();
    }

    /**
     *
     * @param entity
     * @return
     */
    protected List<Map<String, Object>> validateCreateBusinessConstraints(Entity entity) {
        return Lists.newArrayList();
    }

    /**
     *
     * @param entity
     * @return
     */
    protected List<Map<String, Object>> validateUpdateBusinessConstraints(Entity entity) {
        return Lists.newArrayList();
    }

    /**
     *
     * @param id
     * @return
     */
    protected List<Map<String, Object>> validateDeleteBusinessConstraints(Id id) {
        return Lists.newArrayList();
    }

    /**
     * Validate relationship between source destination mapping
     *
     * @param dto
     *            object with the lists of source destination mapping
     */
    protected List<Map<String, Object>> validateBusinessConstraintsDTO(DTO dto) {
        return Lists.newArrayList();
    }

    /**
     * Attempt to retrieve the property value given an Entity and the field propertyPath
     *
     * @param entity
     *            The entity that own the property
     * @param propertyPath
     *            The propertyPath for which we are searching the value
     * @return The value of the field or {@code Strings.EMPTY} when the field value can't be resolve or the propertyPath is
     *         {@code null}
     */
    private Object getPropertyValue(Entity entity, String propertyPath) {
        if (Strings.isEmpty(propertyPath))
            return Strings.EMPTY;

        //Searching field in concrete class
        try {
            return getFieldValue(entity, entity.getClass(), propertyPath);
        } catch (GatewayException e) {
            log.error("Error resolving property value in concrete class", e);
        }

        //Searching field in parent class
        try {
            return getFieldValue(entity, entity.getClass().getSuperclass(), propertyPath);
        } catch (GatewayException e) {
            log.error("Error resolving property value in parent class", e);
        }

        return Strings.EMPTY;
    }

    /**
     *
     * @param entity
     * @param clazz
     * @param propertyPath
     * @return
     * @throws GatewayException
     */
    private Object getFieldValue(Entity entity, Class clazz, String propertyPath) throws GatewayException {
        try {
            Field field = clazz.getDeclaredField(propertyPath);
            field.setAccessible(true);
            return field.get(entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new GatewayException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     *
     * @param error
     * @return
     */
    private String getField(Pair<String, String> error) {

        if (!error.getFirst().contains(CoreConstants.DOT))
            return error.getFirst();

        String[] split = error.getFirst().split(CoreConstants.SCAPE_DOT);
        return split[split.length - 1];
    }

    /**
     *
     * @param entity
     * @return
     * @throws GatewayValidationException
     */
    private List<Map<String, Object>> validateForCreateCommonLogic(Entity entity) throws GatewayValidationException {
        validateStructure(entity, OnCreate.class, Common.class);
        List<Map<String, Object>> errors = validateCommonBusinessConstraints(entity);
        errors.addAll(validateCreateBusinessConstraints(entity));

        return errors;
    }

    /**
     *
     * @param entity
     * @return
     * @throws GatewayValidationException
     */
    private List<Map<String, Object>> validateForUpdateCommonLogic(Entity entity) throws GatewayValidationException {
        validateStructure(entity, OnUpdate.class, Common.class);
        List<Map<String, Object>> errors = validateCommonBusinessConstraints(entity);
        errors.addAll(validateUpdateBusinessConstraints(entity));

        return errors;
    }
    
}
