package Mechanics.Skills;

public enum SkillType {
    BASIC("all"), MAGE("mage"), WARRIOR("warrior"), ROGUE("rogue");

    private String identifier;

    SkillType(String identifier) { this.identifier = identifier; };

    public String getIdentifier() { return identifier; };

    public static SkillType fromString(String identifier) {
        for (SkillType st : SkillType.values()) {
            if (st.getIdentifier().equalsIgnoreCase(identifier)) {
                return st;
            }
        }
        throw new IllegalArgumentException("No SkillType found for " + identifier);
    }
}
