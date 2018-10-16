package Main;

import Equipment.Armor.Armor;
import Equipment.Weapon.Weapon;
import XMLHandler.XMLDataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {


        CharacterGenerator characterGenerator = new CharacterGenerator();
        List<Character> characterList = characterGenerator.getRandomCharacterList(10);

        for (Character character : characterList) {
            character.printCharacterInfo();
        }
    }
}

