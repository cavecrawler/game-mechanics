package Mechanics.Skills;

import Mechanics.Damage;
import Mechanics.DamageType;

import java.util.HashMap;
import java.util.Map;

public class Skill {

    private String name;
    private String flavortext;
    private SkillType skillType;
    private float cooldown;
    private Map<DamageType, Integer> damages;
    private Map<DamageType, Integer> protections;

    public Skill(SkillType skillType, float cooldown) {
        this.cooldown = cooldown;
        this.skillType = skillType;
        damages = new HashMap<>();
        protections = new HashMap<>();
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public float getCooldown() {
        return cooldown;
    }

    public void setName(String name) { this.name = name; }

    public String getName() { return name; }

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
