import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Random;

public class TraderScreen {
    public static Scene generateTraderScreen() {
        Ship s = Player.getInstance().getShip();
        Trader trader = createTrader();

        VBox main = new VBox(50);
        main.setAlignment(Pos.CENTER);
        Label title = new Label("A trader has offered to sell items!");
        title.setFont(Font.font(16));

        HBox choices = new HBox(25);
        choices.setAlignment(Pos.CENTER);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        Button buy = new Button(String.format("Buy " + trader.getQuantity()
                + " " + trader.getItem().toString() + "s for $%.2f credits", trader.getPrice()));
        buy.setOnAction(e -> {
            trader.buy();
            if (trader.getBought()) {
                alert.setContentText("You bought the following items: \n"
                        + trader.getQuantity() + " "
                        + trader.getItem().toString() + String.format("s for $%.2f each",
                        trader.getPrice()));
            } else {
                if (s.capacityFull()) {
                    alert.setContentText("Items not bought - Cargo Capacity is full");
                } else {
                    alert.setContentText("Insufficient Credits - Items not bought");
                }
            }
            alert.showAndWait();
            ((Node) (e.getSource())).getScene().getWindow().hide();
        });

        Button ignore = new Button("Ignore");
        ignore.setOnAction(e -> {
            alert.setContentText("You ignore the traders and"
                    + " continue traveling to your destination");
            alert.showAndWait();
            ((Node) (e.getSource())).getScene().getWindow().hide();
        });

        Button rob = new Button("Rob");
        rob.setOnAction(e -> {
            if (trader.rob()) {
                alert.setContentText("You successfully robbed the trader and added "
                        + trader.getAmountRobbed() + " " + trader.getItem() + "s to your cargo");
            } else {
                alert.setContentText("You were unsuccessful in your attempt"
                        + " to rob the trader. The trader dealt "
                        + trader.getDamage() + " to your ship");
            }
            alert.showAndWait();
            ((Node) (e.getSource())).getScene().getWindow().hide();
        });

        Button negotiate = new Button("Negotiate");
        negotiate.setOnAction(e -> {
            trader.negotiate();
            buy.setText(String.format("Buy " + trader.getQuantity() + " "
                    + trader.getItem().toString() + "s for $%.2f credits", trader.getPrice()));
            choices.getChildren().remove(negotiate);
        });

        choices.getChildren().addAll(buy, ignore, rob, negotiate);
        main.getChildren().addAll(title, choices);

        return new Scene(main, 1025, 625);
    }

    private static Trader createTrader() {
        Random r = new Random();
        Item item = GenerateItems.randItem();
        return new Trader(r.nextInt(10) + 1, item);
    }

}
