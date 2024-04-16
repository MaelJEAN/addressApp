package fr.mines.religion.port.driving;

import fr.mines.religion.domain.model.Group;
import fr.mines.religion.domain.model.Implication;
import fr.mines.religion.domain.model.Person;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface ImplicationUseCase {
    Implication save(Implication implication);
    Optional<Implication> getImplicationById(UUID id);
    
    Collection<Group> getGroupsListByUserId(UUID userId);
    Collection<Person> getPersonsListByGroupId(UUID groupId);
}
