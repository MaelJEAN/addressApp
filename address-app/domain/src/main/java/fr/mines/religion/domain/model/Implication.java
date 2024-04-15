package fr.mines.religion.domain.model;

import java.util.UUID;

public record Implication(UUID id, String status, Group group, Person person) {

    public static final class ImplicationBuilder {
        private UUID id;
        private String status;
        private Group group;
        private Person person;

        private ImplicationBuilder() {
        }

        public static ImplicationBuilder anImplication() {
            return new ImplicationBuilder();
        }

        public ImplicationBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public ImplicationBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public ImplicationBuilder withGroup(Group group) {
            this.group = group;
            return this;
        }

        public ImplicationBuilder withPerson(Person person) {
            this.person = person;
            return this;
        }

        public Implication build() {
            return new Implication(id, status, group, person);
        }
    }
}
