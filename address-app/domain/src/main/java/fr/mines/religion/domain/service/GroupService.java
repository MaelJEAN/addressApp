package fr.mines.religion.domain.service;

import fr.mines.religion.domain.model.Group;
import fr.mines.religion.port.driven.GroupRepositoryPort;
import fr.mines.religion.port.driven.GroupRepositoryPort;
import fr.mines.religion.port.driving.GroupUseCase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GroupService implements GroupUseCase {

    @Autowired
    private GroupRepositoryPort groupRepositoryPort;

    public GroupService(GroupRepositoryPort groupRepositoryPort) {
        this.groupRepositoryPort = groupRepositoryPort;
    }

    @Override
    public Group save(Group group) {
        if (group.name() == null || group.name().isEmpty()) {
            throw new IllegalArgumentException("Group name is required");
        }
        if (group.description() == null || group.description().isEmpty()) {
            throw new IllegalArgumentException("Group description is required");
        }

        if (groupRepositoryPort
                .selectAll()
                .stream()
                .anyMatch(persisted -> persisted.name().equalsIgnoreCase(group.name()) && persisted.id() != group.id())
        ) {
            throw new IllegalArgumentException("Group name already exists");
        }

        if (group.id() == null) {
            return groupRepositoryPort.insert(group);
        } else {
            return groupRepositoryPort.update(group);
        }
    }

    @Override
    public Optional<Group> getGroupById(UUID uuid) {
        return Optional.ofNullable(groupRepositoryPort.select(uuid));
    }

    @Override
    public Optional<Group> getGroupByName(String name) {
        return Optional.ofNullable(groupRepositoryPort.selectByName(name));
    }

    @Override
    public Collection<Group> getList() {
        return groupRepositoryPort.selectAll();
    }

    @Override
    public void remove(UUID uuid) {
        groupRepositoryPort.delete(uuid);
    }
}
