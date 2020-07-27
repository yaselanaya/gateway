package com.test.gateway.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Getter
public class GatewayValidationException extends GatewayException {

    private final transient List<Map<String, Object>> errors;

    public GatewayValidationException(HttpStatus httpStatus, List<Map<String, Object>> errors) {
        super(httpStatus);
        this.errors = errors;
    }

    public GatewayValidationException(HttpStatus httpStatus, String message, List<Map<String, Object>> errors) {
        super(httpStatus, message);
        this.errors = errors;
    }
}
