import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class IslandLandingScreen {

    private static Label position;
    private static Label name;
    private static Label techLevel;
    private static Label description;
    private static VBox main;

    public static void generateIslandLandingScreen(Island island) {

        main = new VBox(15);
        position = new Label("Position: (" + Island.getCurrentIsland().getX()
                + ", " + Island.getCurrentIsland().getY() + ")");
        name = new Label("Name: " + Island.getCurrentIsland().getName());
        techLevel = new Label("Technology Level: " + Island.getCurrentIsland().getTechLevel());
        description = new Label("Description: " + Island.getCurrentIsland().getDescription());

        island.setVisible(true);

        Button toMarketplace = new Button("Marketplace");
        SceneManager.loadMarketplaceDisplay();
        toMarketplace.setOnAction(e -> SceneManager.setSceneMethod(6));

        Button backToMap = new Button("Back to Map");
        backToMap.setOnAction(e -> SceneManager.setSceneMethod(3));

        main.getChildren().addAll(name, techLevel, description, position, toMarketplace, backToMap);
        main.setAlignment(Pos.CENTER);

        SceneManager.modifyScene(5, new Scene(main, 1000, 600));

    }

    public static void update(Island island) {

        position = new Label("Position: (" + island.getX() + ", " + island.getY() + ")");
        name = new Label("Name: " + island.getName());
        techLevel = new Label("Technology Level: " + island.getTechLevel());
        description = new Label("Description: " + island.getDescription());

    }

}
