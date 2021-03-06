package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Faculty;
import ru.itsjava.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

// CRUD API

@Repository     // or @Component
@RequiredArgsConstructor
public class StudentDaoImpl implements StudentDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long insert(Student student) {
        KeyHolder keyHolder = new GeneratedKeyHolder(); // Для возврата сгенерированного БД id.
        Map<String, Object> params = Map.of("fio", student.getFio(), "age", student.getAge(), "faculty_id", student.getFaculty().getId());

        jdbc.update("insert into students(fio, age, faculty_id) values (:fio, :age, :faculty_id)",
                new MapSqlParameterSource(params), keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Student findById(long id) {
        Map<String, Object> params = Map.of("id", id);

        return jdbc.queryForObject("select s.id as SID, fio, age, f.id as FID, f.name from students s, faculties f where s.id = :id and " +
                "s.faculty_id = f.id", params, new StudentsMapper());
    }

    @Override
    public void update(Student student) {
        Map<String, Object> params = Map.of("id", student.getId(), "fio", student.getFio(), "age", student.getAge());

        jdbc.update("update students set fio = :fio where students.id = :id", params);
        jdbc.update("update students set age = :age where students.id = :id", params);
    }

    @Override
    public void delete(Student student) {
        Map<String, Object> params = Map.of("id", student.getId());

        jdbc.update("delete from students where id = :id", params);
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from students", Integer.class);
    }

    @Override
    public List<Student> findAll() {
        return jdbc.query("select s.id as SID, fio, age, f.id as FID, f.name from students s, faculties f " +
                "where s.faculty_id = f.id", new StudentsMapper());
    }

    private static class StudentsMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Student(rs.getLong("SID"), rs.getString("fio"), rs.getInt("age"),
                    new Faculty(rs.getLong("FID"), rs.getString("name")));
        }
    }
}
















