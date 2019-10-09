package Mechanics;

public class Protection {

    private DamageType damageType;
    private int value;

    public Protection(DamageType damageType, int value) {
        this.damageType = damageType;
        this.value = value;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public int getValue() {
        return value;
    }
}
