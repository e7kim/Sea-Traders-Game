import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class GameOverScreen {

    public static void generateGameOverScreen() {

        VBox main = new VBox();
        Label gameOver = new Label("Game over");
        gameOver.setFont(new Font(16));
        Button endCredits = new Button("End Credits");
        endCredits.setOnAction(e -> {
            SceneManager.setSceneMethod(10);
        });

        main.setSpacing(15);

        main.getChildren().addAll(gameOver, endCredits);
        main.setAlignment(Pos.CENTER);
        SceneManager.modifyScene(8, new Scene(main, 1000, 600));
    }

}
