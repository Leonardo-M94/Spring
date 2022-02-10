package ru.itsjava.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.services.IService;
import ru.itsjava.services.MyService;

@Configuration
public class MyConfiguration {

    @Bean
    @ConditionalOnProperty(name = "create", havingValue = "true")
    public IService MyService() {
        return new MyService();
    }
}
