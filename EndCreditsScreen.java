import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class EndCreditsScreen {
    public static void generateEndCreditsScreen() {
        VBox main = new VBox();
        Label credits = new Label("Created By: Eugene Kim, Sambhav Jaideep, Boris Gulev,"
                + " Aarun Srinivas, and Edward Jahoda");
        credits.setFont(new Font(16));
        Button newGame = new Button("New Game");
        newGame.setOnAction(e -> {
            Game.startNewGame();
            SceneManager.setSceneMethod(1);
        });

        main.setSpacing(15);

        main.getChildren().addAll(credits, newGame);
        main.setAlignment(Pos.CENTER);
        SceneManager.modifyScene(10, new Scene(main, 1000, 600));
    }
}
