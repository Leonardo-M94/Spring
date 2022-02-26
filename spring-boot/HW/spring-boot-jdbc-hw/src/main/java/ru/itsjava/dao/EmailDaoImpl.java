package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Email;

@Repository
@RequiredArgsConstructor
public class EmailDaoImpl implements EmailDao {

    private final JdbcOperations jdbc;

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from emails", Integer.class);
    }

    @Override
    public void insert(Email email) {
        jdbc.update("insert into emails(email, password, fio, birthday, male) values (?, ?, ?, ?, ?)",
                email.getEmail(), email.getPassword(), email.getFio(), email.getBirthday(), email.getMale());
    }

    @Override
    public void update(Email email) {   // Для отладки
        jdbc.update("update emails set email = ?, password = ?, fio = ?, birthday = ?, male = ? where id = ?",
                email.getEmail(), email.getPassword(), email.getFio(), email.getBirthday(), email.getMale(), email.getId());
    }

    @Override
    public void updatePassword(long id, String password) {
        jdbc.update("update emails set password = ? where id = ?", password, id);
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from emails where id = ?", id);
    }

    @Override
    public void deleteByEmail(String email) {
        jdbc.update("delete from emails where email = ?", email);
    }
}
