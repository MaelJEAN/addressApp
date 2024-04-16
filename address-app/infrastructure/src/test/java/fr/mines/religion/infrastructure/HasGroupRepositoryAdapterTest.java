package fr.mines.religion.infrastructure;

import fr.mines.religion.domain.model.HasGroup;
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
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    public void shouldFindAll() {
        Collection<HasGroup> all = hasGroupRepository.selectAll();

        assertEquals(1, all.size());
    }
}