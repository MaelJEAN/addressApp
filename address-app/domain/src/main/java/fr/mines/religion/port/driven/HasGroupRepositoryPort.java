package fr.mines.religion.port.driven;

import fr.mines.religion.domain.model.Group;
import fr.mines.religion.domain.model.HasGroup;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface HasGroupRepositoryPort {
    Collection<HasGroup> selectAll();

    Optional<HasGroup> select(UUID id);

    Optional<HasGroup> selectByName(String name);

    HasGroup insert(HasGroup group);

    HasGroup update(HasGroup group);

    void delete(UUID uuid);
}
