package Mechanics.Equipment.Weapon;

public enum WeaponType {
    SHIELD ("shield"), DAGGER("dagger"), SWORD("sword"), MACE("mace"), POLE_ARM("pole-arm"), STAFF("staff"), WAND("wand");

    private String identifier;

    WeaponType(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static WeaponType fromString(String identifier) {
        for (WeaponType wt : WeaponType.values()) {
            if (wt.getIdentifier().equalsIgnoreCase(identifier)) {
                return wt;
            }
        }
        throw new IllegalArgumentException("No WeaponType found for " + identifier);
    }
}
