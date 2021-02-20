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

public class PirateScreen {

    public static Scene generatePirateScreen() {

        Pirate pirate = createPirate();

        VBox main = new VBox(50);
        main.setAlignment(Pos.CENTER);
        Label title = new Label("A pirate is demanding " + pirate.getDemand() + " credits!");
        title.setFont(Font.font(16));

        HBox choices = new HBox(25);
        choices.setAlignment(Pos.CENTER);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        Button pay = new Button("Pay");
        pay.setOnAction(e -> {
            pirate.pay();
            if (pirate.getAmountPaid() == pirate.getDemand()) {
                alert.setContentText(String.format("You paid the pirate %d credits",
                        pirate.getDemand()));
            } else if (pirate.getRemoveOccured()) {
                alert.setContentText("You could not afford the pirate's "
                        + "demands so you gave up your remaining credits and all items");
            } else {
                alert.setContentText(String.format("You could not afford the pirate's demands "
                                + "and have no items to offer so you gave up your remaining"
                                + " credits and %d damage was dealt to your ship",
                        pirate.getDamage()));
            }

            alert.showAndWait();
            ((Node) (e.getSource())).getScene().getWindow().hide();
        });

        Button flee = new Button("Flee");
        flee.setOnAction(e -> {
            if (pirate.flee()) {
                alert.setContentText("You have escaped successfully!");
            } else {
                alert.setContentText(String.format("You were not able to escape successfully. "
                        + "You lost all of your credits, and your ship took %d damage",
                        pirate.getDamage()));
            }

            alert.showAndWait();
            ((Node) (e.getSource())).getScene().getWindow().hide();
        });

        Button fight = new Button("Fight");
        fight.setOnAction(e -> {
            if (pirate.fight()) {
                alert.setContentText(String.format("You won the fight! "
                        + "You win %d credits from the pirate.", pirate.getWonCredits()));
            } else {
                alert.setContentText(String.format("You lost the fight."
                        + " You lost all of your credits, and your ship took %d damage.",
                        pirate.getDamage()));
            }

            alert.showAndWait();
            ((Node) (e.getSource())).getScene().getWindow().hide();
        });

        choices.getChildren().addAll(pay, flee, fight);
        main.getChildren().addAll(title, choices);

        return new Scene(main, 1025, 625);
    }

    private static Pirate createPirate() {

        int demandValue = (int) (Math.random() * 15 + 5);
        int damageValue = (int) (Math.random() * 30 + 10);

        return new Pirate(demandValue, damageValue);

    }

}
