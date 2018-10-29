package hu.elte.szakdolgozat.spms.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "officeEntityManagerFactory",
        transactionManagerRef = "officeTransactionManager",
        basePackages = {"hu.elte.szakdolgozat.spms.repository.office"})
public class OfficeDbConfig {

    @Autowired
    private OfficeDbConfigProperties configProperties;

    @Bean(name = "officeDataSource")
    @Profile("dev")
    public DataSource officeInMemoryDataSource() {
        String[] urlSplitted = configProperties.datasource().getUrl().split(":");

        String dbName = urlSplitted[urlSplitted.length - 1];

        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName(dbName)
                .build();
    }

    @Bean(name = "officeDataSource")
    @Profile("!dev")
    public DataSource officeDataSource() {
        return DataSourceBuilder.create()
                .url(configProperties.datasource().getUrl())
                .username(configProperties.datasource().getUsername())
                .driverClassName(configProperties.datasource().getDriverClassName())
                .password(configProperties.datasource().getPassword())
                .build();
    }

    @Bean(name = "officeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("officeDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean facory =
                builder
                        .dataSource(dataSource)
                        .packages("hu.elte.szakdolgozat.spms.model.entity.office")
                        .properties(configProperties.jpa().getHibernateProperties(
                                new HibernateSettings().ddlAuto(() -> "none")))
                        .persistenceUnit("officePU")
                        .build();

        return facory;
    }

    @Bean(name = "officeTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("officeEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    //TODO ezt leirni -- db kapcs. -- https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html?fbclid=IwAR3FtD8bd28MYrQ794XcCSrHvR4VZOVPCnKA30f0CX3gmbVnkjCOU6UnNXQ

}
