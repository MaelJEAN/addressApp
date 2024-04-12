package fr.mines.religion.domain.model;

public record Origin(String region) {

    public static final class OriginBuilder {
        private String region;

        private OriginBuilder() {
        }

        public static OriginBuilder anOrigin() {
            return new OriginBuilder();
        }

        public OriginBuilder withRegion(String region) {
            this.region = region;
            return this;
        }

        public Origin build() {
            return new Origin(region);
        }
    }
}
