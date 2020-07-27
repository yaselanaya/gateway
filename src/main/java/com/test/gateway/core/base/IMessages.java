package com.test.gateway.core.base;

public interface IMessages {

    String getMessage(String id);

    String getMessage(String id, Object[] params);
}
