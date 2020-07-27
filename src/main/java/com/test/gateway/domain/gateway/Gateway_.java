package com.test.gateway.domain.gateway;

import com.test.gateway.domain.peripheral_device.PeripheralDevice;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Gateway.class)
public class Gateway_ {

    public static volatile SingularAttribute<Gateway, Integer> id;

    public static volatile SingularAttribute<Gateway, String> uniqueSerialNumber;

    public static volatile SingularAttribute<Gateway, String> name;

    public static volatile SingularAttribute<Gateway, String> ip;

    public static volatile SetAttribute<Gateway, PeripheralDevice> devices;

    public static final String ID = "id";

    public static final String UNIQUE_SERIAL_NUMBER = "uniqueSerialNumber";

    public static final String NAME = "name";

    public static final String IP = "ip";

    public static final String DEVICES = "devices";
}
