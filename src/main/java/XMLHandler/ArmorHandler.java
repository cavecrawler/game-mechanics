package XMLHandler;

import Equipment.Armor.Armor;
import Equipment.Armor.ArmorSlot;
import Equipment.Armor.ArmorType;
import Equipment.Armor.Protection;
import Equipment.DamageType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ArmorHandler extends DefaultHandler {

    private List<Armor> armors;
    private Armor currentArmor;
    private DamageType currentProtectionType;
    private int currentProtectionValue;

    private boolean nameContext = false;
    private boolean protectionContext = false;

    public List<Armor> getArmors() {
        return armors;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (null == armors) {
            armors = new ArrayList<>();
        }
        if (qName.equalsIgnoreCase("armor")) {
            ArmorSlot as = ArmorSlot.fromString(attributes.getValue("slot"));
            ArmorType at = ArmorType.fromString(attributes.getValue("type"));
            currentArmor = new Armor(at, as);
        } else if (qName.equalsIgnoreCase("name")) {
            nameContext = true;
        } else if (qName.equalsIgnoreCase("protection")) {
            currentProtectionType = DamageType.fromString(attributes.getValue("type"));
            protectionContext = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("armor")) {
            armors.add(currentArmor);
        } else if (qName.equalsIgnoreCase("protection")) {
            currentArmor.addProtection(new Protection(currentProtectionType, currentProtectionValue));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (nameContext) {
            currentArmor.setName(new String(ch, start, length));
            nameContext = false;
        } else if (protectionContext) {
            currentProtectionValue = Integer.parseInt(new String(ch, start, length));
            protectionContext = false;
        }
    }
}
