package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PetDaoImpl implements PetDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Pet> findAll() {

        return jdbc.query("select id, breed from pet", new PetMapper());
    }

    @Override
    public Pet findByName(String name) {
        return jdbc.queryForObject("select id, breed from pet where breed = :name",
                new MapSqlParameterSource(Map.of("name", name)), new PetMapper());
    }

    private static class PetMapper implements RowMapper<Pet> {
        @Override
        public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Pet(rs.getLong("id"), rs.getString("breed"));
        }
    }
}
