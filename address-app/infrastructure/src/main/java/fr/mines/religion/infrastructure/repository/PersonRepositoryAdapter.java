package fr.mines.religion.infrastructure.repository;

import fr.mines.religion.domain.model.Person;
import fr.mines.religion.port.driven.PersonRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PersonRepositoryAdapter implements PersonRepositoryPort {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PersonRepositoryAdapter(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public Collection<Person> selectAll() {
        return jdbcTemplate.query("select id,firstName,lastName,age, gender from person", new PersonRowMapper());
    }

    @Override
    public Collection<Person> selectByGender(String gender) {
        return this.jdbcTemplate.query("select id,firstName,lastName,age, gender from person where gender=?", new PersonRowMapper(), gender).stream().toList();
    }

    @Override
    public Collection<Person> selectByAge(Integer age) {
        return this.jdbcTemplate.query("select id,firstName,lastName,age, gender from person where age=?", new PersonRowMapper(), age).stream().toList();
    }

    @Override
    public Optional<Person> select(UUID id) {
        return this.jdbcTemplate.query("select id,firstName,lastName,age, gender from person where id=?", new PersonRowMapper(), id).stream().findFirst();
    }

    @Override
    public Person insert(Person person) {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(Objects.requireNonNull(jdbcTemplate.getDataSource())).withTableName("person");
        final UUID uuid = UUID.randomUUID();

        final SqlParameterSource in = new MapSqlParameterSource()
                .addValue("id", uuid)
                .addValue("firstName", person.firstName())
                .addValue("lastName", person.lastName())
                .addValue("age", person.age())
                .addValue("gender", person.gender());
        simpleJdbcInsert.execute(in);

        return Person.PersonBuilder.aPerson()
                .withId(uuid)
                .withFirstName(person.firstName())
                .withLastName(person.lastName())
                .withAge(person.age())
                .withGender(person.gender())
                .build();
    }

    @Override
    public Person update(Person person) {
        jdbcTemplate.update("update person set firstName = ?, lastName = ?, age = ?, gender = ? where id = ?", person.firstName(), person.lastName(), person.age(), person.gender(), person.id());

        return person;
    }

    @Override
    public void delete(UUID uuid) {
        jdbcTemplate.update("delete from implication where person_id=?", uuid);
        jdbcTemplate.update("delete from person where id=?", uuid);
    }
}
