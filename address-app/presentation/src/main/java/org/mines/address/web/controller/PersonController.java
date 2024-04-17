package org.mines.address.web.controller;

import org.mines.address.api.controller.PersonApi;
import org.mines.address.api.model.Person;
import fr.mines.religion.port.driving.PersonUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class PersonController implements PersonApi {
    private final PersonUseCase personUseCase;

    @Autowired
    public PersonController(PersonUseCase personUseCase) {
        this.personUseCase = personUseCase;
    }

    @Override
    public ResponseEntity<Person> createPerson(Person person) {
        fr.mines.religion.domain.model.Person saved = personUseCase.save(this.mapApiToDomain(person));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(saved.id()).toUri();

        return ResponseEntity.created(uri).body(mapDomainToApi(saved));
    }

    @Override
    public ResponseEntity<String> deletePerson(String id) {
        personUseCase.remove(UUID.fromString(id));

        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<Person> getPerson(String id) {
        UUID uuid = UUID.fromString(id);

        return personUseCase.getPersonById(uuid)
                .map(this::mapDomainToApi)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Person>> listPersons() {
        return ResponseEntity.ok(personUseCase.getList().stream().map(this::mapDomainToApi).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Person> updatePerson(Person person) {
        fr.mines.religion.domain.model.Person modelPerson = this.mapApiToDomain(person);

        if (personUseCase.getPersonById(modelPerson.id()).isPresent()) {
            return ResponseEntity.ok(this.mapDomainToApi(personUseCase.save(modelPerson)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Map from person API to person domain
    private fr.mines.religion.domain.model.Person mapApiToDomain(Person person) {
        return fr.mines.religion.domain.model.Person.PersonBuilder.aPerson()
                .withId(person.getId() == null ? null : UUID.fromString(person.getId()))
                .withFirstName(person.getFirstName())
                .withLastName(person.getLastName())
                .withAge(person.getAge())
                .withGender(person.getGender())
                .build();
    }

    // Map from person domain to person API
    private Person mapDomainToApi(fr.mines.religion.domain.model.Person person) {
        Person apiPerson = new Person();
        apiPerson.setId(person.id().toString());
        apiPerson.setFirstName(person.firstName());
        apiPerson.setLastName(person.lastName());
        apiPerson.setAge(person.age());
        apiPerson.setGender(person.gender());

        return apiPerson;
    }

}
