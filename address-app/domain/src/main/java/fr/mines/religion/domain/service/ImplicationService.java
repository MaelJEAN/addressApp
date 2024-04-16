package fr.mines.religion.domain.service;

import fr.mines.religion.domain.model.Group;
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
        if (implication.group() == null) {
            throw new IllegalArgumentException("Group is required");
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
    public Collection<Group> getGroupsListByUserId(UUID userId) {
        List<Group> groupsList = new ArrayList<>();
        List<Implication> userImplications = implicationRepositoryPort
                .selectAll()
                .stream()
                .filter(implication -> implication.person().id().equals(userId))
                .toList();
        for(Implication implication : userImplications){
            groupsList.add(implication.group());
        }
        return groupsList;
    }

    @Override
    public Collection<Person> getPersonsListByGroupId(UUID groupId) {
        List<Person> personsList = new ArrayList<>();
        List<Implication> userImplications = implicationRepositoryPort
                .selectAll()
                .stream()
                .filter(implication -> implication.group().id().equals(groupId))
                .toList();
        for(Implication implication : userImplications){
            personsList.add(implication.person());
        }
        return personsList;
    }
}
