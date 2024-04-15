package fr.mines.religion.domain.model;

public record Origin(String region, Group group) implements HasGroup {

    @Override
    public Group getGroup() {
        return group;
    }

    public static final class OriginBuilder {
        private String region;
        private Group group;

        private OriginBuilder() {
        }

        public static OriginBuilder anOrigin() {
            return new OriginBuilder();
        }

        public OriginBuilder withRegion(String region) {
            this.region = region;
            return this;
        }

        public Origin.OriginBuilder withGroup(Group group) {
            this.group = group;
            return this;
        }

        public Origin build() {
            return new Origin(region, group);
        }
    }
}
