package fr.mines.religion.port.driving;

import fr.mines.religion.domain.model.Implication;

import java.util.Collection;
import java.util.UUID;

public interface ImplicationUseCase {
    Collection<String> listGroupNames(UUID userId);
    Collection<String> listUsersByGroup(UUID groupId);

    Collection<Implication> selectAll();

    Implication select(UUID id);

    Implication insert(Implication implication);

    Implication update(Implication implication);

    void delete(UUID uuid);
}
