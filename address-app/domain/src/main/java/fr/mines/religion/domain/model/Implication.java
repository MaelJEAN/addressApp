package fr.mines.religion.domain.model;

public record Implication(String status, Group group, Person person) {

    public static final class ImplicationBuilder {
        private String status;
        private Group group;
        private Person person;

        private ImplicationBuilder() {
        }

        public static ImplicationBuilder anImplication() {
            return new ImplicationBuilder();
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
            return new Implication(status, group, person);
        }
    }
}
