import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;

import java.util.HashMap;

public class EquipBar extends HBox {

    private boolean equipped;
    private CharacterUpgrades upgrade;
    
    public EquipBar(CharacterUpgrades upgrade) {

        Player player = Player.getInstance();
        this.upgrade = upgrade;
        Label itemName = new Label(upgrade.getName());
        Button equip = new Button("Equip Item");
        HashMap<CharacterUpgrades, Integer> upgrades = Player.getInstance().getUpgrades();

        equip.setOnAction(e -> {
            if (equipped) {
                if (upgrades.containsKey(upgrade) && upgrades.get(upgrade) > 1) {
                    upgrades.put(upgrade, upgrades.get(upgrade) - 1);
                } else if (upgrades.containsKey(upgrade)) {
                    upgrades.remove(upgrade);
                }
                
                equipped = false;
                equip.setText("Equip Item");
                player.updateSkills(upgrade, -1);
            } else {
                if (upgrades.containsKey(upgrade)) {
                    int occurrences = upgrades.get(upgrade);
                    upgrades.put(upgrade, occurrences + 1);
                } else {
                    upgrades.put(upgrade, 1);
                }

                equipped = true;
                equip.setText("Unequip Item");
                player.updateSkills(upgrade, 1);
            }
        });

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

        this.getChildren().addAll(itemName, equip);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);

    }

    public boolean getEquipped() {
        return equipped; 
    }

    public CharacterUpgrades getUpgrade() {
        return upgrade;
    }
}
