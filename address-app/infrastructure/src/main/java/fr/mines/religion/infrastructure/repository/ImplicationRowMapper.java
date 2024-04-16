package fr.mines.religion.infrastructure.repository;

import fr.mines.religion.domain.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ImplicationRowMapper implements RowMapper<Implication> {
    @Override
    public Implication mapRow(ResultSet rs, int rowNum) throws SQLException {
        String type = rs.getString("type");

        HasGroup hasGroup = null;

        Group group = Group.GroupBuilder.aGroup()
                .withId(UUID.fromString(rs.getString("groupId")))
                .withName(rs.getString("name"))
                .withDescription(rs.getString("description"))
                .withType(rs.getString("type"))
                .build();

        Person person = Person.PersonBuilder.aPerson()
                .withId(UUID.fromString(rs.getString("personId")))
                .withFirstName(rs.getString("firstName"))
                .withLastName(rs.getString("lastName"))
                .withAge(rs.getInt("age"))
                .withGender(rs.getString("gender"))
                .build();

        switch (type){
            case "religion" ->
                    hasGroup = Religion.ReligionBuilder.aReligion()
                            .withGroup(group)
                            .withBeliefs(rs.getString("beliefs"))
                            .build();
            case "origin" ->
                    hasGroup = Origin.OriginBuilder.anOrigin()
                            .withGroup(group)
                            .withRegion(rs.getString("region"))
                            .build();
            case "politic" ->
                    hasGroup = Politic.PoliticBuilder.aPolitic()
                            .withGroup(group)
                            .withPosition(rs.getString("position"))
                            .build();
        }

        return Implication.ImplicationBuilder.anImplication()
                .withId(UUID.fromString(rs.getString("id")))
                .withHasGroup(hasGroup)
                .withPerson(person)
                .withStatus(rs.getString("status"))
                .build();
    }
}
