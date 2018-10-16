package Main;

import Equipment.Armor.Armor;
import Equipment.Weapon.Weapon;
import XMLHandler.XMLDataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CharacterGenerator {

    public List<Character> getRandomCharacterList(int numberOfCharacters) {
        List<Character> characterList = new ArrayList<>();
        XMLDataReader xmlReader = new XMLDataReader();
        List<ArrayList<String>> characterNames = xmlReader.getCharacterNames();
        List<CharacterClass> classes = xmlReader.getCharacterClasses();
        List<Armor> armors = xmlReader.getArmorEquipment();
        List<Weapon> weapons = xmlReader.getWeaponEquipment();

        for (int i = 0; i < numberOfCharacters; i++) {
            Random rnd = new Random();
            String gender = null;
            switch (rnd.nextInt(2)) {
                case 0:
                    gender = "male";
                    break;
                case 1:
                    gender = "female";
                    break;
            }
            Character character = new Character(gender, classes.get(rnd.nextInt(classes.size())));
            if (gender.contentEquals("male")) {
                character.setName(characterNames.get(0).get(rnd.nextInt(characterNames.get(0).size())));
            } else if (gender.contentEquals("female")) {
                character.setName(characterNames.get(1).get(rnd.nextInt(characterNames.get(1).size())));
            }

            // try to equip an armor
            character.equip(armors.get(0));

            // try to equip a weapon
            character.equip(weapons.get(0));

            characterList.add(character);
        }
        return characterList;
    }
}
