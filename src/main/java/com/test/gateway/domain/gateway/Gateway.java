package com.test.gateway.domain.gateway;

import com.test.gateway.core.validation.Common;
import com.test.gateway.core.validation.OnUpdate;
import com.test.gateway.core.validation.UniqueField;
import com.test.gateway.domain.peripheral_device.PeripheralDevice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@Builder
@Table(name = "gateway")
@UniqueField(service = IGatewayService.class, fieldName = Gateway_.UNIQUE_SERIAL_NUMBER, fieldIdName = Gateway_.ID, groups = Common.class, message = "validation.error.gateway.duplicate.unique.serial.number")
public class Gateway implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "validation.error.gateway.notnull.id", groups = OnUpdate.class)
    private Integer id;

    @Column(name = "unique_serial_number")
    @NotNull(message = "validation.error.gateway.notnull.unique.serial.number", groups = Common.class)
    private String uniqueSerialNumber;

    @Column(name = "name")
    @NotNull(message = "validation.error.gateway.notnull.name", groups = Common.class)
    private String name;

    @NotNull(message = "validation.error.gateway.notnull.ip", groups = Common.class)
    @Pattern(regexp = "\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b", message = "validation.error.gateway.regex.ip", groups = Common.class)
    @Column(name = "ip")
    private String ip;

    @OneToMany(mappedBy = "gateway", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PeripheralDevice> devices;

}
