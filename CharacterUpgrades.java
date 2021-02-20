import java.util.ArrayList;

public class CharacterUpgrades extends Item {

    public static final CharacterUpgrades PILOT1 =
            new CharacterUpgrades("Light Feather", 5, "Pilot", 1);
    public static final CharacterUpgrades MERCHANT1 =
            new CharacterUpgrades("Golden Coin", 5, "Merchant", 1);
    public static final CharacterUpgrades FIGHTER1 =
            new CharacterUpgrades("Stone-Tipped Arrow", 5, "Fighter", 1);
    public static final CharacterUpgrades ENGINEER1 =
            new CharacterUpgrades("Rusted Screw", 5, "Engineer", 1);

    public static final CharacterUpgrades PILOT2 =
            new CharacterUpgrades("Pilot's Goggles", PILOT1.getBasePrice() * 2, "Pilot", 2);
    public static final CharacterUpgrades MERCHANT2 =
            new CharacterUpgrades("Eyepatch", MERCHANT1.getBasePrice() * 2, "Merchant", 2);
    public static final CharacterUpgrades FIGHTER2 =
            new CharacterUpgrades("Dagger", FIGHTER1.getBasePrice() * 2,  "Fighter", 2);
    public static final CharacterUpgrades ENGINEER2 =
            new CharacterUpgrades("Screwdriver", ENGINEER1.getBasePrice() * 2, "Engineer", 2);

    public static final CharacterUpgrades PILOT3 =
            new CharacterUpgrades("Astrolabe", PILOT2.getBasePrice() * 2, "Pilot", 3);
    public static final CharacterUpgrades MERCHANT3 =
            new CharacterUpgrades("Golden Cloak", MERCHANT2.getBasePrice() * 2, "Merchant", 3);
    public static final CharacterUpgrades FIGHTER3 =
            new CharacterUpgrades("Gunpowder", FIGHTER2.getBasePrice() * 2,  "Fighter", 3);
    public static final CharacterUpgrades ENGINEER3 =
            new CharacterUpgrades("Toolbox", ENGINEER2.getBasePrice() * 2, "Engineer", 3);

    public static final CharacterUpgrades PILOT4 =
            new CharacterUpgrades("Drone", PILOT3.getBasePrice() * 2, "Pilot", 4);
    public static final CharacterUpgrades MERCHANT4 =
            new CharacterUpgrades("Potion of Persuasion",
                    MERCHANT3.getBasePrice() * 2, "Merchant", 4);
    public static final CharacterUpgrades FIGHTER4 =
            new CharacterUpgrades("Laser Gun", FIGHTER3.getBasePrice() * 2,  "Fighter", 4);
    public static final CharacterUpgrades ENGINEER4 =
            new CharacterUpgrades("Robot", ENGINEER3.getBasePrice() * 2, "Engineer", 4);

    private String skill;
    private int power;
    
    public CharacterUpgrades(String name, double basePrice, String skill, int power) {
        super(name, basePrice);
        this.skill = skill;
        this.power = power;
    }

    public String getDescription() {
        String description =
                String.format("This item increases your %s skill by %d points", skill, power);
        return description;
    }

    public String getSkill() {
        return skill;
    }

    public int getPower() {
        return power;
    }


    public static ArrayList<CharacterUpgrades> getBasicCharacterUpgrades() {
        ArrayList<CharacterUpgrades> a = new ArrayList<>();
        a.add(PILOT1);
        a.add(MERCHANT1);
        a.add(ENGINEER1);
        a.add(FIGHTER1);
        return a;
    }

    public static ArrayList<CharacterUpgrades> getGoodCharacterUpgrades() {
        ArrayList<CharacterUpgrades> a = new ArrayList<>();
        a.add(PILOT2);
        a.add(MERCHANT2);
        a.add(ENGINEER2);
        a.add(FIGHTER2);
        return a;
    }

    public static ArrayList<CharacterUpgrades> getTopCharacterUpgrades() {
        ArrayList<CharacterUpgrades> a = new ArrayList<>();
        a.add(PILOT3);
        a.add(MERCHANT3);
        a.add(ENGINEER3);
        a.add(FIGHTER3);
        return a;
    }

    public static ArrayList<CharacterUpgrades> getBestCharacterUpgrades() {
        ArrayList<CharacterUpgrades> a = new ArrayList<>();
        a.add(PILOT4);
        a.add(MERCHANT4);
        a.add(ENGINEER4);
        a.add(FIGHTER4);
        return a;
    }
}
