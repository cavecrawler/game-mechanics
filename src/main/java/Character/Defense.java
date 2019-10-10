package Character;

import java.util.HashMap;
import java.util.Map;

import Mechanics.DamageType;

public class Defense {

    private int baseDefense = 0;
    private Map<DamageType, Integer> defenses;

    public Defense() {
        defenses = new HashMap<>();
    }

    public void addBaseDefense(int value) {
        baseDefense += value;
    }

    public void addSpecialDefense(DamageType type, int value) {
        int existingDefense = defenses.getOrDefault(type, 0);
        defenses.put(type, existingDefense + value);
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public Map<DamageType, Integer> getSpecialDefenses() {
        return defenses;
    }
}
