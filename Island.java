import java.util.ArrayList;

public class Island {

    private static Island currentIsland;
    private int x;
    private int y;
    private String name;
    private String techLevel;
    private String description;
    private boolean visible;
    private IslandDisplay id;
    private int smallestDistance;
    private ArrayList<Item> goods;

    public Island(int x, int y, String name, String techLevel,
                  String description, boolean visible, ArrayList<Item> goods) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.techLevel = techLevel;
        this.description = description;
        this.visible = visible;
        this.goods = goods;
    }

    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getTechLevel() {
        return techLevel;
    }

    public String getDescription() {
        return description;
    }

    public static void setCurrentIsland(Island island) {
        currentIsland = island;
    }

    public static Island getCurrentIsland() {
        return currentIsland;
    }

    public double getDistanceFrom(Island island) {
        return Math.sqrt(Math.pow(x - island.x, 2) + Math.pow(y - island.y, 2));
    }

    public void setIslandDisplay(IslandDisplay id) {
        this.id = id;
    }

    public IslandDisplay getIslandDisplay() {
        return id;
    }

    public String toString() {
        return name;
    }

    public ArrayList<Item> getGoods() {
        return goods;
    }

    public void setGood(int index, Item item) {
        goods.set(index, item);
    }
}
