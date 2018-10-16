package Equipment.Weapon;

import Equipment.DamageType;

public class Damage {

    private DamageType damageType;
    private int value;

    public Damage(DamageType damageType, int value) {
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
