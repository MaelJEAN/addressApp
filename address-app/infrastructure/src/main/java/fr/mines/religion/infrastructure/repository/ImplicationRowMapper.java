package fr.mines.religion.infrastructure.repository;

import fr.mines.religion.domain.model.Group;
import fr.mines.religion.domain.model.Implication;
import fr.mines.religion.domain.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ImplicationRowMapper implements RowMapper<Implication> {

    @Override
    public Implication mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Implication.ImplicationBuilder.anImplication()
                .withId(UUID.fromString(rs.getString("id")))
                .withStatus(rs.getString("status"))
                .withPerson(rs.getObject("person", Person.class))
                .withGroup(rs.getObject("group", Group.class))
                .build();
    }
}
