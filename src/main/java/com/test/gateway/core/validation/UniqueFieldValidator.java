package com.test.gateway.core.validation;

import com.google.common.base.Strings;
import com.test.gateway.core.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;

@Slf4j
public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {

    private FieldValueExists service;

    private String fieldName;

    private String fieldIdName;

    private String message;

    @Override
    public void initialize(UniqueField uniqueField) {
        Class<? extends FieldValueExists> clazz = uniqueField.service();
        this.fieldName = uniqueField.fieldName();
        this.fieldIdName = uniqueField.fieldIdName();
        String serviceQualifier = uniqueField.serviceQualifier();
        this.service = Strings.isNullOrEmpty(serviceQualifier) ? BeanUtil.getBean(clazz) : BeanUtil.getBean(serviceQualifier);
        this.message = uniqueField.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean isValid = false;
        try {
            Object fieldValue = extractFieldValue(value, this.fieldName);

            if (Objects.isNull(fieldValue))
                return true;

            isValid = !this.service.fieldValueExists(fieldValue, this.fieldName, extractFieldValue(value, this.fieldIdName));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Error during the validation of the unique property " + this.fieldIdName + ".", e);
        }

        if (isValid)
            return true;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(this.message).addPropertyNode(this.fieldName).addConstraintViolation();

        return false;

    }

    /**
     *
     * @param entity
     *            The entity to validate
     * @param fieldName
     *            The field to extract the value
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private Object extractFieldValue(Object entity, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = entity.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(entity);
    }
}
