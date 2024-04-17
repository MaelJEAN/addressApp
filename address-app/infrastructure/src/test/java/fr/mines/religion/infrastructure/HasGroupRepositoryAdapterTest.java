package fr.mines.religion.infrastructure;

import fr.mines.religion.domain.model.Group;
import fr.mines.religion.domain.model.HasGroup;
import fr.mines.religion.domain.model.Religion;
import fr.mines.religion.port.driven.HasGroupRepositoryPort;
import fr.mines.religion.infrastructure.config.PersistenceTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners({SqlScriptsTestExecutionListener.class, TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(classes = {PersistenceTestConfig.class})
@Sql(scripts = {"/data/clear.sql", "/data/groups.sql"})
class HasGroupRepositoryAdapterTest {

    @Autowired
    private HasGroupRepositoryPort hasGroupRepository;

    @Test
    void selectAll() {
    }

    @Test
    void select() {
    }

    @Test
    void selectByName() {
    }

    @Test
    void insert() {
        Group group = Group.GroupBuilder.aGroup().withId(UUID.fromString("77643c98-d221-4f34-b11c-3732405cd83a"))
                .withName("newname")
                .withDescription("desc")
                .withType("religion")
                .build();

        Religion rel = Religion.ReligionBuilder.aReligion()
                        .withGroup(group)
                        .withBeliefs("beliefs")
                        .build();
        hasGroupRepository.insert(rel);

        Collection<HasGroup> all = hasGroupRepository.selectAll();
        assertEquals(2, all.size());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        Collection<HasGroup> all = hasGroupRepository.selectAll();
        Integer size = all.size();

        hasGroupRepository.delete(UUID.fromString("77643c27-d221-4f34-b11c-3732405cd83a"));
        Collection<HasGroup> allbis = hasGroupRepository.selectAll();

        assertEquals(size - 1, allbis.size());
    }

    @Test
    public void shouldFindAll() {
        Collection<HasGroup> all = hasGroupRepository.selectAll();

        assertEquals(1, all.size());
    }
}