package fr.mines.religion.domain.model;

import java.util.UUID;

public record Group(UUID id, String name, String description, String type) {

    public static final class GroupBuilder {
        private UUID id;
        private String name;
        private String description;
        private String type;

        private GroupBuilder() {
        }

        public static GroupBuilder aGroup() {
            return new GroupBuilder();
        }

        public GroupBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public GroupBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public GroupBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public GroupBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public Group build() {
            return new Group(id, name, description, type);
        }
    }
}
