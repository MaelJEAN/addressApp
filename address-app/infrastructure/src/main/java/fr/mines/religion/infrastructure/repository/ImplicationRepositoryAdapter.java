package fr.mines.religion.infrastructure.repository;

import fr.mines.religion.domain.model.Implication;
import fr.mines.religion.port.driven.ImplicationRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ImplicationRepositoryAdapter implements ImplicationRepositoryPort {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ImplicationRepositoryAdapter(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public Collection<Implication> selectAll() {
        return jdbcTemplate.query("select i.id as 'implicationId', i.group_id, i.person_id, i.status, " +
                "g.id as 'groupId', g.name, g.description, g.type, g.region, g.position, g.beliefs, " +
                "p.id as 'personId', p.firstName, p.lastName, p.age, p.gender " +
                "from implication i " +
                "join groups g on i.group_id = g.id " +
                "join person p on i.person_id = p.id", new ImplicationRowMapper());
    }

    @Override
    public Optional<Implication> select(UUID id) {
        return this.jdbcTemplate.query(
                "select i.id as 'implicationId', i.group_id, i.person_id, i.status, " +
                "g.id as 'groupId', g.name, g.description, g.type, g.region, g.position, g.beliefs, " +
                "p.id as 'personId', p.firstName, p.lastName, p.age, p.gender " +
                "from implication i " +
                "join groups g on i.group_id = g.id " +
                "join person p on i.person_id = p.id " +
                "where i.id=?", new ImplicationRowMapper(), id).stream().findFirst();
    }

    @Override
    public Implication insert(Implication implication) {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(Objects.requireNonNull(jdbcTemplate.getDataSource())).withTableName("implication");
        final UUID uuid = UUID.randomUUID();

        final SqlParameterSource in = new MapSqlParameterSource()
                .addValue("id", uuid)
                .addValue("group_id", implication.hasGroup().getGroup().id())
                .addValue("person_id", implication.person().id())
                .addValue("status", implication.status());
        simpleJdbcInsert.execute(in);

        return Implication.ImplicationBuilder.anImplication()
                .withId(uuid)
                .withHasGroup(implication.hasGroup())
                .withPerson(implication.person())
                .withStatus(implication.status())
                .build();
    }

    @Override
    public Implication update(Implication implication) {
        jdbcTemplate.update("update implication set group_id = ?, person_id = ?, status = ? where id = ?", implication.hasGroup().getGroup().id(), implication.person().id(), implication.status(), implication.id());
        return implication;
    }

    @Override
    public void delete(UUID uuid) {
        jdbcTemplate.update("delete from implication where id=?", uuid);
    }
}
