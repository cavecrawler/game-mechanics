package Main;

import XMLHandler.CharacterClassHandler;
import XMLHandler.NameHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        List<CharacterClass> classes;
        List<ArrayList<String>> characterNames;
        List<Character> characterList = new ArrayList<Character>();

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        File namesFile = new File("src/main/resources/character_names.xml");

        try {
            SAXParser nameParser = saxParserFactory.newSAXParser();
            NameHandler nameHandler = new NameHandler();
            nameParser.parse(namesFile, nameHandler);
            characterNames = nameHandler.getNamesList();

            for (ArrayList<String> genderNames : characterNames) {
                for (String name : genderNames) {
                    System.out.println(name);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            SAXParser characterClassesParser = saxParserFactory.newSAXParser();
            File characterClassesFile = new File("src/main/resources/character_classes.xml");
            CharacterClassHandler characterClassHandler = new CharacterClassHandler();
            characterClassesParser.parse(characterClassesFile, characterClassHandler);
            classes = characterClassHandler.getClasses();

            for (CharacterClass characterClass : classes) {
                System.out.println("");
                System.out.println("type: " + characterClass.getType());
                System.out.println("attribute: " + characterClass.getAttribute());
                System.out.println("hitpoints: " + characterClass.getHitpoints());
                System.out.println("mana: " + characterClass.getMana());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for (int i = 0; i < 5; i++) {
                Random rnd = new Random();
                Character character = new Character();
                String gender;
                switch (rnd.nextInt(3)) {
                    case 0:
                        gender = "male";
                        break;
                    case 1:
                        gender = "female";
                        break;
                    case 2:
                        gender = "neutral";
                        break;
                }
                character.setCharGender(gender);
                if (gender.contentEquals("male")) {
                    character.setCharName(characterNames.get(0).get(rnd.nextInt(characterNames.get(0).size())));
                } else if (gender.contentEquals("female")) {
                    character.setCharName(characterNames.get(1).get(rnd.nextInt(characterNames.get(1).size())));
                } else if (gender.contentEquals("neutral")) {
                    character.setCharName(characterNames.get(2).get(rnd.nextInt(characterNames.get(2).size())));
                }
                character.setCharacterClass(classes.get(rnd.nextInt(classes.size())));
                characterList.add(character);
            }
            int i = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
