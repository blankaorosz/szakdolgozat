package hu.elte.szakdolgozat.spms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpmsApplication.class, args);
    }
}
