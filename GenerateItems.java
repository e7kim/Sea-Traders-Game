import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GenerateItems {
    private static Item sail = new Item("Sail", 10);
    private static Item beefJerkey = new Item("Beef Jerky", 5);
    private static Item rum = new Item("Rum", 8);
    private static Item ale = new Item("Ale", 6);
    private static Item oars = new Item("Oars", 10);
    private static Item telescope = new Item("Telescope", 6);
    private static Item sailor = new Item("Sailor", 15);
    private static Item anchor = new Item("Anchor", 25);
    private static Item rope = new Item("Rope", 4);
    private static Item lifeboat = new Item("Life Boat", 30);
    private static Item knife = new Item("Knife", 3);
    private static Item fishingGear = new Item("Fishing Gear", 7);
    private static Item motor = new Item("Motor", 25);
    private static Item signalFlare = new Item("Signal Flare", 15);
    private static Item cannon = new Item("Cannon", 35);
    private static Item captain = new Item("Captain", 40);

    private static ArrayList<Item> basicLevelItems =
            new ArrayList<>(Arrays.asList(beefJerkey, rope, knife, fishingGear));
    private static ArrayList<Item> lowLevelItems =
            new ArrayList<>(Arrays.asList(telescope, signalFlare, rum, ale));
    private static ArrayList<Item> medLevelItems =
            new ArrayList<>(Arrays.asList(oars, anchor, lifeboat, sailor));
    private static ArrayList<Item> highLevelItems =
            new ArrayList<>(Arrays.asList(motor, cannon, sail, captain));

    private static ArrayList<CharacterUpgrades> basicUpgrades =
            CharacterUpgrades.getBasicCharacterUpgrades();
    private static ArrayList<CharacterUpgrades> goodUpgrades =
            CharacterUpgrades.getGoodCharacterUpgrades();
    private static ArrayList<CharacterUpgrades> topUpgrades =
            CharacterUpgrades.getTopCharacterUpgrades();
    private static ArrayList<CharacterUpgrades> bestUpgrades =
            CharacterUpgrades.getBestCharacterUpgrades();

    private static Random r = new Random();
    private static int r1 = 0;
    private static int r2 = 0;

    private static ArrayList<Item> items = new ArrayList<>();

    public static ArrayList<Item> getItemsForSale(int level) {
        if (level == 0) {
            return getBasicLevelItems();
        } else if (level == 1) {
            return getLowLevelItems();
        } else if (level == 2) {
            return getMedLevelItems();
        } else {
            return getHighLevelItems();
        }
    }

    public static ArrayList<Item> getBasicLevelItems() {
        //all four basic items
        return new ArrayList<>(basicLevelItems);
    }

    public static ArrayList<Item> getLowLevelItems() {
        //two low level + two basic
        setR1R2();
        items.add(basicLevelItems.get(r1));
        items.add(basicLevelItems.get(r2));
        items.add(lowLevelItems.get(r1));
        items.add(lowLevelItems.get(r2));
        return items;
    }

    public static ArrayList<Item> getMedLevelItems() {
        //two medium, 1 low, 1 basic
        setR1R2();
        items.add(basicLevelItems.get(r1));
        items.add(lowLevelItems.get(r2));
        items.add(medLevelItems.get(r1));
        items.add(medLevelItems.get(r2));
        return items;
    }

    public static ArrayList<Item> getHighLevelItems() {
        //two high, two med
        setR1R2();
        items.add(medLevelItems.get(r1));
        items.add(medLevelItems.get(r2));
        items.add(highLevelItems.get(r1));
        items.add(highLevelItems.get(r2));
        return items;
    }

    public static void setR1R2() {
        items = new ArrayList<>();
        r1 = r.nextInt(4);
        r2 = r.nextInt(4);
        while (r2 == r1) {
            r2 = r.nextInt(4);
        }
    }

    public static CharacterUpgrades getCharacterUpgrades(int level) {
        ArrayList<CharacterUpgrades> choices = new ArrayList<>();
        switch (level) {
        case 0:
            choices.addAll(basicUpgrades);
            break;
        case 1:
            choices.addAll(goodUpgrades);
            break;
        case 2:
            choices.addAll(topUpgrades);
            break;
        default:
            choices.addAll(bestUpgrades);
            break;
        }

        int num = r.nextInt(choices.size());

        return choices.get(num);
        
    }

    public static Item randItem() {
        int i = r.nextInt(4);
        int j = r.nextInt(4);
        Item item;
        switch (i) {
        case 0:
            item = basicLevelItems.get(j);
            break;
        case 1:
            item = lowLevelItems.get(j);
            break;
        case 2:
            item = medLevelItems.get(j);
            break;
        default:
            item = highLevelItems.get(j);
            break;
        }
        return item;
    }

}

