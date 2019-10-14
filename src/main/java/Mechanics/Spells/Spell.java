package Mechanics.Spells;

import Mechanics.Damage;
import Mechanics.DamageType;

import java.util.HashMap;
import java.util.Map;

public class Spell implements Cloneable {

    private String name;
    private String flavortext;
    private SpellType spellType;
    private float cooldown;
    private Map<DamageType, Integer> damages;
    private Map<DamageType, Integer> protections;

    public Spell(SpellType spellType, float cooldown) {
        this.cooldown = cooldown;
        this.spellType = spellType;
        damages = new HashMap<>();
        protections = new HashMap<>();
    }

    public Spell clone() {
        try {
            return (Spell)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public float getCooldown() {
        return cooldown;
    }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }

    public SpellType getSpellType() {
        return spellType;
    }

    public void setFlavortext(String flavortext) { this.flavortext = flavortext; }

    public String getFlavortext() { return flavortext; }

    public Map<DamageType, Integer> getDamages() {
        return damages;
    }

    public Map<DamageType, Integer> getProtections() {
        return protections;
    }

    public void addDamage(Damage damage){
        int currentDamageValue = damages.getOrDefault(damage.getDamageType(),0);
        damages.put(damage.getDamageType(), damage.getValue() + currentDamageValue);
    }

    public void addProtection(Damage protection){
        int currentProtectionValue = protections.getOrDefault(protection.getDamageType(),0);
        protections.put(protection.getDamageType(), protection.getValue() + currentProtectionValue);
    }

}
