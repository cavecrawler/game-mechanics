package XMLHandler;

import Mechanics.Equipment.Armor.ArmorType;
import Mechanics.Equipment.Weapon.WeaponType;
import Main.CharacterClass;
import Mechanics.Skills.SkillType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CharacterClassHandler extends DefaultHandler {

    private List<CharacterClass> classes;
    private CharacterClass characterClass;
    boolean bAttributes = false;
    boolean bHitpoints = false;
    boolean bMana = false;

    public List<CharacterClass> getClasses() {
        return classes;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("class")) {
            if (classes == null) {
                classes = new ArrayList<CharacterClass>();
            }
            characterClass = new CharacterClass();
            characterClass.setType(attributes.getValue("type"));
        } else if (qName.equalsIgnoreCase("attribute")) {
            bAttributes = true;
        } else if (qName.equalsIgnoreCase("hitpoints")) {
            bHitpoints = true;
        } else if (qName.equalsIgnoreCase("mana")) {
            bMana = true;
        } else if (qName.equalsIgnoreCase("armor")) {
            characterClass.getArmorProficiencies().add(ArmorType.fromString(attributes.getValue("type")));
        } else if (qName.equalsIgnoreCase("weapon")) {
            characterClass.getWeaponProficiencies().add(WeaponType.fromString(attributes.getValue("type")));
        } else if (qName.equalsIgnoreCase("skill")) {
            characterClass.getSkillProficiencies().add(SkillType.fromString(attributes.getValue("type")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("class")) {
            classes.add(characterClass);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (bAttributes) {
            characterClass.setAttribute(new String(ch, start, length));
            bAttributes = false;
        } else if (bHitpoints) {
            characterClass.setHitpoints(Integer.parseInt(new String(ch, start, length)));
            bHitpoints = false;
        } else if (bMana) {
            characterClass.setMana(Integer.parseInt(new String(ch, start, length)));
            bMana = false;
        }
    }
}
