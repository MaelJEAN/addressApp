package fr.mines.religion.domain.model;

public record Religion(String truc, Group group) implements HasGroup{

    @Override
    public Group getGroup() {
        return group;
    }

    public static final class ReligionBuilder {
        private String truc;
        private Group group;

        private ReligionBuilder() {
        }

        public static ReligionBuilder aReligion() {
            return new ReligionBuilder();
        }

        public ReligionBuilder withTruc(String truc) {
            this.truc = truc;
            return this;
        }

        public ReligionBuilder withGroup(Group group) {
            this.group = group;
            return this;
        }

        public Religion build() {
            return new Religion(truc, group);
        }
    }
}
