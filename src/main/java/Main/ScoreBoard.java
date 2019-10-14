package Main;

import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {

    private Map<Character, Integer> champions;
    private Character currentChampion;
    private int currentChampionSurvivedRounds;

    public ScoreBoard() {
        champions = new HashMap<Character, Integer>();
    }

    public void endRound(Character winnerOfThisRound, Character looserOfThisRound) {
        // check for the first round
        if (currentChampion == null) {
            currentChampion = winnerOfThisRound;
            thereIsANewChampion(currentChampion);
            announceRoundSummary(currentChampion, looserOfThisRound);
            System.out.println("All hail " + currentChampion.getName() + ", new Champion of the Arena!");
        } else {
            // for all following rounds
            if (winnerOfThisRound == currentChampion) {
                championWonAnotherRound();
                announceRoundSummary(currentChampion, looserOfThisRound);
                System.out.println(winnerOfThisRound.getName() + " the Mighty, already survived " +  currentChampionSurvivedRounds + " rounds!");
            } else {
                currentChampion = winnerOfThisRound;
                thereIsANewChampion(currentChampion);
                announceRoundSummary(currentChampion, looserOfThisRound);
                System.out.println("All Hail " + currentChampion.getName() + ", new Champion of the Arena!");
            }
        }

    }


    public void thereIsANewChampion(Character newChampion) {
        if (currentChampion == null) {
            champions = new HashMap<Character, Integer>();
        }
        currentChampionSurvivedRounds = 1;
        champions.put(newChampion, currentChampionSurvivedRounds);

    }

    public void championWonAnotherRound(){
        champions.put(currentChampion, champions.get(currentChampion) + 1);
        currentChampionSurvivedRounds++;
    }

    public Map<Character, Integer> getChampions() { return champions; }

    public void announceRoundSummary(Character winner, Character looser) {
        System.out.println(looser.getName() + " the " + looser.getCharacterClass().getType() + " was defeated!");
        System.out.println(winner.getName() + " the " + winner.getCharacterClass().getType() + " was victorious, with " + winner.getHitpoints() + " hp left!");
    }
}
