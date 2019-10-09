package Mechanics.Equipment.Weapon;

public enum WeaponSlot {
    ONE_HAND("one-hand"), TWO_HAND("two-hand"), MAIN_HAND("main-hand"), OFF_HAND("off-hand");

    private String identifier;

    WeaponSlot(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static WeaponSlot fromString(String identifier) {
        for (WeaponSlot ws : WeaponSlot.values()) {
            if (ws.getIdentifier().equalsIgnoreCase(identifier)) {
                return ws;
            }
        }
        throw new IllegalArgumentException("No WeaponSlot found for " + identifier);
    }
}
