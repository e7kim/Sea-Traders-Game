import java.util.Objects;

public class Item {
    private String name;
    private double basePrice;
    private double maxMerchantSkill = 20 + Player.getInstance().getShip().getCargoCapacity() * 4;
    private Player player = Player.getInstance();
    private static Item treasureChest = new Item("TREASURE CHEST!!", 45);

    public Item(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }
    
    public double getBuyingPrice() {
        double inflation = 0;
        Game game = Game.getInstance();
        if (game.getDifficulty() == 1) {
            inflation += 5;
        } else if (game.getDifficulty() == 2) {
            inflation += 10;
        }
        return (basePrice + inflation)
                * (1 - player.getMerchantSkillPoints() / (2 * maxMerchantSkill));
    }
    
    public double getSellingPrice() {
        return getBuyingPrice()
                * (.5 + player.getMerchantSkillPoints() / (2 * maxMerchantSkill));
    }

    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, basePrice, maxMerchantSkill, player);
    }

    public static Item getTreasureChest() {
        return treasureChest;
    }
}
