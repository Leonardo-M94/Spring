package ru.itsjava.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.services.*;

import java.io.InputStream;

@Configuration
public class MyConfig {
    @Value("${notebook.firm}")
    private String firm;
    @Value("${notebook.model}")
    private String model;
    @Value("${notebook.year}")
    private int year;
    @Value("#{T(java.lang.System).in}")
    private InputStream inputStream;

    @Bean
    public NotebookService notebookService() {
        return new NotebookServiceImpl(firm, model, year);
    }

    @Bean
    public IOService ioService() {
        return new IOServiceImpl(inputStream);
    }

    @Bean
    public ProgrammerService programmerService(NotebookService notebookService, IOService ioService) {
        return new ProgrammerServiceImpl(notebookService, ioService);
    }
}
