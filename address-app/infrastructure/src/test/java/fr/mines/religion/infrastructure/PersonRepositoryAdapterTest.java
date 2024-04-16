package fr.mines.religion.infrastructure;

import fr.mines.religion.domain.model.Person;
import fr.mines.religion.infrastructure.config.PersistenceTestConfig;
import fr.mines.religion.port.driven.PersonRepositoryPort;
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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners({SqlScriptsTestExecutionListener.class, TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(classes = {PersistenceTestConfig.class})
@Sql(scripts = {"/data/clear.sql", "/data/person.sql"})
class PersonRepositoryAdapterTest {
    @Autowired
    private PersonRepositoryPort personRepositoryPort;

    @Test
    void shouldFindAll() {
    }

    @Test
    void selectByGender() {
    }

    @Test
    void selectByAge() {
    }

    @Test
    void select() {
    }

    @Test
    public void shouldInsertAPerson() {
        // GIVEN
        UUID id = UUID.fromString("fb74d086-5a4a-11e7-907b-a6006ad3dba0");
        Person person = Person.PersonBuilder.aPerson()
                .withId(id)
                .withFirstName("Mael")
                .withLastName("Jean")
                .withAge(22)
                .withGender("male")
                .build();

        // WHEN
        Person persisted = personRepositoryPort.insert(person);

        // THEN
        assertNotNull(persisted.id());
        assertEquals("Mael", persisted.firstName());
        assertEquals("Jean", persisted.lastName());
        assertEquals(22, persisted.age());
        assertEquals("male", persisted.gender());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}