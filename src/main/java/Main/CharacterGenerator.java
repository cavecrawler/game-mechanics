package Main;

import Character.Attributes.Gender;
import Equipment.Armor.Armor;
import Equipment.Weapon.Weapon;
import XMLHandler.XMLDataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class CharacterGenerator {

    public List<Character> getRandomCharacters(int numberOfCharacters) {
        List<Character> characterList = new ArrayList<>();
        XMLDataReader xmlReader = new XMLDataReader();
        Map<Gender, List<String>> characterNames = xmlReader.getCharacterNames();
        List<CharacterClass> classes = xmlReader.getCharacterClasses();
        List<Armor> armors = xmlReader.getArmorEquipment();
        List<Weapon> weapons = xmlReader.getWeaponEquipment();

        for (int i = 0; i < numberOfCharacters; i++) {
            Random rnd = new Random();
            Gender male = Gender.MALE;
            Gender female = Gender.FEMALE;
            Gender gender = rnd.nextInt(2) == 0 ? male : female;

            Character character = new Character(gender, classes.get(rnd.nextInt(classes.size())));
            character.setName(characterNames.get(gender).get(rnd.nextInt(characterNames.get(gender).size())));

            // try to equip an armor
            List<Armor> equippableArmors = getEquippableArmors(character, armors);
            character.equip(equippableArmors.get(rnd.nextInt(equippableArmors.size())));

            // try to equip a weapon
            List<Weapon> equippableWeapons = getEquippableWeapons(character, weapons);
            character.equip(equippableWeapons.get(rnd.nextInt(equippableWeapons.size())));

            characterList.add(character);
        }
        return characterList;
    }

    private List<Armor> getEquippableArmors(Character character, List<Armor> armors) {
        // filters given Armor List for proficiencies
        List<Armor> equippableArmors = armors.stream().filter(aSingleArmor -> {
            return character.getCharacterClass().getArmorProficiencies().contains(aSingleArmor.getType());
        }).collect(Collectors.toList());

        return equippableArmors;
    }

    private List<Weapon> getEquippableWeapons(Character character, List<Weapon> weapons) {
        // filters given Armor List for proficiencies
        List<Weapon> equippableWeapons = weapons.stream().filter(aSingleWeapon -> {
            return character.getCharacterClass().getWeaponProficiencies().contains(aSingleWeapon.getType());
        }).collect(Collectors.toList());

        return equippableWeapons;
    }
}
