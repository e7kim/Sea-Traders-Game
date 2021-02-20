import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InventoryScreen {

    private static Label cargoCapacity;
    private static Label inventory;
    private static VBox equipItems;

    public static void generateInventoryScreen() {

        Game game = Game.getInstance();
        inventory = new Label();
        cargoCapacity = new Label();
        equipItems = new VBox(10);

        VBox main = new VBox(10);


        HBox inventoryContainer = new HBox();

        updateCargoCapacityLabel();
        updateInventoryLabel();

        inventoryContainer.getChildren().addAll(inventory, cargoCapacity);
        inventoryContainer.setAlignment(Pos.CENTER);

        Button backToMap = new Button("Back to Map");
        backToMap.setOnAction(e -> SceneManager.setSceneMethod(3));

        main.getChildren().addAll(inventoryContainer, equipItems, backToMap);

        main.setAlignment(Pos.CENTER);

        SceneManager.modifyScene(7, new Scene(main, 1000, 600));

    }

    public static void updateInventoryLabel() {
        inventory.setText("Inventory: "
                + Player.getInstance().getShip().getItemInventory().toString() + ". ");
    }

    public static void updateCargoCapacityLabel() {
        Ship s = Player.getInstance().getShip();
        cargoCapacity.setText("Cargo Capacity: " + (s.getCargoCapacity() - s.getNumItems()));
    }

    public static void addItem(CharacterUpgrades upgrade) {
        equipItems.getChildren().add(new EquipBar(upgrade));
    }

    public static void removeItem(CharacterUpgrades upgrade) {
        for (Node node : equipItems.getChildren()) {
            if (node instanceof EquipBar) {
                EquipBar bar = (EquipBar) node;
                CharacterUpgrades thisUpgrade = (bar).getUpgrade();
                if (upgrade == thisUpgrade) {
                    if (bar.getEquipped()) {
                        Player.getInstance().updateSkills(upgrade, -1);
                    }
                    equipItems.getChildren().remove(node);
                    return;
                }
            }
        }
    }

}
