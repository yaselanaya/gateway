package com.test.gateway.core.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;

@Configuration
public class FlyWayConfig {

    private Environment env;

    public FlyWayConfig(Environment env) {
        this.env = env;
    }

    @Bean(name = "gatewayDatasource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Primary
    @Bean(initMethod = "migrate")
    public Flyway flyway(@Qualifier("gatewayDatasource") DataSource dataSource) {
        final FluentConfiguration configuration = new FluentConfiguration();
        configuration.baselineOnMigrate(true).dataSource(dataSource).locations("classpath:db/migration")
                .encoding(StandardCharsets.UTF_8.name()).validateOnMigrate(false);

        return new Flyway(configuration);
    }
}
