package fr.mines.religion.domain.service;

import fr.mines.religion.domain.model.HasGroup;
import fr.mines.religion.domain.model.Implication;
import fr.mines.religion.domain.model.Person;
import fr.mines.religion.port.driven.ImplicationRepositoryPort;
import fr.mines.religion.port.driving.ImplicationUseCase;
import org.mines.address.port.driven.TownRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class ImplicationService implements ImplicationUseCase {
    @Autowired
    private ImplicationRepositoryPort implicationRepositoryPort;

    public ImplicationService(ImplicationRepositoryPort implicationRepositoryPort) { this.implicationRepositoryPort = implicationRepositoryPort; }

    @Override
    public Implication save(Implication implication) {
        if (implication.hasGroup() == null) {
            throw new IllegalArgumentException("HasGroup is required");
        }
        if (implication.person() == null) {
            throw new IllegalArgumentException("Person is required");
        }
        if (implication.status() == null) {
            throw new IllegalArgumentException("Implication's status is required");
        }

        if (implication.id() == null) {
            return implicationRepositoryPort.insert(implication);
        } else {
            return implicationRepositoryPort.update(implication);
        }
    }

    @Override
    public Optional<Implication> getImplicationById(UUID id) {
        return implicationRepositoryPort.select(id);
    }

    @Override
    public Collection<HasGroup> getHasGroupsListByUserId(UUID userId) {
        List<HasGroup> hasGroupsList = new ArrayList<>();
        List<Implication> userImplications = implicationRepositoryPort
                .selectAll()
                .stream()
                .filter(implication -> implication.person().id().equals(userId))
                .toList();
        for(Implication implication : userImplications){
            hasGroupsList.add(implication.hasGroup());
        }
        return hasGroupsList;
    }

    @Override
    public Collection<Person> getPersonsListByHasGroupId(UUID hasGroupId) {
        List<Person> personsList = new ArrayList<>();
        List<Implication> userImplications = implicationRepositoryPort
                .selectAll()
                .stream()
                .filter(implication -> implication.hasGroup().getGroup().id().equals(hasGroupId))
                .toList();
        for(Implication implication : userImplications){
            personsList.add(implication.person());
        }
        return personsList;
    }
}
