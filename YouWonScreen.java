import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class YouWonScreen {
    public static void generateYouWonScreen() {
        VBox main = new VBox();
        Label youWon = new Label("You Win");
        youWon.setFont(new Font(16));
        Button endCredits = new Button("End Credits");
        endCredits.setOnAction(e -> {
            SceneManager.setSceneMethod(10);
        });

        main.setSpacing(15);

        main.getChildren().addAll(youWon, endCredits);
        main.setAlignment(Pos.CENTER);
        SceneManager.modifyScene(9, new Scene(main, 1000, 600));
    }
}
