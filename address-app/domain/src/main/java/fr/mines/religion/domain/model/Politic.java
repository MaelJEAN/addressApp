package fr.mines.religion.domain.model;

public record Politic(String position, Group group) implements HasGroup {
    @Override
    public Group getGroup() {
        return group;
    }

    public static final class PoliticBuilder {
        private String position;
        private Group group;

        private PoliticBuilder() {
        }

        public static PoliticBuilder aPolitic() {
            return new PoliticBuilder();
        }

        public PoliticBuilder withPosition(String position) {
            this.position = position;
            return this;
        }

        public PoliticBuilder withGroup(Group group) {
            this.group = group;
            return this;
        }

        public Politic build() {
            return new Politic(position, group);
        }
    }
}
