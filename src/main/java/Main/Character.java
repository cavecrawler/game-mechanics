package Main;

import Equipment.Armor.Armor;
import Equipment.Armor.ArmorSlot;
import Equipment.Weapon.Weapon;
import Equipment.Weapon.WeaponSlot;

import java.util.HashMap;
import java.util.Map;

public class Character {

    private String name;
    private String gender;
    private CharacterClass characterClass;
    private int hitpoints;
    private Map<ArmorSlot, Armor> equippedArmors;
    private Map<WeaponSlot, Weapon> equippedWeapons;

    public Character(String gender, CharacterClass characterClass) {
        this.gender = gender;
        this.characterClass = characterClass;
        hitpoints = characterClass.getHitpoints();
        equippedArmors = new HashMap<>();
        equippedWeapons = new HashMap<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void equip(Armor armor) {
        if (characterClass.getArmorProficiencies().contains(armor.getType())) {
            equippedArmors.put(armor.getSlot(), armor);
        }
    }

    public void equip(Weapon weapon) {
        if (characterClass.getWeaponProficiencies().contains(weapon.getType())) {
            equippedWeapons.put(weapon.getSlot(), weapon);
        }
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void printCharacterInfo() {
        System.out.println(name + ", " + characterClass.getType() + ", " + hitpoints + " hitpoints");
    }

}
