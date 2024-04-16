package fr.mines.religion.port.driving;

import fr.mines.religion.domain.model.Group;
import fr.mines.religion.domain.model.HasGroup;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface HasGroupUseCase {
    HasGroup save(HasGroup group);

    Optional<Optional<HasGroup>> getGroupById(UUID uuid);

    Optional<Optional<HasGroup>> getGroupByName(String name);

    Collection<HasGroup> getList();

    void remove(UUID uuid);
}
