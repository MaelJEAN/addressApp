package fr.mines.religion.domain.service;

import fr.mines.religion.domain.model.Person;
import fr.mines.religion.port.driven.PersonRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {


    @Mock
    private PersonRepositoryPort personRepositoryPort;

    @InjectMocks
    private PersonService personService = new PersonService(personRepositoryPort);


    @Test
    void shouldSaveAPerson() {
        //GIVEN
        Person person = Person.PersonBuilder.aPerson().withFirstName("Gourep").withLastName("C'est le gourep").withAge(32).withGender("male").build();

        UUID id = UUID.randomUUID();
        when(personRepositoryPort.insert(any())).thenReturn(
                Person.PersonBuilder.aPerson().withId(id).build()
        );

        //WHEN
        Person saved = personService.save(person);

        //THEN
        assertEquals(id, saved.id());
    }


    @Test
    void shouldNotSavePersonWithEmptyName() {
        // GIVEN
        Person person = Person.PersonBuilder.aPerson().withFirstName("").withLastName("C'est le gourep").withAge(32).withGender("male").build();

        // WHEN
        assertThrows(IllegalArgumentException.class, () -> personService.save(person));
    }

    @Test
    void shouldNotSaveAnExistingPerson() {
        // GIVEN

        UUID id = UUID.randomUUID();
        when(personRepositoryPort.selectAll()).thenReturn(List.of(Person.PersonBuilder.aPerson().withFirstName("Gourep").withLastName("C'est le gourep").withAge(32).withGender("male").withId(id).build()));

        Person person = Person.PersonBuilder.aPerson().withFirstName("Gourep").withLastName("C'est le gourep").withAge(3).withGender("female").build();

        // WHEN
        assertThrows(IllegalArgumentException.class, () -> personService.save(person));
    }



    @Test
    void shouldRemoveAPerson() {
        // GIVEN
        UUID uuid = UUID.randomUUID();

        // WHEN
        personService.remove(uuid);

        // THEN
        verify(personRepositoryPort, times(1)).delete(uuid);
    }


    @Test
    void getPersonById() {
        UUID id = UUID.randomUUID();
        Person person = Person.PersonBuilder.aPerson().withFirstName("Gourep").withLastName("C'est le gourep").withAge(32).withGender("male").build();
        when(personRepositoryPort.select(id)).thenReturn(Optional.of(person));
        Optional<Optional<Person>> personById = personService.getPersonById(id);

        assertTrue(personById.isPresent());
        Optional<Person> returned = personById.get();

        assertEquals(person.lastName(), returned.get().lastName());
        assertEquals(person.firstName(), returned.get().firstName());
        assertEquals(person.age(), returned.get().age());
        assertEquals(person.gender(), returned.get().gender());
    }

    
}