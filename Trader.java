import java.util.Random;

public class Trader {

    private int damage;
    private Item item;
    private int quantity;
    private double price;
    private boolean itemBought;
    private int amountRobbed = 0;
    private Random r = new Random();

    public Trader(int damage, Item item) {
        this.damage = damage;
        this.item = item;
        this.quantity = r.nextInt(5) + 1;
        this.price = item.getBuyingPrice();
    }

    public void buy() {
        Player p = Player.getInstance();
        Ship s = p.getShip();
        if (p.getCredits() >= price * quantity && s.getNumItems()
                + quantity <= s.getCargoCapacity()) {
            p.setCredits(p.getCredits() - price * quantity);
            for (int i = 0; i < quantity; i++) {
                s.addToInventory(item);
            }
            itemBought = true;
        }
    }

    public boolean rob() {
        Player p = Player.getInstance();
        Ship s = p.getShip();
        int probChance = (int) (Math.random() * 100) + 1;
        int winChance = p.getFighterSkillPoints() * 10;
        if (winChance > 85) {
            winChance = 85;
        }
        if (winChance > probChance && !s.capacityFull()) {
            int num = r.nextInt(quantity) + 1;
            for (int i = 0; i < num; i++) {
                s.addToInventory(item);
                amountRobbed += 1;
                if (s.capacityFull()) {
                    break;
                }
            }
            return true;
        } else {
            s.damageShip(damage);
            return false;
        }
    }

    public void negotiate() {
        Player p = Player.getInstance();
        int probChance = (int) (Math.random() * 100) + 1;
        int negotiateChance = p.getMerchantSkillPoints() * 10;
        if (negotiateChance > 85) {
            negotiateChance = 85;
        }
        if (negotiateChance > probChance) {
            price *= .5;
        } else {
            price *= 2;
        }
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public boolean getBought() {
        return itemBought;
    }

    public int getDamage() {
        return damage;
    }

    public int getAmountRobbed() {
        return amountRobbed;
    }
}
