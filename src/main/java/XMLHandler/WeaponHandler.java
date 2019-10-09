package XMLHandler;

import Mechanics.Damage;
import Mechanics.DamageType;
import Mechanics.Equipment.Weapon.Weapon;
import Mechanics.Equipment.Weapon.WeaponSlot;
import Mechanics.Equipment.Weapon.WeaponType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class WeaponHandler extends DefaultHandler {

    private List<Weapon> weapons;
    private Weapon currentWeapon;
    private int currentDamageValue;
    private int currentProtectionValue;
    private DamageType currentDamageType;
    private DamageType currentProtectionType;

    private boolean nameContext = false;
    private boolean damageContext = false;
    private boolean protectionContext = false;

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
        if (qName.equalsIgnoreCase("weapon")) {
            weapons.add(currentWeapon);
        } else if (qName.equalsIgnoreCase("damage")) {
            currentWeapon.addDamage(new Damage(currentDamageType, currentDamageValue));
            damageContext = false;
        } else if (qName.equalsIgnoreCase("protection")) {
            currentWeapon.addProtection(new Damage(currentProtectionType, currentProtectionValue));
            protectionContext = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (nameContext) {
            currentWeapon.setName(new String(ch, start, length));
            nameContext = false;
        } else if (damageContext) {
            currentDamageValue = Integer.parseInt(new String(ch, start, length));
            damageContext = false;
        } else if (protectionContext){
            currentProtectionValue = Integer.parseInt(new String(ch, start, length));
            protectionContext = false;
        }
    }
}
