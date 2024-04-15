package fr.mines.religion.domain.service;

import fr.mines.religion.domain.model.Group;
import fr.mines.religion.port.driving.GroupUseCase;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class GroupService implements GroupUseCase {

    @Override
    public Collection<Group> selectAll() {
        return List.of();
    }

    @Override
    public Group select(UUID id) {
        return null;
    }

    @Override
    public Group insert(Group group) {
        return null;
    }

    @Override
    public Group update(Group group) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }
}
