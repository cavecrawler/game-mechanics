package Equipment.Armor;

public class Armor {

    private ArmorType type;
    private ArmorSlot slot;
    private String name;

    public Armor(ArmorType type, ArmorSlot slot) {
        this.type = type;
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArmorType getType() {
        return type;
    }

    public ArmorSlot getSlot() {
        return slot;
    }
}
