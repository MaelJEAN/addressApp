package fr.mines.religion.domain.model;

public record Religion(String beliefs, Group group) implements HasGroup{

    @Override
    public Group getGroup() {
        return group;
    }

    public static final class ReligionBuilder {
        private String beliefs;
        private Group group;

        private ReligionBuilder() {
        }

        public static ReligionBuilder aReligion() {
            return new ReligionBuilder();
        }

        public ReligionBuilder withBeliefs(String beliefs) {
            this.beliefs = beliefs;
            return this;
        }

        public ReligionBuilder withGroup(Group group) {
            this.group = group;
            return this;
        }

        public Religion build() {
            return new Religion(beliefs, group);
        }
    }
}
