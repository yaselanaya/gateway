package com.test.gateway.domain.peripheral_device;

import com.test.gateway.core.validation.Common;
import com.test.gateway.core.validation.OnUpdate;
import com.test.gateway.domain.gateway.Gateway;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@Builder
@Table(name = "peripheral_device")
public class PeripheralDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "validation.error.peripheral.device.notnull.uid", groups = OnUpdate.class)
    private Integer uid;

    @Column(name = "vendor")
    @NotNull(message = "validation.error.peripheral.device.notnull.vendor", groups = Common.class)
    private String vendor;

    @Column(name = "created_date")
    @NotNull(message = "validation.error.peripheral.device.notnull.date", groups = Common.class)
    private LocalDate createdDate;

    @Column(name = "status")
    private Boolean status;

    @JoinColumn(name = "gateway_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Gateway gateway;

}
