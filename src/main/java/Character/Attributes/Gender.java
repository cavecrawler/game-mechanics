package Character.Attributes;

public enum Gender {
    MALE("male"), FEMALE("female");

    private String identifier;

    Gender(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static Gender fromString(String identifier) {
        for (Gender g : Gender.values()) {
            if (g.getIdentifier().equalsIgnoreCase(identifier)) {
                return g;
            }
        }
        throw new IllegalArgumentException("Wrong Gender identifier given.");
    }
}
