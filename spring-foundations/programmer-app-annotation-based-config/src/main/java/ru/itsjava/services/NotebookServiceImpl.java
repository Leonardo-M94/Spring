package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itsjava.domain.Notebook;

@Service
public class NotebookServiceImpl implements NotebookService {

    private final String firm;
    private final String model;
    private final int year;

    public NotebookServiceImpl(@Value("${notebook.firm}") String firm,
                               @Value("${notebook.model}") String model,
                               @Value("${notebook.year}") int year) {
        this.firm = firm;
        this.model = model;
        this.year = year;
    }

    @Override
    public Notebook getNotebook() {
        return new Notebook(firm, model, year);
    }
}
