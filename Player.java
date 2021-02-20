import java.util.HashMap;

public class Player {
    private static Player player;
    private String name;
    private String difficulty;
    private int pilotSkillPoints;
    private int merchantSkillPoints;
    private int fighterSkillPoints;
    private int engineerSkillPoints;
    private int maxSkillPoints;
    private int remainingPoints;
    private double credits = 150;
    private Ship ship;
    private HashMap<CharacterUpgrades, Integer> upgrades;
    private static boolean fleeing;

    private Player() {
        remainingPoints = 16;
        difficulty = "Medium";
        pilotSkillPoints = 0;
        merchantSkillPoints = 0;
        fighterSkillPoints = 0;
        engineerSkillPoints = 0;
        ship = new Ship("Unnamed Ship", 6, new HashMap<>(), 100, 100);
        upgrades = new HashMap<>();
    }

    public static Player getInstance() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        ship.setName(name + "'s Ship");
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getPilotSkillPoints() {
        return pilotSkillPoints;
    }

    public void setPilotSkillPoints(int pilotSkillPoints) {
        this.pilotSkillPoints = pilotSkillPoints;
    }

    public int getMerchantSkillPoints() {
        return merchantSkillPoints;
    }

    public void setMerchantSkillPoints(int merchantSkillPoints) {
        this.merchantSkillPoints = merchantSkillPoints;
    }

    public int getFighterSkillPoints() {
        return fighterSkillPoints;
    }

    public void setFighterSkillPoints(int fighterSkillPoints) {
        this.fighterSkillPoints = fighterSkillPoints;
    }

    public int getEngineerSkillPoints() {
        return engineerSkillPoints;
    }

    public void setEngineerSkillPoints(int engineerSkillPoints) {
        this.engineerSkillPoints = engineerSkillPoints;
    }

    public int getMaxSkillPoints() {
        return maxSkillPoints;
    }

    public void setMaxSkillPoints(int difficulty) {
        maxSkillPoints = difficulty * -4 + 20;
        remainingPoints = maxSkillPoints;
    }

    public int getRemainingPoints() {
        return remainingPoints;
    }

    public void setRemainingPoints(int remainingPoints) {
        this.remainingPoints = remainingPoints;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
        MapScreen.refreshCreditsLabel();
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public HashMap<CharacterUpgrades, Integer> getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(HashMap<CharacterUpgrades, Integer> upgrades) {
        this.upgrades = upgrades;
    }

    public void incrementEngineer() {
        if (remainingPoints > 0) {
            engineerSkillPoints++;
            remainingPoints--;
        }
    }

    public void incrementFighter() {
        if (remainingPoints > 0) {
            fighterSkillPoints++;
            remainingPoints--;
        }
    }

    public void incrementMerchant() {
        if (remainingPoints > 0) {
            merchantSkillPoints++;
            remainingPoints--;
        }
    }

    public void incrementPilot() {
        if (remainingPoints > 0) {
            pilotSkillPoints++;
            remainingPoints--;
        }
    }

    public void decrementEngineer() {
        if (engineerSkillPoints > 0) {
            engineerSkillPoints--;
            remainingPoints++;
        }
    }

    public void decrementFighter() {
        if (fighterSkillPoints > 0) {
            fighterSkillPoints--;
            remainingPoints++;
        }
    }

    public void decrementMerchant() {
        if (merchantSkillPoints > 0) {
            merchantSkillPoints--;
            remainingPoints++;
        }
    }

    public void decrementPilot() {
        if (pilotSkillPoints > 0) {
            pilotSkillPoints--;
            remainingPoints++;
        }
    }

    public void resetPoints() {
        remainingPoints = maxSkillPoints;
        pilotSkillPoints = 0;
        merchantSkillPoints = 0;
        engineerSkillPoints = 0;
        fighterSkillPoints = 0;
    }

    public void updateSkills(CharacterUpgrades upgrade, int increase) {
        int power = upgrade.getPower();
        String skill = upgrade.getSkill();

        switch (skill) {
        case "Pilot":
            pilotSkillPoints += power * increase;
            break;
        case "Merchant":
            merchantSkillPoints += power * increase;
            break;
        case "Engineer":
            engineerSkillPoints += power * increase;
            break;
        default:
            fighterSkillPoints += power * increase;
            break;
        }

    }

    public void pay(double cost) {
        if (credits <= cost) {
            credits = 0;
        } else {
            credits = credits - cost;
        }
    }

    public static boolean getFleeing() {
        return fleeing;
    }

    public static void setFleeing(boolean bool) {
        fleeing = bool;
    }

    public static void resetPlayer() {
        player = null;
        player = new Player();
    }
}
