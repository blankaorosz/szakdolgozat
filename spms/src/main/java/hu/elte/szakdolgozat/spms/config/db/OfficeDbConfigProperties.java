package hu.elte.szakdolgozat.spms.config.db;

import lombok.Data;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("office")
@Data
public class OfficeDbConfigProperties {
    private Datasource datasource = new Datasource();
    private JpaProperties jpa = new JpaProperties();

    @Data
    public class Datasource {
        private String url;
        private String driverClassName;
        private String username;
        private String password;
    }

    public Datasource datasource() {
        return datasource;
    }

    public JpaProperties jpa() {
        return jpa;
    }
}

