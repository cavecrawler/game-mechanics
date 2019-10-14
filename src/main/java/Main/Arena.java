package Main;

import java.util.Map;
import Character.Attack;
import Character.Defense;
import Mechanics.DamageType;

/**
 * Arena with fixed timestep loop
 */
public class Arena {

    private static final int TICKS = 20;
    private static final long MS_PER_TICK = 1000 / TICKS;

    private static final int ARMOR_CAP = 250;
    private static final float MAX_DAMAGE_REDUCTION = 0.75f;

    public static final float GLOBAL_COOLDOWN = 0.5f;

    private long lastTickTime = 0;
    private int deltaTickTime = 0;

    private boolean running = true;

    private Character charOne;
    private Character charTwo;

    private ScoreBoard scoreBoard;

    public Arena() {
        scoreBoard = new ScoreBoard();
    }

    public void populateCharacters(Character first, Character second) {
        charOne = first;
        charTwo = second;

        System.out.println("These heroes have entered the Arena:");
        charOne.printCharacterInfo();
        charTwo.printCharacterInfo();
    }

    public void update(long tickTime) {
        deltaTickTime = (int)(tickTime - lastTickTime);
        if (charOne.isDead() || charTwo.isDead()) {
            running = false;
            if (charOne.isDead()) {
                scoreBoard.endRound(charTwo, charOne);
            }
            if (charTwo.isDead()) {
                scoreBoard.endRound(charOne, charTwo);
            }
            return;
        }

        Attack attackOfCharOne = charOne.attack(deltaTickTime);
        Defense defenseOfCharOne = charOne.defend();

        Attack attackOfCharTwo = charTwo.attack(deltaTickTime);
        Defense defenseOfCharTwo = charTwo.defend();

        if (!attackOfCharOne.getAttacks().entrySet().isEmpty()) {
            charTwo.receiveDamage(fight(attackOfCharOne, defenseOfCharTwo));
        }
        if (!attackOfCharTwo.getAttacks().entrySet().isEmpty()){
            charOne.receiveDamage(fight(attackOfCharTwo, defenseOfCharOne));
        }
        lastTickTime = tickTime;
    }

    private int fight(Attack attack, Defense defense) {
        int resultDamage = 0;
        for (Map.Entry<DamageType, Integer> damage : attack.getAttacks().entrySet()) {
            int damageTypeDefense = defense.getSpecialDefenses().getOrDefault(damage.getKey(),
                    defense.getBaseDefense());
            int finalDamageTypeDefense = Math.min(damageTypeDefense, ARMOR_CAP);

            float finalDamageReduction = ((float) finalDamageTypeDefense / ARMOR_CAP) * MAX_DAMAGE_REDUCTION;

            resultDamage += Math.ceil(damage.getValue() * ( 1 - finalDamageReduction ));
        }
        return resultDamage;
    }

    public void run() throws InterruptedException {

        running = true;

        while (running) {
            long startTime = System.currentTimeMillis();

            update(startTime);

            try { //try-catch only for debugging purpose
                Thread.sleep(startTime + MS_PER_TICK - System.currentTimeMillis());
            } catch (Exception e) {

            }
        }
    }
}