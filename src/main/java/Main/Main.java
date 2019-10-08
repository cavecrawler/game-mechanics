package Main;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Arena arena = new Arena();
        CharacterGenerator characterGenerator = new CharacterGenerator();
        List<Character> characters = characterGenerator.getRandomCharacters(2);

        arena.populateCharacters(characters.get(0), characters.get(1));
        try {
            arena.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
