package Mechanics.Equipment.Armor;

import Mechanics.Damage;
import Mechanics.DamageType;

import java.util.HashMap;
import java.util.Map;

public class Armor {

    private ArmorType type;
    private ArmorSlot slot;
    private String name;
    private int value;
    private Map<DamageType, Integer> protections;

    public Armor(ArmorType type, ArmorSlot slot, int value) {
        this.type = type;
        this.slot = slot;
        this.value = value;
        protections = new HashMap<>();
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

    public int getValue() {
        return value;
    }

    public void addProtection(Damage protection) {
        int currentProtection = protections.getOrDefault(protection.getDamageType(), 0);
        protections.put(protection.getDamageType(), currentProtection + protection.getValue());
    }

    public Map<DamageType, Integer> getProtections() {
        return protections;
    }
}
