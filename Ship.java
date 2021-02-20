import java.util.HashMap;

public class Ship {
    private String name;
    private static int cargoCapacity;
    private HashMap<Item, Integer> itemInventory;
    private static int fuelCapacity;
    private static int health;

    public Ship(String name, int cargoCapacity, HashMap<Item,
            Integer> itemInventory, int fuelCapacity, int health) {
        this.name = name;
        this.cargoCapacity = cargoCapacity;
        this.itemInventory = itemInventory;
        this.fuelCapacity = fuelCapacity;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int fuelCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public HashMap<Item, Integer> getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(HashMap<Item, Integer> itemInventory) {
        this.itemInventory = itemInventory;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean inventoryContains(Item item) {
        return itemInventory.containsKey(item);
    }

    public void addToInventory(Item key) {
        if (itemInventory.containsKey(key)) {
            int occurrences = itemInventory.get(key);
            itemInventory.put(key, occurrences + 1);
        } else {
            itemInventory.put(key, 1);
        }
        if (key instanceof CharacterUpgrades) {
            InventoryScreen.addItem((CharacterUpgrades) key);
        }

        InventoryScreen.updateInventoryLabel();
        InventoryScreen.updateCargoCapacityLabel();
    }

    public void removeFromInventory(Item key) {
        if (itemInventory.containsKey(key) && itemInventory.get(key) > 1) {
            itemInventory.put(key, itemInventory.get(key) - 1);
        } else if (itemInventory.containsKey(key)) {
            itemInventory.remove(key);
        }

        if (key instanceof CharacterUpgrades) {
            InventoryScreen.removeItem((CharacterUpgrades) key);
        }

        InventoryScreen.updateCargoCapacityLabel();
        InventoryScreen.updateInventoryLabel();
    }
    
    public void removeAllItems() {
        HashMap<Item, Integer> temp = (HashMap<Item, Integer>) itemInventory.clone();
        for (Item item : temp.keySet()) {
            while (itemInventory.containsKey(item)) {
                removeFromInventory(item);
            }
        }

        InventoryScreen.updateCargoCapacityLabel();
        InventoryScreen.updateInventoryLabel();
    }

    public boolean capacityFull() {
        return getNumItems() == cargoCapacity;
    }

    public int getNumItems() {
        int count = 0;
        for (Integer quantity : itemInventory.values()) {
            count += quantity;
        }
        return count;
    }

    public void damageShip(int damage) {
        if (health <= damage) {
            health = 0;
            SceneManager.setSceneMethod(8);
        } else {
            health = health - damage;
        }
    }

    public static void useFuel(int distance) {
        Player p = Player.getInstance();
        double efficiency = 1 - p.getPilotSkillPoints() / 44;
        fuelCapacity -= (distance / 25) * efficiency;
    }
}
