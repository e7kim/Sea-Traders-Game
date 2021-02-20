import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

public class IslandDisplay extends StackPane {

    private Label name;
    private Label distanceFromCurrent;
    private Label location;
    private Label techLevel;
    private Label bio;
    private Island island;

    public IslandDisplay(Island island) {

        this.island = island;
        this.setPrefSize(30, 30);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        island.setIslandDisplay(this);

        if (Island.getCurrentIsland() == island) {
            this.setStyle("-fx-background-color: green;");
        } else {
            this.setStyle("-fx-background-color: black;");
        }

        StackPane islandDescriptionName = new StackPane();
        islandDescriptionName.setPrefSize(230, 125);

        name = new Label("Name: Unknown");
        techLevel = new Label("Tech Level: Unknown");
        bio = new Label("Bio: Unknown");
        location = new Label("Pos: (" + island.getX() + ", " + island.getY() + ")");
        distanceFromCurrent = new Label("Distance: "
                + (int) island.getDistanceFrom(Island.getCurrentIsland()) + "m");

        bio.setWrapText(true);
        bio.setTextAlignment(TextAlignment.CENTER);

        VBox islandCharacteristics = new VBox();
        islandCharacteristics.getChildren().addAll(
                name, techLevel, bio, location, distanceFromCurrent);
        islandCharacteristics.setAlignment(Pos.CENTER);
        islandCharacteristics.setSpacing(3);

        islandDescriptionName.getChildren().addAll(islandCharacteristics);

        islandDescriptionName.setStyle("-fx-background-color: white;");

        Player p = Player.getInstance();
        Ship s = p.getShip();
        this.setOnMouseClicked(e -> {
            if (island.isVisible()
                    || island.getDistanceFrom(Island.getCurrentIsland()) <= 300
                    || (int) island.getDistanceFrom(Island.getCurrentIsland()) == smallestDist()) {
                Ship.useFuel((int) island.getDistanceFrom(Island.getCurrentIsland()));
                MapScreen.refreshFuelLabel();
                if (!island.equals(Island.getCurrentIsland())) {
                    encounter();
                    MapScreen.refreshHealthLabel();
                }
                if (s.getHealth() <= 0 || (s.getFuelCapacity() <= 0 && p.getCredits() <= 0)) {
                    SceneManager.setSceneMethod(8);
                } else if (!Player.getFleeing()) {
                    Island.getCurrentIsland().
                            getIslandDisplay().setStyle("-fx-background-color: purple;");
                    Island.setCurrentIsland(island);
                    island.setVisible(true);
                    this.setStyle("-fx-background-color: green;");
                    Map.updateIslands();
                    SceneManager.loadIslandDisplay();
                    SceneManager.setSceneMethod(5);
                }
                Player.setFleeing(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Please choose the nearest unvisited island to a visited island "
                                + "(from that visited island),"
                                + " a nearby island (<=300m), or an island you've already visited."
                );
                alert.showAndWait();
            }
        });

        Popup popup = new Popup();
        popup.getContent().add(islandDescriptionName);

        this.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                Bounds bnds = this.localToScreen(this.getLayoutBounds());
                double x = bnds.getMinX() - (islandDescriptionName.getWidth() / 2)
                        + (this.getWidth() / 2);
                double y = bnds.getMinY() - islandDescriptionName.getHeight();
                popup.show(this, x, y);
            } else {
                popup.hide();
            }
        });
    }

    public void update() {
        if (island.isVisible()) {
            name.setText("Name: " + island.getName());
            techLevel.setText("Tech Level: " + island.getTechLevel());
            bio.setText("Bio: " + island.getDescription());
        }
        location.setText("Pos: (" + island.getX() + ", " + island.getY() + ")");
        distanceFromCurrent.setText("Distance: "
                + (int) island.getDistanceFrom(Island.getCurrentIsland()) + "m");
    }

    // Finds the smallest distance from any visited island to any unvisited island.
    public int smallestDist() {
        int min = Integer.MAX_VALUE;
        for (Island island : Map.getIslands()) {
            if (island.isVisible()) {
                for (Island island2 : Map.getIslands()) {
                    if (!island2.isVisible() && island2.getDistanceFrom(island) < min) {
                        min = (int) island2.getDistanceFrom(island);
                    }
                }
            }
        }
        return min;
    }

    private void encounter() {

        Game game = Game.getInstance();
        double difficultyAspect = 0;

        if (game.getDifficulty() == 1) {
            difficultyAspect = .10;
        } else if (game.getDifficulty() == 2) {
            difficultyAspect = .15;
        }

        double chanceOfEncounter = Math.random() - difficultyAspect;
        Player p = Player.getInstance();
        Ship s = p.getShip();

        if (s.getNumItems() > 0) {

            if (chanceOfEncounter < .25) {
                EncounterPopup pirateEncounter = new EncounterPopup(0);
            } else if (chanceOfEncounter >= .25 && chanceOfEncounter < .50) {
                EncounterPopup marineEncounter = new EncounterPopup(2);
            } else if (chanceOfEncounter >= .50 && chanceOfEncounter < .75) {
                EncounterPopup traderEncounter = new EncounterPopup(1);

            }
        } else {

            if (chanceOfEncounter < .30 + difficultyAspect) {
                EncounterPopup pirateEncounter = new EncounterPopup(0);
            } else if (chanceOfEncounter >= .30 && chanceOfEncounter < .60) {
                EncounterPopup traderEncounter = new EncounterPopup(1);
            }
        }
    }

    public Island getIsland() {
        return island;
    }

}
