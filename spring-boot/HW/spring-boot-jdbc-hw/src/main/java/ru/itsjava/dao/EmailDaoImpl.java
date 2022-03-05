package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ru.itsjava.domain.Email;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmailDaoImpl implements EmailDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {

        return jdbc.getJdbcOperations().queryForObject("select count(*) from emails", Integer.class);
    }

    @Override
    public long insert(Email email) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        Map<String, Object> params = Map.of("email", email.getEmail(), "password", email.getPassword());
        jdbc.update("insert into emails(email, password) values (:email, :password)",
                new MapSqlParameterSource(params), keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(Email email) {
        Map<String, Object> params = Map.of("id", email.getId(), "email", email.getEmail(), "password", email.getPassword());
        jdbc.update("update emails set email = :email, password = :password where id = :id", params);
    }

    @Override
    public void delete(Email email) {

        jdbc.update("delete from emails where id = :id", Map.of("id", email.getId()));
    }

    @Override
    public Email findById(long id) {
        return jdbc.queryForObject("select id, email, password from emails where id = :id",
                new MapSqlParameterSource(Map.of("id", id)), new EmailMapper());
    }

    @Override
    public Optional<Email> findByLogin(String email) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("select id, email, password from emails where email = :email",
                    new MapSqlParameterSource(Map.of("email", email)), new EmailMapper()));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public List<Email> findAll() {
        return jdbc.query("select id, email, password from emails", new EmailMapper());
    }

    private static class EmailMapper implements RowMapper<Email> {
        @Override
        public Email mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Email(rs.getLong("id"), rs.getString("email"), rs.getString("password"));
        }
    }
}
