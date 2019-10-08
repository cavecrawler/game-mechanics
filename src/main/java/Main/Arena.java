package Main;

import java.util.Map;
import Character.Attack;
import Character.Defense;
import Equipment.DamageType;

/**
 * Arena with fixed timestep loop
 */
public class Arena {

    private static final int TICKS = 20;
    private static final long MS_PER_TICK = 1000 / TICKS;

    private boolean running = true;

    private Character charOne;
    private Character charTwo;

    public void populateCharacters(Character first, Character second) {
        charOne = first;
        charTwo = second;
    }

    public void update() {
        if (charOne.isDead() || charTwo.isDead()) {
            running = false;
            if (charOne.isDead()) {
                System.out.println(charOne.getName() + " was defeated!");
            }
            if (charTwo.isDead()) {
                System.out.println(charTwo.getName() + " was defeated!");
            }
            return;
        }

        Attack attackOfCharOne = charOne.attack();
        Defense defenseOfCharOne = charOne.defend();

        Attack attackOfCharTwo = charTwo.attack();
        Defense defenseOfCharTwo = charTwo.defend();

        charOne.receiveDamage(fight(attackOfCharTwo, defenseOfCharOne));
        charTwo.receiveDamage(fight(attackOfCharOne, defenseOfCharTwo));
    }

    private int fight(Attack attack, Defense defense) {
        int resultDamage = 0;
        for(Map.Entry<DamageType, Integer> damage : attack.getAttacks().entrySet()) {
            int damageTypeDefense = defense.getDefenses().getOrDefault(damage.getKey(), 0);
            resultDamage += Math.max(0, damage.getValue() - damageTypeDefense);
        }
        return resultDamage;
    }

    public void run() throws InterruptedException {

        running = true;

        while (running) {
            long startTime = System.currentTimeMillis();

            update();

            Thread.sleep(startTime + MS_PER_TICK - System.currentTimeMillis());
        }
    }
}