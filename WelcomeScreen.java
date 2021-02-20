import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class WelcomeScreen {

    private static final int HEIGHT = 600;
    private static final int WIDTH = 1000;

    static void generateWelcomeScreen() {
        Game game = Game.getInstance();
        game.getMusic().playHornpipe();

        BorderPane pane = new BorderPane();
        VBox vbox = new VBox(10);
        pane.setCenter(vbox);

        Button startButton = new Button("Start New Game");
        startButton.setStyle("-fx-background-color: #C0E8F6;"
                + "-fx-font-size: 18px; -fx-text-fill: #0F52BA;"
                + " -fx-border-color: #C0E8F6; -fx-border-width: 7px;");

        FileInputStream titleFile = null;
        FileInputStream pixelShipFile = null;
        BackgroundImage myBI = null;

        try {
            titleFile = new FileInputStream("res/TitleFont.png");
            pixelShipFile = new FileInputStream("res/PixelPirateShipEdited.png");
            myBI = new BackgroundImage(new Image(new FileInputStream(
                    "res/SeaBackground.png")), BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        ImageView title = new ImageView(new Image(titleFile));
        ImageView ship = new ImageView(new Image(pixelShipFile));
        vbox.setBackground(new Background(myBI));

        HBox shipContainer = new HBox(ship);

        ship.setPreserveRatio(true);
        ship.setFitHeight(200);
        shipContainer.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(title, startButton, shipContainer);

        Button muteMusic = new Button("Toggle Mute");
        muteMusic.setOnAction(e -> {
            if (!game.getIsMusicMuted()) {
                game.getMusic().stopHornpipe();
                game.setIsMusicMuted(true);
            } else {
                game.getMusic().playHornpipe();
                game.setIsMusicMuted(false);
            }
        });
        vbox.getChildren().add(muteMusic);





        Button skipConfiguration = new Button("For Developers Only: Skip Character Creation");
        skipConfiguration.setOnAction(e -> {
            if (!game.getIsMusicMuted()) {
                game.getMusic().stopHornpipe();
                game.getMusic().playTomfoolery();
            }
            MapScreen.generateMapScreen();
            SceneManager.setSceneMethod(3);
        });
        vbox.getChildren().add(skipConfiguration);





        vbox.setAlignment(Pos.CENTER);

        startButton.setOnAction(e -> {
            if (!game.getIsMusicMuted()) {
                game.getMusic().stopHornpipe();
                game.getMusic().playTomfoolery();
            }
            SceneManager.setSceneMethod(2);
        });

        SceneManager.modifyScene(1, new Scene(pane, WIDTH, HEIGHT));


    }

}
