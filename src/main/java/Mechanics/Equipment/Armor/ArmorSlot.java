package Mechanics.Equipment.Armor;

public enum ArmorSlot {
    HEAD("head"), SHOULDER("shoulder"), CHEST("chest"), HAND("hand"), LEG("leg"), FEET("feet");

    private String identifier;

    ArmorSlot(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static ArmorSlot fromString(String identifier) {
        for (ArmorSlot as : ArmorSlot.values()) {
            if (as.getIdentifier().equalsIgnoreCase(identifier)) {
                return as;
            }
        }
        throw new IllegalArgumentException("No ArmorSlot found for " + identifier);
    }
}
