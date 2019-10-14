package Main;

import Character.Attack;
import Character.Defense;
import Character.Attributes.Gender;
import Mechanics.DamageType;
import Mechanics.Equipment.Armor.Armor;
import Mechanics.Equipment.Armor.ArmorSlot;
import Mechanics.Equipment.Weapon.Weapon;
import Mechanics.Equipment.Weapon.WeaponSlot;
import Mechanics.Spells.Spell;
import Mechanics.Damage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Character {

    private String name;
    private Gender gender;
    private CharacterClass characterClass;
    private int hitpoints;
    private Map<ArmorSlot, Armor> equippedArmors;
    private Map<WeaponSlot, Weapon> equippedWeapons;
    private List<Spell> equippedSpells;
    private Map<Spell, Float> spellCooldowns;
    private float globalCooldown;


    public Character(Gender gender, CharacterClass characterClass) {
        this.gender = gender;
        this.characterClass = characterClass;
        hitpoints = characterClass.getHitpoints();
        equippedArmors = new HashMap<>();
        equippedWeapons = new HashMap<>();
        equippedSpells = new ArrayList<>();
        spellCooldowns = new HashMap<>();
    }

    public void equip(Armor armor) {
        if (characterClass.getArmorProficiencies().contains(armor.getType())) {
            equippedArmors.put(armor.getSlot(), armor);
        }
    }

    public void equip(Weapon weapon) {
        if (characterClass.getWeaponProficiencies().contains(weapon.getType())) {
            equippedWeapons.put(weapon.getSlot(), weapon);
            if (weapon.getSlot() == WeaponSlot.MAIN_HAND || weapon.getSlot() == WeaponSlot.TWO_HAND) {
                getBasicAttack().setCooldown(weapon.getWeaponSpeed());
                weapon.getDamages().forEach((k, v) -> {
                    getBasicAttack().addDamage(new Damage(k, v));
                });
            }
        }
    }

    public void equip(Spell spell) {
        if (characterClass.getSpellProficiencies().contains(spell.getSpellType())) {
            equippedSpells.add(spell.clone());
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

    public Attack attack(int deltaTickTime) {
        updateCharacterCooldowns(deltaTickTime);

        Attack attack = new Attack();
        castSpell(attack, getSignatureSpell());
        castSpell(attack, getBasicAttack());

        return attack;
    }

    private Attack castSpell(Attack attack, Spell namelessSpell) {
        if (isGlobalCooldownReady() && !spellCooldowns.containsKey(namelessSpell)) {
            for (Map.Entry<DamageType, Integer> damage : namelessSpell.getDamages().entrySet()) {
                attack.addAttack(damage.getKey(), damage.getValue());
            }
            System.out.println(name + " " + namelessSpell.getFlavortext());
            globalCooldown = Arena.GLOBAL_COOLDOWN;
            spellCooldowns.put(namelessSpell, namelessSpell.getCooldown());
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

    private void updateCharacterCooldowns(int deltaTickTime) {
        for (Map.Entry<Spell, Float> currentSpellCooldown : spellCooldowns.entrySet()) {
            float currentCooldown = currentSpellCooldown.getValue() - deltaTickTime / 1000f;
            spellCooldowns.put(currentSpellCooldown.getKey(), currentCooldown);
        }
        spellCooldowns.entrySet().removeIf(entry -> entry.getValue() <= 0);
        if (!isGlobalCooldownReady()) {
            globalCooldown = globalCooldown - deltaTickTime / 1000f;
        }
    }

    private boolean isGlobalCooldownReady() {
        return globalCooldown <= 0;
    }

    private Spell getSignatureSpell() {
        // TODO signature spell is hardcoded - spell selection mechanism needed
        return equippedSpells.get(2);
    }

    private Spell getBasicAttack() {
        // TODO basicAttack spell is hardcoded - spell selection mechanism needed
        return equippedSpells.get(1);
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
