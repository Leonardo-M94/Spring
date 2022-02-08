package ru.itsjava.proxy;

public class AbstractBookDaoImplProxy extends AbstractBookDao {

    @Override
    public String getBook(long id) {
        System.out.println("Hello from proxy class based on abstract class!");
        return new AbstractBookDaoImpl().getBook(1L);
    }
}
