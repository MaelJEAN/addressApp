package fr.mines.religion.domain.service;

import fr.mines.religion.domain.model.Implication;
import fr.mines.religion.port.driving.ImplicationUseCase;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class ImplicationService implements ImplicationUseCase {
    @Override
    public Collection<String> listGroupNames(UUID userId) {
        return List.of();
    }

    @Override
    public Collection<String> listUsersByGroup(UUID groupId) {
        return List.of();
    }

    @Override
    public Collection<Implication> selectAll() {
        return List.of();
    }

    @Override
    public Implication select(UUID id) {
        return null;
    }

    @Override
    public Implication insert(Implication Implication) {
        return null;
    }

    @Override
    public Implication update(Implication Implication) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }
}
