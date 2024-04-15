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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImplicationServiceTest {

    @Mock
    private ImplicationRepositoryPort implicationRepositoryPort;

    @InjectMocks
    private ImplicationService implicationService = new ImplicationService(implicationRepositoryPort);

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
    }

    @Test
    void getImplicationById() {
        //GIVEN

    }

    @Test
    void getGroupsListByUserId() {
    }

    @Test
    void getPersonsListByGroupId() {
    }
}