package Main;

import Mechanics.Equipment.Armor.ArmorType;
import Mechanics.Equipment.Weapon.WeaponType;
import Mechanics.Skills.SkillType;

import java.util.ArrayList;
import java.util.List;

public class CharacterClass {
    private String type;
    private String attribute;
    private int hitpoints;
    private int mana;
    private List<ArmorType> armorProficiencies;
    private List<WeaponType> weaponProficiencies;
    private List<SkillType> skillProficiencies;

    public CharacterClass() {
        armorProficiencies = new ArrayList<>();
        weaponProficiencies = new ArrayList<>();
        skillProficiencies = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public List<ArmorType> getArmorProficiencies() {
        return this.armorProficiencies;
    }

    public List<SkillType> getSkillProficiencies() {
        return this.skillProficiencies;
    }

    public List<WeaponType> getWeaponProficiencies() {
        return weaponProficiencies;
    }
}
