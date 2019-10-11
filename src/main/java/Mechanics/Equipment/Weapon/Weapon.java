package Mechanics.Equipment.Weapon;

import Mechanics.Damage;
import Mechanics.DamageType;

import java.util.HashMap;
import java.util.Map;

public class Weapon {

    private WeaponType type;
    private WeaponSlot slot;
    private String name;
    private Map<DamageType, Integer> damages;
    private Map<DamageType, Integer> protections;
    private float weaponSpeed;

    public Weapon(WeaponType type, WeaponSlot slot) {
        this.type = type;
        this.slot = slot;
        damages = new HashMap<>();
        protections = new HashMap<>();
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

    public void addDamage(Damage damage){
        int currentDamageValue = damages.getOrDefault(damage.getDamageType(),0);
        damages.put(damage.getDamageType(), damage.getValue() + currentDamageValue);
    }

    public void addProtection(Damage protection){
        int currentProtectionValue = protections.getOrDefault(protection.getDamageType(),0);
        protections.put(protection.getDamageType(), protection.getValue() + currentProtectionValue);
    }

    public Map<DamageType, Integer> getDamages() {
        return damages;
    }

    public Map<DamageType, Integer> getProtections() {
        return protections;
    }

    public void setWeaponSpeed(float weaponSpeed) {
        this.weaponSpeed = weaponSpeed;
    }

    public float getWeaponSpeed() {
        return weaponSpeed;
    }
}
