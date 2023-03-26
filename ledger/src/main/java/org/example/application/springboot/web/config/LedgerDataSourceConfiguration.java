package org.example.application.springboot.web.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.example.adapter.gateway.repository",
        entityManagerFactoryRef = "ledgerEntityManagerFactory",
        transactionManagerRef= "ledgerTransactionManager"
)
public class LedgerDataSourceConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.ledger")
    public DataSourceProperties ledgerDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.ledger.configuration")
    public DataSource ledgerDataSource() {
        return ledgerDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "ledgerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean ledgerEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(ledgerDataSource())
                .packages("org.example.adapter.gateway.repository")
                .build();
    }

    @Bean
    public PlatformTransactionManager ledgerTransactionManager(
            final @Qualifier("ledgerEntityManagerFactory") LocalContainerEntityManagerFactoryBean ledgerEntityManagerFactory) {
        return new JpaTransactionManager(ledgerEntityManagerFactory.getObject());
    }
}
