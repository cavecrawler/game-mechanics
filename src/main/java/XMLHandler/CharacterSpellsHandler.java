package XMLHandler;

import Mechanics.Damage;
import Mechanics.DamageType;
import Mechanics.Spells.Spell;
import Mechanics.Spells.SpellType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CharacterSpellsHandler extends DefaultHandler {

    private List<Spell> spells;
    private Spell currentSpell;
    private float currentCooldown;
    private DamageType currentDamageType;
    private DamageType currentProtectionType;
    private int currentDamageValue;
    private int currentProtectionValue;

    private boolean nameContext;
    private boolean flavorTextContext;
    private boolean damageContext;
    private boolean protectionContext;

    public List<Spell> getSpells(){
        return spells;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (null == spells) {
            spells = new ArrayList<>();
        }

        if (qName.equalsIgnoreCase("spell")) {
            SpellType st = SpellType.fromString(attributes.getValue("type"));
            currentCooldown = Float.parseFloat(attributes.getValue("cooldown"));
            currentSpell = new Spell(st, currentCooldown);
        } else if (qName.equalsIgnoreCase("name")) {
            nameContext = true;
        } else if (qName.equalsIgnoreCase("flavortext")) {
            flavorTextContext = true;
        } else if (qName.equalsIgnoreCase("damage")) {
            currentDamageType = DamageType.fromString(attributes.getValue("type"));
            damageContext = true;
        } else if (qName.equalsIgnoreCase("protection")) {
            currentProtectionType = DamageType.fromString(attributes.getValue("type"));
            protectionContext = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("spell")) {
            currentSpell.setCooldown(currentCooldown);
            spells.add(currentSpell);
        } else if (qName.equalsIgnoreCase("damage")) {
            currentSpell.addDamage(new Damage(currentDamageType, currentDamageValue));
        } else if (qName.equalsIgnoreCase("protection")) {
            currentSpell.addProtection(new Damage(currentProtectionType, currentProtectionValue));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (nameContext) {
            currentSpell.setName(new String(ch, start, length));
            nameContext = false;
        } else if (flavorTextContext) {
            currentSpell.setFlavortext(new String(ch, start, length));
            flavorTextContext = false;
        } else if (damageContext) {
            currentDamageValue = Integer.parseInt(new String(ch, start, length));
            damageContext = false;
        } else if (protectionContext) {
            currentProtectionValue = Integer.parseInt(new String(ch, start, length));
            protectionContext = false;
        }
    }

}
