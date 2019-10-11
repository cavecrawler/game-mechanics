package Main;

import Character.Attack;
import Character.Defense;
import Character.Attributes.Gender;
import Mechanics.DamageType;
import Mechanics.Equipment.Armor.Armor;
import Mechanics.Equipment.Armor.ArmorSlot;
import Mechanics.Equipment.Weapon.Weapon;
import Mechanics.Equipment.Weapon.WeaponSlot;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Character {

    private String name;
    private Gender gender;
    private CharacterClass characterClass;
    private int hitpoints;
    private Map<ArmorSlot, Armor> equippedArmors;
    private Map<WeaponSlot, Weapon> equippedWeapons;

    public Character(Gender gender, CharacterClass characterClass) {
        this.gender = gender;
        this.characterClass = characterClass;
        hitpoints = characterClass.getHitpoints();
        equippedArmors = new HashMap<>();
        equippedWeapons = new HashMap<>();
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

    public Gender getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public boolean isDead() {
        return hitpoints <= 0;
    }

    public Attack attack() {
        Attack attack = new Attack();

        for (Map.Entry<WeaponSlot, Weapon> weapon : equippedWeapons.entrySet()) {
            for (Map.Entry<DamageType, Integer> damage : weapon.getValue().getDamages().entrySet()) {
                attack.addAttack(damage.getKey(), damage.getValue());
            }
        }

        return attack;
    }

    public Defense defend() {
        Defense defense = new Defense();

        // get protections of weapons
        for (Map.Entry<WeaponSlot, Weapon> weapon : equippedWeapons.entrySet()) {
            for (Map.Entry<DamageType, Integer> damage : weapon.getValue().getProtections().entrySet()) {
                defense.addSpecialDefense(damage.getKey(), damage.getValue());
            }
        }

        // get armor protections
        for (Map.Entry<ArmorSlot, Armor> armor : equippedArmors.entrySet()) {
            int baseArmorValue = armor.getValue().getValue();
            defense.addBaseDefense(baseArmorValue);
            for (Map.Entry<DamageType, Integer> damage : armor.getValue().getProtections().entrySet()) {
                defense.addSpecialDefense(damage.getKey(), baseArmorValue * damage.getValue());
            }
        }

        return defense;
    }

    public void receiveDamage(int damage) {
        if (damage > 0) {
            hitpoints -= damage;
            System.out.println(name + " receives " + damage + " damage.");
        } else {
            System.out.println(name + " was hit but received no damage.");
        }
    }

    public void printCharacterInfo() {
        String armorString = equippedArmors.entrySet().stream().map((e) -> {
            return e.getValue().getName();
        }).collect(Collectors.joining(", "));

        String weaponString = equippedWeapons.entrySet().stream().map((e) -> {
            return e.getValue().getName();
        }).collect(Collectors.joining(", "));

        System.out.println(name + ", " + characterClass.getType() + ", " + hitpoints + " hitpoints, wearing "
                + armorString + " and " + weaponString);
    }

}
