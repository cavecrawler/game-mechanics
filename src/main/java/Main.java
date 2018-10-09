import XMLHandler.CharacterClassHandler;
import XMLHandler.NameHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        File namesFile = new File("src/main/resources/character_names.xml");
        try {
            SAXParser nameParser = saxParserFactory.newSAXParser();
            NameHandler nameHandler = new NameHandler();
            nameParser.parse(namesFile, nameHandler);
            List<ArrayList<String>> names = nameHandler.getNamesList();

            for (ArrayList<String> genderNames : names) {
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
            List<CharacterClass> classes = characterClassHandler.getClasses();

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
    }
}
