import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Map {

    private static int numIslands = 10;
    private static ArrayList<Island> islands = new ArrayList<>();
    private String[] names;
    private ArrayList<String> descriptions;

    public Map(int xLength, int yLength, int apart) {
        names = new String[numIslands];
        descriptions = new ArrayList<>(10);
        String[] techLevels = new String[]{"Basic", "Low", "Medium", "High"};
        createNamesAndDescriptions();

        Random r = new Random();
        int itemsRand = r.nextInt(techLevels.length);
        ArrayList<Item> items = GenerateItems.getItemsForSale(itemsRand);
        items.add(GenerateItems.getCharacterUpgrades(itemsRand));
        Island startingIsland = new Island(r.nextInt(xLength), r.nextInt(yLength),
                names[0], techLevels[itemsRand], descriptions.get(0), true, items);
        Island.setCurrentIsland(startingIsland);
        islands.add(startingIsland);
        for (int i = 0; i < 9; i++) {
            int x = r.nextInt(xLength);
            int y = r.nextInt(yLength);
            for (int j = 0; j < islands.size(); j++) {
                itemsRand = r.nextInt(techLevels.length);
                double distance = Math.sqrt(Math.pow(islands.get(j).getX() - x, 2)
                        + Math.pow(islands.get(j).getY() - y, 2));
                if (distance > apart && j == islands.size() - 1) {
                    ArrayList<Item> newItems = GenerateItems.getItemsForSale(itemsRand);
                    newItems.add(GenerateItems.getCharacterUpgrades(itemsRand));
                    islands.add(new Island(x, y, names[i + 1],
                            techLevels[itemsRand],
                            descriptions.get(i + 1), false, newItems));
                    break;
                } else if (distance < apart) {
                    j = -1;
                    x = r.nextInt(xLength);
                    y = r.nextInt(yLength);
                }
            }
        }

        Island lastIsland = islands.get(islands.size() - 1);
        lastIsland.setGood(lastIsland.getGoods().size() - 1, Item.getTreasureChest());
        System.out.println("Treasure chest located at: " + lastIsland);
    }

    private void createNamesAndDescriptions() {
        Scanner scanner = null;
        Scanner scanner2 = null;
        try {
            File file = new File("res/island_names.txt");
            File descriptionFile = new File("res/island_descriptions.txt");
            scanner = new Scanner(file);
            scanner2 = new Scanner(descriptionFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        ArrayList<String> allNames = new ArrayList<>();

        while (scanner.hasNext()) {
            allNames.add(scanner.nextLine());
        }

        Collections.shuffle(allNames);

        for (int i = 0; i < numIslands; i++) {
            names[i] = allNames.get(i);
        }

        while (scanner2.hasNextLine()) {
            descriptions.add(scanner2.nextLine());
        }
        Collections.shuffle(descriptions);
    }

    public static ArrayList<Island> getIslands() {
        return islands;
    }

    public static void updateIslands() {
        for (Island island : islands) {
            IslandDisplay id = island.getIslandDisplay();
            id.update();
        }
    }

    public static void clearIslands() {
        islands = new ArrayList<>();
    }
}
