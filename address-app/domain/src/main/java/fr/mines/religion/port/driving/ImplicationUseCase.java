package fr.mines.religion.port.driving;

import fr.mines.religion.domain.model.HasGroup;
import fr.mines.religion.domain.model.Implication;
import fr.mines.religion.domain.model.Person;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface ImplicationUseCase {
    Implication save(Implication implication);
    Optional<Implication> getImplicationById(UUID id);
    
    Collection<HasGroup> getHasGroupsListByUserId(UUID userId);
    Collection<Person> getPersonsListByHasGroupId(UUID hasGroupId);
}
