package fr.mines.religion.domain.model;

import java.util.UUID;

public record Implication(UUID id, String status, HasGroup hasGroup, Person person) {

    public static final class ImplicationBuilder {
        private UUID id;
        private String status;
        private HasGroup hasGroup;
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

        public ImplicationBuilder withHasGroup(HasGroup hasGroup) {
            this.hasGroup = hasGroup;
            return this;
        }

        public ImplicationBuilder withPerson(Person person) {
            this.person = person;
            return this;
        }

        public Implication build() {
            return new Implication(id, status, hasGroup, person);
        }
    }
}
