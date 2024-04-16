package fr.mines.religion.infrastructure.repository;

import fr.mines.religion.domain.model.*;
import org.mines.address.domain.model.Town;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class HasGroupRowMapper implements RowMapper<HasGroup>{

    @Override
    public HasGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
        String type = rs.getString("type");

        HasGroup hasGroup = null;
        
        Group group = Group.GroupBuilder.aGroup()
                .withId(UUID.fromString(rs.getString("id")))
                .withName(rs.getString("name"))
                .withDescription(rs.getString("description"))
                .withType(rs.getString("type"))
                .build();
        
        switch (type){
            case "religion" ->
                    hasGroup = Religion.ReligionBuilder.aReligion()
                    .withGroup(group)
                    .withBeliefs(rs.getString("belief"))
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

        return hasGroup;
    }
}
