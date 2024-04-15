package fr.mines.religion.port.driving;

import fr.mines.religion.domain.model.Implication;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface ImplicationUseCase {
    Implication save(Implication implication);
    Optional<Implication> getImplicationById(UUID id);
    
    Collection<String> getGroupsListByUserId(UUID userId);
    Collection<String> getPersonsListByGroupId(UUID groupId);
}
