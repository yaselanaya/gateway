package com.test.gateway.core.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;

@Configuration
public class FlyWayConfig {

    @Bean
    public Flyway flyway(DataSource dataSource) {
        final FluentConfiguration configuration = new FluentConfiguration();
        configuration.baselineOnMigrate(true).dataSource(dataSource).locations("classpath:db/migration")
                .encoding(StandardCharsets.UTF_8.name()).validateOnMigrate(false).outOfOrder(true);
        return new Flyway(configuration);
    }
}
