package fr.mines.religion.domain.model;

import java.util.UUID;

public record Person(UUID uuid, String firstName, String lastName, Origin origin, Politic politic, Religion religion) {
}
