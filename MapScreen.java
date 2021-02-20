import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MapScreen {
    private static Player player;
    private static Label health;
    private static Label credits;
    private static Label fuel;

    public static void generateMapScreen() {
        player = Player.getInstance();
        BorderPane mainScreen = new BorderPane();

        mainScreen.setStyle("-fx-background-color: #72bcd4");

        HBox top = new HBox(5);
        top.setAlignment(Pos.CENTER);

        Label shipName = new Label("Ship Name: " + player.getShip().getName());
        shipName.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        health = new Label("Health: " + player.getShip().getHealth());
        health.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        credits = new Label("Credits: " + player.getCredits());
        credits.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        fuel = new Label("Fuel Capacity: " + player.getShip().getFuelCapacity());
        fuel.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));



        HBox bottom = new HBox(25);
        bottom.setAlignment(Pos.CENTER);

        Button characterSheet = new Button("View Character Sheet");
        characterSheet.setOnAction(e -> SceneManager.setSceneMethod(4));

        Button toInventoryScreen = new Button("View Inventory/Upgrades");
        toInventoryScreen.setOnAction(e -> {
            SceneManager.setSceneMethod(7);
        });

        Pane map = new Pane();

        ArrayList<Island> islands = Map.getIslands();

        for (Island island : islands) {
            IslandDisplay islandDisplay = new IslandDisplay(island);

            islandDisplay.setLayoutX(island.getX() + 100);
            islandDisplay.setLayoutY(600 - (island.getY() + 83));

            map.getChildren().add(islandDisplay);
        }

        Map.updateIslands();
        mainScreen.setCenter(map);
        top.getChildren().addAll(shipName, health, credits, fuel);
        mainScreen.setTop(top);
        bottom.getChildren().addAll(characterSheet, toInventoryScreen);
        mainScreen.setBottom(bottom);

        SceneManager.modifyScene(3, new Scene(mainScreen, 1000, 600));

    }
    public static void refreshHealthLabel() {
        health.setText("Health: " + player.getShip().getHealth());
    }

    public static void refreshCreditsLabel() {
        credits.setText(String.format("Credits: %.2f", player.getCredits()));
    }

    public static void refreshFuelLabel() {
        fuel.setText("Fuel Capacity: " + player.getShip().getFuelCapacity());
    }
}
