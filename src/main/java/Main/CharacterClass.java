package Main;

import Mechanics.Equipment.Armor.ArmorType;
import Mechanics.Equipment.Weapon.WeaponType;
import Mechanics.Spells.SpellType;

import java.util.ArrayList;
import java.util.List;

public class CharacterClass {
    private String type;
    private String attribute;
    private int hitpoints;
    private int mana;
    private List<ArmorType> armorProficiencies;
    private List<WeaponType> weaponProficiencies;
    private List<SpellType> spellProficiencies;

    public CharacterClass() {
        armorProficiencies = new ArrayList<>();
        weaponProficiencies = new ArrayList<>();
        spellProficiencies = new ArrayList<>();
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

    public List<SpellType> getSpellProficiencies() { return this.spellProficiencies; }

    public List<WeaponType> getWeaponProficiencies() { return weaponProficiencies; }
}
