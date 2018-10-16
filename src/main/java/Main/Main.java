package Main;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        CharacterGenerator characterGenerator = new CharacterGenerator();
        List<Character> characterList = characterGenerator.getRandomCharacterList(10);

        for (Character character : characterList) {
            character.printCharacterInfo();
        }
    }
}

