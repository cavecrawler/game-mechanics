package Character;

import java.util.HashMap;
import java.util.Map;

import Mechanics.DamageType;

public class Attack {

    private final Map<DamageType, Integer> attacks;

    public Attack() {
        this.attacks = new HashMap<>();
    }

    public void addAttack(DamageType type, int value) {
        int existingAttack = attacks.getOrDefault(type, 0);
        attacks.put(type, existingAttack + value);
    }

    /**
     * @return the attacks
     */
    public Map<DamageType, Integer> getAttacks() {
        return attacks;
    }
}