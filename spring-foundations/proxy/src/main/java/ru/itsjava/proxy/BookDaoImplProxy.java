package ru.itsjava.proxy;

public class BookDaoImplProxy implements BookDao {

    @Override
    public String getBook(long id) {
        System.out.println("Hello from proxy class based on interface!");
        return new BookDaoImpl().getBook(id);
    }
}
