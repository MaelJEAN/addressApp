package fr.mines.religion.domain.model;

public sealed interface HasGroup permits Origin, Politic, Religion {

    Group getGroup();
}
