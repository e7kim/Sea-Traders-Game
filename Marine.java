import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Marine {

    private List<Item> demandedItems;

    /**
     * Creates a Marine that demands a random number of 1-3 items from the player
     */
    public Marine() {
        demandedItems = new ArrayList<>();
        Ship s = Player.getInstance().getShip();
        Random r = new Random();
        List<Item> playerItems = new ArrayList<>(s.getItemInventory().keySet());
        int numItems = Math.min(playerItems.size(), r.nextInt(3) + 1);

        if (numItems == playerItems.size()) {
            demandedItems.addAll(playerItems);
        } else {
            for (int i = 0; i < numItems; i++) {
                int num = r.nextInt(s.getItemInventory().keySet().size());
                if (demandedItems.contains(playerItems.get(num))) {
                    i--;
                } else {
                    demandedItems.add(playerItems.get(num));
                }
            }
        }
    }

    public void forfeit() {
        Player p = Player.getInstance();
        Ship s = p.getShip();
        for (Item item : demandedItems) {
            s.removeFromInventory(item);
        }
    }

    public boolean flee() {
        Player p = Player.getInstance();
        Ship s = p.getShip();
        int probChance = (int) (Math.random() * 100) + 1;
        int fleeChance = p.getPilotSkillPoints() * 10;
        if (fleeChance > 75) {
            fleeChance = 75;
        }
        if (fleeChance <= probChance) {
            forfeit();
            s.damageShip(20);
            p.pay(20);
            return false;
        }
        p.setFleeing(true);
        return true;
    }

    public boolean fight() {
        Player p = Player.getInstance();
        Ship s = p.getShip();
        int probChance = (int) (Math.random() * 100) + 1;
        int winChance = p.getFighterSkillPoints() * 10;
        if (winChance > 80) {
            winChance = 80;
        }
        if (winChance <= probChance) {
            forfeit();
            s.damageShip(40);
            Player.setFleeing(true);
            return false;
        }
        return true;
    }

    public List<Item> getDemandedItems() {
        return demandedItems;
    }

}
