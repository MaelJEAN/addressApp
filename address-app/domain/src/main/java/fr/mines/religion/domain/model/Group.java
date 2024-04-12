package fr.mines.religion.domain.model;

public record Group(String name, String description) {

    public static final class GroupBuilder {
        private String name;
        private String description;

        private GroupBuilder() {
        }

        public static GroupBuilder aGroup() {
            return new GroupBuilder();
        }

        public GroupBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public GroupBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Group build() {
            return new Group(name, description);
        }
    }
}
