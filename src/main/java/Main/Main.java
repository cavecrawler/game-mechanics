package Main;

import Equipment.Armor.Armor;
import XMLHandler.XMLDataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {


        XMLDataReader xmlReader = new XMLDataReader();
        List<ArrayList<String>> characterNames = xmlReader.getCharacterNames();
        List<CharacterClass> classes = xmlReader.getCharacterClasses();
        List<Armor> armors = xmlReader.getArmorEquipment();
        List<Character> characterList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Random rnd = new Random();
            Character character = new Character();
            String gender = null;
            switch (rnd.nextInt(2)) {
                case 0:
                    gender = "male";
                    break;
                case 1:
                    gender = "female";
                    break;
            }
            character.setCharGender(gender);
            if (gender.contentEquals("male")) {
                character.setCharName(characterNames.get(0).get(rnd.nextInt(characterNames.get(0).size())));
            } else if (gender.contentEquals("female")) {
                character.setCharName(characterNames.get(1).get(rnd.nextInt(characterNames.get(1).size())));
            }
            character.setCharacterClass(classes.get(rnd.nextInt(classes.size())));
            characterList.add(character);
        }

        for (Character character : characterList) {
            character.printCharacterInfo();
        }
    }
}

