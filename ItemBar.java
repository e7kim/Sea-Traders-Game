import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;

public class ItemBar extends HBox {
    private Item item;
    private Label prices;
    private Player player = Player.getInstance();

    public ItemBar(Item item) {
        this.item = item;
        prices = new Label();
        setItemText();
        Button buy = new Button("Buy");
        Button sell = new Button("Sell");
        buy.setOnAction(e -> {
            addToInventoryHelper(item);
        });
        sell.setOnAction(e -> {
            removeFromInventoryHelper(item);
        });
        this.getChildren().addAll(buy, sell, prices);

        if (item instanceof CharacterUpgrades) {
            CharacterUpgrades upgrade = (CharacterUpgrades) item;
            Popup popup = new Popup();
            Label description = new Label(upgrade.getDescription());
            StackPane pane = new StackPane(description);
            pane.setPrefSize(300, 30);
            pane.setStyle("-fx-background-color: white;");
            popup.getContent().add(pane);

            this.hoverProperty().addListener((obs, oldVal, newValue) -> {
                if (newValue) {
                    Bounds bnds = this.localToScreen(this.getLayoutBounds());
                    double x = bnds.getMinX() - (pane.getWidth() / 2)
                            + (this.getWidth() / 2);
                    double y = bnds.getMinY() - pane.getHeight();
                    popup.show(this, x, y);
                } else {
                    popup.hide();
                }
            });
        }
    }

    public void setItemText() {
        prices.setText(String.format("%s : $%.2f | $%.2f", item.getName(),
                item.getBuyingPrice(), item.getSellingPrice()));
    }

    private void addToInventoryHelper(Item item) {
        if (player.getCredits() - item.getBuyingPrice() >= 0 && !player.getShip().capacityFull()) {
            if (item.equals(Item.getTreasureChest())) {
                System.out.println("Game Won");
                SceneManager.setSceneMethod(9);
            }
            player.getShip().addToInventory(item);
            player.setCredits(player.getCredits() - item.getBuyingPrice());
            MarketplaceScreen.updateInventoryLabel();
            MarketplaceScreen.updatePlayerCreditsLabel();
        }
    }

    private void removeFromInventoryHelper(Item item) {
        if (player.getShip().inventoryContains(item)) {
            player.getShip().removeFromInventory(item);
            player.setCredits(player.getCredits() + item.getSellingPrice());
            MarketplaceScreen.updateInventoryLabel();
            MarketplaceScreen.updatePlayerCreditsLabel();
            if (item instanceof CharacterUpgrades
                    && ((CharacterUpgrades) item).getSkill().equals("Merchant")) {
                MarketplaceScreen.updateItemBars();
            }
        }
    }
}
