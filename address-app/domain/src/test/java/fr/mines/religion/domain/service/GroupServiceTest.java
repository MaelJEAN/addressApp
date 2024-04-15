package fr.mines.religion.domain.service;

import fr.mines.religion.domain.model.Group;
import fr.mines.religion.domain.model.Implication;
import fr.mines.religion.domain.model.Person;
import fr.mines.religion.port.driven.GroupRepositoryPort;
import fr.mines.religion.port.driven.ImplicationRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mines.address.domain.model.Town;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    @Mock
    private GroupRepositoryPort groupRepositoryPort;

    @InjectMocks
    private GroupService groupService = new GroupService(groupRepositoryPort);


    @Test
    void shouldSaveAGroup() {
        //GIVEN
        Group group = Group.GroupBuilder.aGroup().withName("Gourep").withDescription("C'est le gourep").build();

        UUID id = UUID.randomUUID();
        when(groupRepositoryPort.insert(any())).thenReturn(
                Group.GroupBuilder.aGroup().withId(id).build()
        );

        //WHEN
        Group saved = groupService.save(group);

        //THEN
        assertEquals(id, saved.id());
    }


    @Test
    void shouldNotSaveGroupWithEmptyName() {
        // GIVEN
        Group group = Group.GroupBuilder.aGroup().withName("").withDescription("C'est le gourep").build();

        // WHEN
        assertThrows(IllegalArgumentException.class, () -> groupService.save(group));
    }

    @Test
    void shouldNotSaveAnExistingGroup() {
        // GIVEN

        UUID id = UUID.randomUUID();
        when(groupRepositoryPort.selectAll()).thenReturn(List.of(Group.GroupBuilder.aGroup().withName("alreadyPresent").withDescription("C'est le gourep").withId(id).build()));

        Group group = Group.GroupBuilder.aGroup().withName("alreadyPresent").withDescription("C'est le gourep").build();

        // WHEN
        assertThrows(IllegalArgumentException.class, () -> groupService.save(group));
    }



    @Test
    void shouldRemoveAGroup() {
        // GIVEN
        UUID uuid = UUID.randomUUID();

        // WHEN
        groupService.remove(uuid);

        // THEN
        verify(groupRepositoryPort, times(1)).delete(uuid);
    }



}