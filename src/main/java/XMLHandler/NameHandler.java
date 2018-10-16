package XMLHandler;

import Character.Attributes.Gender;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NameHandler extends DefaultHandler {

    private Map<Gender, List<String>> names;
    private Gender currentGender;
    private String name;
    boolean bName = false;


    public Map<Gender, List<String>> getNamesList() {
        return names;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("names")) {
            if (names == null) {
                names = new HashMap<Gender, List<String>>();
                for (Gender g : Gender.values()) {
                    names.put(g, new ArrayList<String>());
                }
            }
        } else if (qName.equalsIgnoreCase("name")) {
            currentGender = Gender.fromString(attributes.getValue("gender"));
            bName = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("name")) {

            names.get(currentGender).add(name);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (bName == true) {
            name = new String(ch, start, length);
            bName = false;
        }
    }
}
