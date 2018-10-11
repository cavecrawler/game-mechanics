package XMLHandler;

import Equipment.Armor.Armor;
import Main.CharacterClass;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLDataReader {


    public List<ArrayList<String>> getCharacterNames() {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        File namesFile = new File("src/main/resources/character_names.xml");
        List<ArrayList<String>> characterNames = new ArrayList<>();

        try {
            SAXParser nameParser = saxParserFactory.newSAXParser();
            NameHandler nameHandler = new NameHandler();
            nameParser.parse(namesFile, nameHandler);
            characterNames = nameHandler.getNamesList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return characterNames;
    }


    public List<CharacterClass> getCharacterClasses() {

        List<CharacterClass> classes = new ArrayList<>();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser characterClassesParser = saxParserFactory.newSAXParser();
            File characterClassesFile = new File("src/main/resources/character_classes.xml");
            CharacterClassHandler characterClassHandler = new CharacterClassHandler();
            characterClassesParser.parse(characterClassesFile, characterClassHandler);
            classes = characterClassHandler.getClasses();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    public List<Armor> getArmorEquipment() {
        List<Armor> armors = null;
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser armorParser = saxParserFactory.newSAXParser();
            File armorFile = new File("src/main/resources/equipment_armors.xml");
            ArmorHandler armorHandler = new ArmorHandler();
            armorParser.parse(armorFile, armorHandler);
            armors = armorHandler.getArmors();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return armors;
    }
}
