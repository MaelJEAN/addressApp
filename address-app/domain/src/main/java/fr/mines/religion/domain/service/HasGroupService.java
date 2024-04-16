package fr.mines.religion.domain.service;

import fr.mines.religion.domain.model.HasGroup;
import fr.mines.religion.port.driven.HasGroupRepositoryPort;
import fr.mines.religion.port.driving.HasGroupUseCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class HasGroupService implements HasGroupUseCase {

    @Autowired
    private HasGroupRepositoryPort hasGroupRepositoryPort;

    public HasGroupService(HasGroupRepositoryPort hasGroupRepositoryPort) {
        this.hasGroupRepositoryPort = hasGroupRepositoryPort;
    }

    @Override
    public HasGroup save(HasGroup group) {
        if (group.getGroup().name() == null || group.getGroup().name().isEmpty()) {
            throw new IllegalArgumentException("Group name is required");
        }
        if (group.getGroup().description() == null || group.getGroup().description().isEmpty()) {
            throw new IllegalArgumentException("Group description is required");
        }
        if (group.getGroup().type() != "origin" && group.getGroup().type() != "politic" && group.getGroup().type() != "religion") {
            throw new IllegalArgumentException("Group type is required");
        }

        if (hasGroupRepositoryPort
                .selectAll()
                .stream()
                .anyMatch(persisted -> persisted.getGroup().name().equalsIgnoreCase(group.getGroup().name()) && persisted.getGroup().id() != group.getGroup().id())
        ) {
            throw new IllegalArgumentException("Group name already exists");
        }

        if (group.getGroup().id() == null) {
            return hasGroupRepositoryPort.insert(group);
        } else {
            return hasGroupRepositoryPort.update(group);
        }
    }

    @Override
    public Optional<Optional<HasGroup>> getGroupById(UUID uuid) {
        return Optional.ofNullable(hasGroupRepositoryPort.select(uuid));
    }

    @Override
    public Optional<Optional<HasGroup>> getGroupByName(String name) {
        return Optional.ofNullable(hasGroupRepositoryPort.selectByName(name));
    }

    @Override
    public Collection<HasGroup> getList() {
        return hasGroupRepositoryPort.selectAll();
    }

    @Override
    public void remove(UUID uuid) {
        hasGroupRepositoryPort.delete(uuid);
    }
}
