package fr.mines.religion.infrastructure.repository;


import fr.mines.religion.domain.model.*;
import fr.mines.religion.port.driven.HasGroupRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class HasGroupRepositoryAdapter implements HasGroupRepositoryPort {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public HasGroupRepositoryAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Collection<HasGroup> selectAll() {
        return jdbcTemplate.query("select id,name,description,type,region,position,beliefs from groups", new HasGroupRowMapper());
    }

    @Override
    public Optional<HasGroup> select(UUID id) {
        return this.jdbcTemplate.query("select id,name,description,type,region,position,beliefs from groups where id=?", new HasGroupRowMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<HasGroup> selectByName(String name) {
        return this.jdbcTemplate.query("select id,name,description,type,region,position,beliefs from groups where name=?", new HasGroupRowMapper(), name).stream().findFirst();
    }

    @Override
    public HasGroup insert(HasGroup hasGroup) {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(Objects.requireNonNull(jdbcTemplate.getDataSource())).withTableName("groups");
        final UUID uuid = UUID.randomUUID();

        String beliefs = "";
        String region = "";
        String position = "";

        switch (hasGroup){
            case  Religion r -> {
                beliefs = r.beliefs();
            }
            case Origin o -> {
                region = o.region();
            }
            case Politic p -> {
                position = p.position();
            }
        }

        final SqlParameterSource in = new MapSqlParameterSource()
                .addValue("id", uuid)
                .addValue("name", hasGroup.getGroup().name())
                .addValue("description", hasGroup.getGroup().description())
                .addValue("type", hasGroup.getGroup().type())
                .addValue("belief", beliefs)
                .addValue("region", region)
                .addValue("position", position);

        simpleJdbcInsert.execute(in);

        Group group = Group.GroupBuilder.aGroup().withId(uuid)
                .withName(hasGroup.getGroup().name())
                .withDescription(hasGroup.getGroup().description())
                .withType(hasGroup.getGroup().type())
                .build();

        switch (hasGroup.getGroup().type()){
            case "religion" -> {
                return Religion.ReligionBuilder.aReligion()
                        .withGroup(group)
                        .withBeliefs(beliefs)
                        .build();
            }
            case "origin" -> {
                return Origin.OriginBuilder.anOrigin()
                        .withGroup(group)
                        .withRegion(region)
                        .build();
            }
            case "politic" -> {
                return Politic.PoliticBuilder.aPolitic()
                        .withGroup(group)
                        .withPosition(position)
                        .build();
            }
        }
        return hasGroup;
    }

    @Override
    public HasGroup update(HasGroup hasGroup) {
        String beliefs = "";
        String region = "";
        String position = "";

        switch (hasGroup){
            case  Religion r -> {
                beliefs = r.beliefs();
            }
            case Origin o -> {
                region = o.region();
            }
            case Politic p -> {
                position = p.position();
            }
        }

        jdbcTemplate.update("update groups set name = ?, description = ?, type = ?, region = ?, position = ?,beliefs = ?  where id = ?", hasGroup.getGroup().name(), hasGroup.getGroup().description(), hasGroup.getGroup().type(), region, position, beliefs, hasGroup.getGroup().id());
        return hasGroup;
    }

    @Override
    public void delete(UUID uuid) {
        jdbcTemplate.update("delete from implication where group_id=?", uuid);
        jdbcTemplate.update("delete from groups where id=?", uuid);
    }
}
