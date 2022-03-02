package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Email;
import ru.itsjava.domain.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class EmailDaoImpl implements EmailDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from emails", Integer.class);
    }

    @Override
    public long insert(Email email) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Map<String, Object> params = Map.of("email", email.getEmail(), "password", email.getPassword(),
                "fio", email.getFio(), "birthday", email.getBirthday(), "male", email.getMale(),
                "pet_id", email.getPet().getId());

        jdbc.update("insert into emails(email, password, fio, birthday, male, pet_id) " +
                "values (:email, :password, :fio, :birthday, :male, :pet_id)",
                new MapSqlParameterSource(params), keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(Email email) {   // Для отладки
        Map<String, Object> params = Map.of("id", email.getId(), "email", email.getEmail(),
                "password", email.getPassword(), "fio", email.getFio(), "birthday",
                email.getBirthday(), "male", email.getMale());

        jdbc.update("update emails set id = :id, email = :email, password = :password, fio = :fio, " +
                "birthday = :birthday, male = :male where id = :id", params);
    }

    @Override
    public void updatePassword(long id, String password) {
        Map<String, Object> params = Map.of("id", id, "password", password);

        jdbc.update("update emails set password = :password where id = :id", params);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Map.of("id", id);

        jdbc.update("delete from emails where id = :id", params);
    }

    @Override
    public void deleteByEmail(String email) {
        Map<String, Object> params = Map.of("email", email);

        jdbc.update("delete from emails where email = :email", params);
    }

    @Override
    public Email findById(long id) {
        Map<String, Object> params = Map.of("id", id);

        return jdbc.queryForObject("select e.id, email, password, fio, birthday, male, p.id, p.breed " +
                "from emails e, pet p where e.id = :id and e.pet_id = p.id", params, new EmailMapper());
    }

    private static class EmailMapper implements RowMapper<Email> {
        @Override
        public Email mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Email(rs.getLong("id"), rs.getString("email"), rs.getString("password"),
                    rs.getString("fio"), rs.getDate("birthday"), rs.getBoolean("male"),
                    new Pet(rs.getLong("id"), rs.getString("breed")));
        }
    }
}
