package fr.mines.religion.port.driven;

import fr.mines.religion.domain.model.Implication;

import java.util.Collection;
import java.util.UUID;

public interface ImplicationRepositoryPort {
    Collection<Implication> selectAll();

    Implication select(UUID id);

    Implication insert(Implication implication);

    Implication update(Implication implication);

    void delete(UUID uuid);
}
