package Mechanics.Equipment.Armor;

public enum ArmorType {
    LIGHT("light"), MEDIUM("medium"), HEAVY("heavy");

    private String identifier;

    ArmorType(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static ArmorType fromString(String identifier) {
        for (ArmorType at : ArmorType.values()) {
            if (at.getIdentifier().equalsIgnoreCase(identifier)) {
                return at;
            }
        }
        throw new IllegalArgumentException("No ArmorType found for " + identifier);
    }
}
