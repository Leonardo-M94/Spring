package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PetDaoImpl implements PetDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long insert(Pet pet) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update("insert into pet(breed) values(:breed)",
                new MapSqlParameterSource(Map.of("breed", pet.getBreed())), keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Pet> findAll() {

        return jdbc.query("select id, breed from pet", new PetMapper());
    }

    @Override
    public Optional<Pet> findByName(String name) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("select id, breed from pet where breed = :name",
                    new MapSqlParameterSource(Map.of("name", name)), new PetMapper()));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    private static class PetMapper implements RowMapper<Pet> {
        @Override
        public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Pet(rs.getLong("id"), rs.getString("breed"));
        }
    }
}
