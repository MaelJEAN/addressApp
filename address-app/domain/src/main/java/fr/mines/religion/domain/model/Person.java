package fr.mines.religion.domain.model;

import java.util.UUID;

public record Person(UUID id, String firstName, String lastName, Integer age, String gender) {

    public static final class PersonBuilder {
        private UUID id;
        private String firstName;
        private String lastName;
        private Integer age;
        private String gender;

        private PersonBuilder() {
        }

        public static PersonBuilder aPerson() {
            return new PersonBuilder();
        }

        public PersonBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public PersonBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder withAge(Integer age) {
            this.age = age;
            return this;
        }

        public PersonBuilder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Person build() {
            return new Person(id, firstName, lastName, age, gender);
        }
    }
}
