package XMLHandler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;


public class NameHandler extends DefaultHandler {

    private ArrayList<ArrayList<String>> names;
    private String gender;
    private String name;
    boolean bName = false;


    public ArrayList<ArrayList<String>> getNamesList() {
        return names;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("names")) {
            if (names == null) {
                names = new ArrayList<ArrayList<String>>();
                for (int i=0; i<3; i++) {
                    names.add(new ArrayList<String>());
                }
            }
        } else if (qName.equalsIgnoreCase("name")) {
            gender = attributes.getValue("gender");
            bName = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("name")) {

            if (gender.contentEquals("male")) {
                names.get(0).add(name);
            } else if (gender.contentEquals("female")) {
                names.get(1).add(name);
            } else if (gender.contentEquals("neutral")) {
                names.get(2).add(name);
            }
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
