package fr.mines.religion.domain.service;

import fr.mines.religion.domain.model.Group;
import fr.mines.religion.domain.model.Implication;
import fr.mines.religion.domain.model.Person;
import fr.mines.religion.port.driven.ImplicationRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImplicationServiceTest {

    @Mock
    private ImplicationRepositoryPort implicationRepositoryPort;

    @InjectMocks
    private ImplicationService implicationService = new ImplicationService(implicationRepositoryPort);

    private final Person person = Person.PersonBuilder.aPerson().withFirstName("Mael").withLastName("Jean").withAge(22).build();
    private final Group group = Group.GroupBuilder.aGroup().withName("Gourep").withDescription("C'est le gourep").build();

    @Test
    void shouldSaveAnImplication() {
        //GIVEN
        Implication implication = Implication.ImplicationBuilder.anImplication().withGroup(group).withPerson(person).withStatus("OK").build();

        UUID id = UUID.randomUUID();
        when(implicationRepositoryPort.insert(any())).thenReturn(
                Implication.ImplicationBuilder.anImplication().withId(id).build()
        );

        //WHEN
        Implication saved = implicationService.save(implication);

        //THEN
        assertEquals(id, saved.id());

        verifyNoMoreInteractions(implicationRepositoryPort);
    }

    @Test
    void shouldNotSaveAnImplicationWithNullPerson() {
        //GIVEN
        Implication implication = Implication.ImplicationBuilder.anImplication().withPerson(null).withGroup(group).withStatus("OK").build();

        // WHEN
        assertThrows(IllegalArgumentException.class, () -> implicationService.save(implication));

        verifyNoInteractions(implicationRepositoryPort);
    }

    @Test
    void getImplicationById() {
        UUID id = UUID.randomUUID();
        Implication implication = Implication.ImplicationBuilder.anImplication().withGroup(group).withPerson(person).withStatus("OK").build();
        when(implicationRepositoryPort.select(id)).thenReturn(Optional.of(implication));
        Optional<Implication> implicationById = implicationService.getImplicationById(id);

        assertTrue(implicationById.isPresent());
        Implication returned = implicationById.get();

        assertEquals(implication.group(), returned.group());
    }

    @Test
    void getGroupsListByUserId() {
    }

    @Test
    void getPersonsListByGroupId() {
    }
}