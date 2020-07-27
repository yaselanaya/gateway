package com.test.gateway.core.validation;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

public interface FieldValueExists {

    /**
     * Checks whether or not a given value exists for a given field
     *
     * @param fieldValue
     *            The value to check for
     * @param fieldName
     *            The name of the field for which to check if the value exists
     * @param fieldIdValue
     *            The value of the entity id
     * @return True if the value exists for the field; false otherwise
     * @throws UnsupportedOperationException
     */
    boolean fieldValueExists(@NotNull Object fieldValue, @NotNull String fieldName, @Nullable Object fieldIdValue);
}
