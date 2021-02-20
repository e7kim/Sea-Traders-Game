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
import java.util.List;

public class MarineScreen {
    public static Scene generateMarineScreen() {

        Marine marine = new Marine();

        VBox main = new VBox(50);
        main.setAlignment(Pos.CENTER);
        List<Item> demandedItems = marine.getDemandedItems();
        Label title = new Label("A marine is trying to confiscate " + demandedItems.toString());
        title.setFont(Font.font(16));

        HBox choices = new HBox(25);
        choices.setAlignment(Pos.CENTER);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        Button forfeit = new Button("Forfeit");
        forfeit.setOnAction(e -> {
            marine.forfeit();
            alert.setContentText("You forfeited the following items: " + demandedItems.toString());
            alert.showAndWait();
            ((Node) (e.getSource())).getScene().getWindow().hide();
        });

        Button flee = new Button("Flee");
        flee.setOnAction(e -> {
            if (marine.flee()) {
                alert.setContentText(("You successfully fled!"));
            } else {
                alert.setContentText("Your escape was unsuccessful."
                        + " Your ship took 20 damage, and you were fined 20 credits.");
            }

            alert.showAndWait();
            ((Node) (e.getSource())).getScene().getWindow().hide();
        });

        Button fight = new Button("Fight");
        fight.setOnAction(e -> {
            if (marine.fight()) {
                alert.setContentText("You won the fight and successfully fled!");
            } else {
                alert.setContentText("You lost the fight and forfeited the following items: "
                        + demandedItems.toString()
                        + "\n You also took 40 damage.");
            }

            alert.showAndWait();
            ((Node) (e.getSource())).getScene().getWindow().hide();
        });

        choices.getChildren().addAll(forfeit, flee, fight);
        main.getChildren().addAll(title, choices);

        return new Scene(main, 1025, 625);
    }
}
