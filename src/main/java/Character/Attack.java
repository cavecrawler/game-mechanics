package Character;

import java.util.Map;

import Equipment.DamageType;

public class Attack {

    private final Map<DamageType, Integer> attacks;

    public Attack(Map<DamageType, Integer> attacks) {
        this.attacks = attacks;
    }

    /**
     * @return the attacks
     */
    public Map<DamageType, Integer> getAttacks() {
        return attacks;
    }
}