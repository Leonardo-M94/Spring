package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.domain.Notebook;

import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Класс ProgrammerServiceImpl")
public class ProgrammerServiceImplTest {

    @Configuration
    static class MyConfiguration {

        @Bean
        public IOService ioService() { // Заглушка с помощью Mockito
            IOService mockIOService = Mockito.mock(IOServiceImpl.class);
            when(mockIOService.input()).thenReturn("Alex"); // Задаем поведение.

            return mockIOService;
        }

        @Bean
        public NotebookService notebookService() {
            NotebookService notebookService = Mockito.mock(NotebookService.class);
            when(notebookService.getNotebook()).thenReturn(new Notebook("Asus", "G115AF", 2018));

            return notebookService;
        }

        @Bean
        public ProgrammerService programmerService(NotebookService notebookService, IOService ioService) {

            return new ProgrammerServiceImpl(notebookService, ioService);
        }

    }

    @Autowired
    private ProgrammerService programmerService;

    @DisplayName("должен корректно приветствовать нового разработчика")
    @Test
    public void shouldHaveCorrectSayHiToNewProgrammer() {
        programmerService.hiToNewProgrammer();
    }
}
