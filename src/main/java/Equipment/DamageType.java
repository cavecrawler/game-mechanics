package Equipment;

public enum DamageType {
    BLUNT("blunt"), STAB("stab"), SLASH("slash"), BURN("burn"), FREEZE("freeze");

    private String identifier;

    DamageType(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static DamageType fromString(String identifier) {
        for (DamageType dt : DamageType.values()) {
            if (dt.getIdentifier().equalsIgnoreCase(identifier)) {
                return dt;
            }
        }
        throw new IllegalArgumentException("No DamageType " + identifier + " found!");
    }
}
