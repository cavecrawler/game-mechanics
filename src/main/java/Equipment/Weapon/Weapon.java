package Equipment.Weapon;

public class Weapon {

    private WeaponType type;
    private WeaponSlot slot;
    private String name;

    public Weapon(WeaponType type, WeaponSlot slot) {
        this.type = type;
        this.slot = slot;
    }

    public WeaponType getType() {
        return type;
    }

    public WeaponSlot getSlot() {
        return slot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
