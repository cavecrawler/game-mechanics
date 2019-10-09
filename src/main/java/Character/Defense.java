package Character;

import java.util.Map;

import Equipment.DamageType;

public class Defense {

    private final Map<DamageType, Integer> defenses;

    public Defense(Map<DamageType, Integer> defenses) {
        this.defenses = defenses;
    }

    /**
     * @return the defenses
     */
    public Map<DamageType, Integer> getDefenses() {
        return defenses;
    }
}
