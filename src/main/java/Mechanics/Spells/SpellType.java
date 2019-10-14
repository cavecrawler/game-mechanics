package Mechanics.Spells;

public enum SpellType {
    BASIC("all"), MAGE("mage"), WARRIOR("warrior"), ROGUE("rogue");

    private String identifier;

    SpellType(String identifier) { this.identifier = identifier; };

    public String getIdentifier() { return identifier; };

    public static SpellType fromString(String identifier) {
        for (SpellType st : SpellType.values()) {
            if (st.getIdentifier().equalsIgnoreCase(identifier)) {
                return st;
            }
        }
        throw new IllegalArgumentException("No SpellType found for " + identifier);
    }
}
