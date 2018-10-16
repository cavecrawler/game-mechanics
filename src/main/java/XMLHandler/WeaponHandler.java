package XMLHandler;

import Equipment.Weapon.Weapon;
import Equipment.Weapon.WeaponSlot;
import Equipment.Weapon.WeaponType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class WeaponHandler extends DefaultHandler {

    private List<Weapon> weapons;
    private Weapon currentWeapon;

    private boolean nameContext = false;

    public List<Weapon> getWeapons() {
        return weapons;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (null == weapons) {
            weapons = new ArrayList<>();
        }

        if (qName.equalsIgnoreCase("weapon")) {
            WeaponType wt = WeaponType.fromString(attributes.getValue("type"));
            WeaponSlot ws = WeaponSlot.fromString(attributes.getValue("slot"));
            currentWeapon = new Weapon(wt, ws);
        } else if (qName.equalsIgnoreCase("name")) {
            nameContext = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("weapon")) {
            weapons.add(currentWeapon);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (nameContext) {
            currentWeapon.setName(new String(ch, start, length));
            nameContext = false;
        }
    }
}
