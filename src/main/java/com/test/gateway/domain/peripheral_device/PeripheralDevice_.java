package com.test.gateway.domain.peripheral_device;

import com.test.gateway.domain.gateway.Gateway;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PeripheralDevice.class)
public class PeripheralDevice_ {

    public static volatile SingularAttribute<PeripheralDevice, Integer> uid;

    public static volatile SingularAttribute<PeripheralDevice, String> vendor;

    public static volatile SingularAttribute<PeripheralDevice, LocalDate> createdDate;

    public static volatile SingularAttribute<PeripheralDevice, Boolean> status;

    public static volatile SingularAttribute<PeripheralDevice, Gateway> gateway;

    public static final String UID = "uid";

    public static final String VENDOR = "vendor";

    public static final String CREATED_DATE= "createdDate";

    public static final String STATUS = "status";

    public static final String GATEWAY = "gateway";

}
