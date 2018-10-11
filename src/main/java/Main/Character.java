package Main;

public class Character {

    private String charName;
    private String charGender;
    private CharacterClass characterClass;
    private int hitpoints;

    public Character(String charName, CharacterClass characterClass) {
        this.charName = charName;
        this.characterClass = characterClass;
    }

    public Character() {
    }

    public String getCharGender() {
        return charGender;
    }

    public void setCharGender(String charGender) {
        this.charGender = charGender;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
        hitpoints = characterClass.getHitpoints();
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public void printCharacterInfo() {
        System.out.println(charName + ", " + characterClass.getType() + ", " + hitpoints + " hitpoints");
    }

}
