package fr.mines.religion.infrastructure.repository;

import fr.mines.religion.domain.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Person.PersonBuilder.aPerson()
                .withId(UUID.fromString(rs.getString("id")))
                .withFirstName(rs.getString("firstName"))
                .withLastName(rs.getString("lastName"))
                .withAge(rs.getInt("age"))
                .withGender(rs.getString("gender"))
                .build();
    }
}
