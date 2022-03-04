package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Email;
import ru.itsjava.domain.User;
import ru.itsjava.domain.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from users", Integer.class);
    }

    @Override
    public long insert(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Map<String, Object> params = Map.of("fio", user.getFio(), "birthday", user.getBirthday(), "male", user.getMale(),
                "email_id", user.getEmail().getId(), "pet_id", user.getPet().getId());

        jdbc.update("insert into users(fio, birthday, male, email_id, pet_id) " +
                        "values (:fio, :birthday, :male, :email_id, :pet_id)",
                new MapSqlParameterSource(params), keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void update(User user) {
        Map<String, Object> params = Map.of("id", user.getId(), "fio", user.getFio(),
                "birthday", user.getBirthday(), "male", user.getMale());

        jdbc.update("update users set fio = :fio, birthday = :birthday, " +
                "male = :male where id = :id", params);
    }

    @Override
    public void delete(User user) {
        Map<String, Object> params = Map.of("id", user.getId());

        jdbc.update("delete from users where id = :id", params);
    }

    @Override
    public User findById(long id) {
        Map<String, Object> params = Map.of("id", id);

        return jdbc.queryForObject("select u.id as UID, fio, birthday, male, e.id as EID, email, password, p.id as PID, breed " +
                        "from users u, emails e, pet p " +
                        "where u.id = :id and u.email_id = e.id and u.pet_id = p.id",
                new MapSqlParameterSource(params), new UserMapper());
    }

    @Override
    public List<User> findAll() {
        return jdbc.query("select u.id as UID, fio, birthday, male, e.id as EID, email, password, p.id as PID, breed " +
                        "from users u, emails e, pet p where u.email_id = e.id and u.pet_id = p.id",
                new UserMapper());
    }

    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("UID"), rs.getString("fio"), rs.getDate("birthday"),
                    rs.getBoolean("male"),
                    new Email(rs.getLong("EID"), rs.getString("email"), rs.getString("password")),
                    new Pet(rs.getLong("PID"), rs.getString("breed")));
        }
    }
}
