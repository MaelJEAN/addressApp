package org.mines.address.web.controller;

import fr.mines.religion.port.driving.PersonUseCase;
import org.junit.jupiter.api.Test;
import fr.mines.religion.domain.model.Person;
import org.mines.address.web.config.WebTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = PersonController.class)
@AutoConfigureMockMvc
@Import(WebTestConfig.class)
class PersonControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonUseCase personUseCase;

    @Test
    void createPerson() {
    }

    @Test
    void deletePerson() {
    }

    @Test
    void getPerson() {
    }

    @Test
    void listPersons() {
    }

    @Test
    void updatePerson() {
    }

    @Test
    void shouldGetAPerson() throws Exception {
        // Given
        UUID uuid = UUID.randomUUID();

        // When
        when(personUseCase.getPersonById(uuid)).thenReturn(Optional.of(
                Person.PersonBuilder.aPerson()
                        .withId(uuid)
                        .withLastName("somrson")
                        .withFirstName("soperson")
                        .withAge(12)
                        .withGender("female")
                        .build()
        ));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/person/%s".formatted(uuid.toString()))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("soperson"));

    }


    @Test
    void shouldNotGetInstanceData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/person/%s".formatted(UUID.randomUUID().toString()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}