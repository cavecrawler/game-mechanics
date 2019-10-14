package Main;

import Character.Attributes.Gender;
import Mechanics.Equipment.Armor.Armor;
import Mechanics.Equipment.Armor.ArmorSlot;
import Mechanics.Equipment.Weapon.Weapon;
import Mechanics.Spells.Spell;
import XMLHandler.XMLDataReader;

import java.util.*;
import java.util.stream.Collectors;

public class CharacterGenerator {

    public List<Character> getRandomCharacters(int numberOfCharacters) {
        List<Character> characterList = new ArrayList<>();
        XMLDataReader xmlReader = new XMLDataReader();
        Map<Gender, List<String>> characterNames = xmlReader.getCharacterNames();
        List<CharacterClass> classes = xmlReader.getCharacterClasses();
        List<Armor> armors = xmlReader.getArmorEquipment();
        List<Weapon> weapons = xmlReader.getWeaponEquipment();
        List<Spell> spells = xmlReader.getSpells();
        // TODO skills an character anheften

        for (int i = 0; i < numberOfCharacters; i++) {
            Random rnd = new Random();
            Gender male = Gender.MALE;
            Gender female = Gender.FEMALE;
            Gender gender = rnd.nextInt(2) == 0 ? male : female;

            Character character = new Character(gender, classes.get(rnd.nextInt(classes.size())));
            character.setName(characterNames.get(gender).get(rnd.nextInt(characterNames.get(gender).size())));

            // try to equip spells
            List<Spell> equippableSpells = getEquippableSpells(character, spells);
            for (Spell currentSpell : equippableSpells) {
                character.equip(currentSpell);
            }
            characterList.add(character);

            // try to equip an armor
            for (Map.Entry<ArmorSlot, List<Armor>> armorsBySlot : getEquippableArmors(character, armors).entrySet()) {
                int size = armorsBySlot.getValue().size();
                character.equip(armorsBySlot.getValue().get(rnd.nextInt(size)));
            }

            // try to equip a weapon
            List<Weapon> equippableWeapons = getEquippableWeapons(character, weapons);
            character.equip(equippableWeapons.get(rnd.nextInt(equippableWeapons.size())));
        }
        return characterList;
    }

    private Map<ArmorSlot, List<Armor>> getEquippableArmors(Character character, List<Armor> armors) {
        // filters given Armor List for proficiencies
        Map<ArmorSlot, List<Armor>> equippableArmors = new HashMap<>();
        List<Armor> unsortetEquippableArmors = armors.stream().filter(aSingleArmor -> {
            return character.getCharacterClass().getArmorProficiencies().contains(aSingleArmor.getType());
        }).collect(Collectors.toList());

        for (Armor currentArmor : unsortetEquippableArmors) {
            if (equippableArmors.containsKey(currentArmor.getSlot())) {
                equippableArmors.get(currentArmor.getSlot()).add(currentArmor);
            } else {
                List<Armor> armorList = new ArrayList<>();
                armorList.add(currentArmor);
                equippableArmors.put(currentArmor.getSlot(), armorList);
            }
        }

        return equippableArmors;
    }

    private List<Weapon> getEquippableWeapons(Character character, List<Weapon> weapons) {
        // filters given Armor List for proficiencies
        List<Weapon> equippableWeapons = weapons.stream().filter(aSingleWeapon -> {
            return character.getCharacterClass().getWeaponProficiencies().contains(aSingleWeapon.getType());
        }).collect(Collectors.toList());

        return equippableWeapons;
    }

    private List<Spell> getEquippableSpells(Character character, List<Spell> spells) {
        List<Spell> equippableSpells = spells.stream().filter(aSingleSpell -> {
            return character.getCharacterClass().getSpellProficiencies().contains(aSingleSpell.getSpellType());
        }).collect(Collectors.toList());

        return equippableSpells;
    }
}
