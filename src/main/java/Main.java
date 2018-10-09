import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        File namesFile = new File("src/main/resources/character_names.xml");
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            NameHandler nameHandler = new NameHandler();
            saxParser.parse(namesFile, nameHandler);
            ArrayList<ArrayList<String>> names = nameHandler.getNamesList();

            for (ArrayList<String> genderNames : names) {
                for (String name : genderNames) {
                    System.out.println(name);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
