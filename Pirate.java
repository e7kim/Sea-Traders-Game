import java.util.Random;

public class Pirate {
    private int demand;
    private int damage;
    private int wonCredits;
    private int amountPaid = 0;
    private boolean removeOccured;
    private Random rand = new Random();

    public Pirate(int demand, int damage) {
        this.demand = demand;
        this.damage = damage;
        wonCredits = rand.nextInt(50);
    }

    public void pay() {
        Player p = Player.getInstance();
        Ship s = p.getShip();
        double currentCredits = p.getCredits();
        if (currentCredits >= demand) {
            p.setCredits(currentCredits - demand);
            amountPaid = demand;
        } else {
            p.setCredits(0);
            if (s.getNumItems() > 0) {
                s.removeAllItems();
                removeOccured = true;
            } else {
                s.damageShip(damage);
            }
        }
    }

    public boolean flee() {
        Player p = Player.getInstance();
        Ship s = p.getShip();
        int probChance = (int) (Math.random() * 100) + 1;
        int fleeChance = p.getPilotSkillPoints() * 10;
        if (fleeChance > 85) {
            fleeChance = 85;
        }
        if (fleeChance <= probChance) {
            p.setCredits(0);
            s.damageShip(damage);
            return false;
        } else {
            p.setFleeing(true);
            return true;
        }
    }

    public boolean fight() {
        Player p = Player.getInstance();
        Ship s = p.getShip();
        int probChance = (int) (Math.random() * 100) + 1;
        int winChance = p.getFighterSkillPoints() * 10;
        if (winChance > 90) {
            winChance = 90;
        }
        if (winChance > probChance) {
            p.setCredits(p.getCredits() + wonCredits);
            return true;
        } else {
            s.damageShip(damage);
            p.setCredits(0);
            p.setFleeing(true);
            return false;
        }
    }

    public int getDemand() {
        return demand;
    }

    public int getDamage() {
        return damage;
    }

    public int getWonCredits() {
        return wonCredits;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public boolean getRemoveOccured() {
        return removeOccured;
    }
}
