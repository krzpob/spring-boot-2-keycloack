package pl.javasoft.springboot2keycloack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringBoot2KeycloackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot2KeycloackApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplateBuilder().build();
    }

}
