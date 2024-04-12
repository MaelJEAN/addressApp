package fr.mines.religion.domain.model;

public record Person(String firstName, String lastName, int age, String gender) {

    public static final class PersonBuilder {
        private String firstName;
        private String lastName;
        private int age;
        private String gender;

        private PersonBuilder() {
        }

        public static PersonBuilder aPerson() {
            return new PersonBuilder();
        }

        public PersonBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Person build() {
            return new Person(firstName, lastName, age, gender);
        }
    }
}
