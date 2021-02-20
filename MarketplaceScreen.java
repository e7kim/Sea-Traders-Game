import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MarketplaceScreen {
    private static ArrayList<ItemBar> itemBars;
    private static Label inventoryLabel = new Label();
    private static Label playerCredits = new Label();

    public static void generateMarketplaceScreen(Island island) {
        ArrayList<Item> goodsForSale = island.getGoods();

        VBox main = new VBox(10);

        updatePlayerCreditsLabel();
        updateInventoryLabel();

        int buttonSpacing = 10;

        main.getChildren().addAll(playerCredits);

        itemBars = new ArrayList<>();
        for (Item item : goodsForSale) {
            ItemBar itemBar = new ItemBar(item);
            itemBars.add(itemBar);
            itemBar.setSpacing(buttonSpacing);
            itemBar.setAlignment(Pos.CENTER);
            main.getChildren().add(itemBar);
        }

        Player p = Player.getInstance();
        Ship s = p.getShip();

        Button refuel = new Button("Refuel");
        refuel.setOnAction(e -> {
            int remaining = 100 - s.getFuelCapacity();
            double amount = Math.min(remaining, p.getCredits());
            s.setFuelCapacity(s.getFuelCapacity() + (int) amount);
            p.pay(amount);
            updatePlayerCreditsLabel();
            MapScreen.refreshFuelLabel();
            MapScreen.refreshCreditsLabel();
        });

        Button repair = new Button("Repair");
        repair.setOnAction(e -> {
            int remaining = 100 - s.getHealth();
            double amount = Math.min(remaining, p.getCredits() + p.getEngineerSkillPoints());
            s.setHealth(s.getHealth() + (int) amount);
            p.pay(Math.max(amount - p.getEngineerSkillPoints(), 0));
            updatePlayerCreditsLabel();
            MapScreen.refreshHealthLabel();
            MapScreen.refreshCreditsLabel();
        });

        Button backToIslandLandingScreen = new Button("Back to Island");
        backToIslandLandingScreen.setOnAction(e -> SceneManager.setSceneMethod(5));

        main.getChildren().addAll(inventoryLabel, refuel, repair, backToIslandLandingScreen);
        main.setAlignment(Pos.CENTER);

        SceneManager.modifyScene(6, new Scene(main, 1000, 600));
    }

    public static void updateItemBars() {
        for (ItemBar itemBar : itemBars) {
            itemBar.setItemText();
        }
    }

    public static void updateInventoryLabel() {
        Player p = Player.getInstance();
        Ship s = p.getShip();
        inventoryLabel.setText("Inventory: " + s.getItemInventory().toString()
                + ". Cargo Capacity: " + (s.getCargoCapacity() - s.getNumItems()));
    }

    public static void updatePlayerCreditsLabel() {
        playerCredits.setText(String.format(
                "Credits Remaining: %.2f", Player.getInstance().getCredits()));
    }

}
