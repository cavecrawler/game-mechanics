package XMLHandler;

import Equipment.Armor.Armor;
import Equipment.Armor.ArmorSlot;
import Equipment.Armor.ArmorType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ArmorHandler extends DefaultHandler {

    private List<Armor> armors;
    private Armor currentArmor;

    private boolean nameContext = false;

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
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("armor")) {
            armors.add(currentArmor);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (nameContext) {
            currentArmor.setName(new String(ch, start, length));
            nameContext = false;
        }
    }
}
