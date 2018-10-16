package Main;

import Character.Attributes.Gender;
import Equipment.Armor.Armor;
import Equipment.Weapon.Weapon;
import XMLHandler.XMLDataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CharacterGenerator {

    public List<Character> getRandomCharacterList(int numberOfCharacters) {
        List<Character> characterList = new ArrayList<>();
        XMLDataReader xmlReader = new XMLDataReader();
        Map<Gender, List<String>> characterNames = xmlReader.getCharacterNames();
        List<CharacterClass> classes = xmlReader.getCharacterClasses();
        List<Armor> armors = xmlReader.getArmorEquipment();
        List<Weapon> weapons = xmlReader.getWeaponEquipment();

        for (int i = 0; i < numberOfCharacters; i++) {
            Random rnd = new Random();
            Gender gender = rnd.nextInt(2) == 0 ? Gender.MALE : Gender.FEMALE;


            Character character = new Character(gender, classes.get(rnd.nextInt(classes.size())));
            character.setName(characterNames.get(gender).get(rnd.nextInt(characterNames.get(gender).size())));

            // try to equip an armor
            character.equip(armors.get(0));

            // try to equip a weapon
            character.equip(weapons.get(0));

            characterList.add(character);
        }
        return characterList;
    }
}
