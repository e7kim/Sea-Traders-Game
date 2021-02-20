import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


class CharacterSheetScreen {

    private static Label name;
    private static Label difficulty;
    private static Label pilotPoints;
    private static Label fighterPoints;
    private static Label merchantPoints;
    private static Label engineerPoints;
    private static Label credits;
    private static VBox mainScreen;

    static void generateCharacterSheetScreen() {

        Player player = Player.getInstance();
        mainScreen = new VBox(15);
        //Set Background of Main screen
        BackgroundImage myBI = null;

        try {
            myBI = new BackgroundImage(new Image(new FileInputStream("res/SeaBackground.png")),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        mainScreen.setBackground(new Background(myBI));

        name = new Label();
        double fontSize = 16.0;
        name.setFont(new Font(fontSize));
        difficulty = new Label();
        difficulty.setFont(new Font(fontSize));
        pilotPoints = new Label();
        pilotPoints.setFont(new Font(fontSize));
        fighterPoints = new Label();
        fighterPoints.setFont(new Font(fontSize));
        merchantPoints = new Label();
        merchantPoints.setFont(new Font(fontSize));
        engineerPoints = new Label();
        engineerPoints.setFont(new Font(fontSize));
        credits = new Label();
        credits.setFont(new Font(fontSize));

        Button back = new Button("Back to Map");
        back.setOnAction(e -> SceneManager.setSceneMethod(3));

        mainScreen.getChildren().addAll(name, difficulty, pilotPoints, fighterPoints,
                merchantPoints, engineerPoints, credits, back);

        mainScreen.setAlignment(Pos.CENTER);

        SceneManager.modifyScene(4, new Scene(mainScreen, 1000, 600));
    }

    public static void update() {
        Player player = Player.getInstance();
        name.setText("Player name: " + player.getName());
        difficulty.setText("Difficulty: " + player.getDifficulty());
        pilotPoints.setText("Pilot points: " + player.getPilotSkillPoints());
        fighterPoints.setText("Fighter points: " + player.getFighterSkillPoints());
        merchantPoints.setText("Merchant points: " + player.getMerchantSkillPoints());
        engineerPoints.setText("Engineer points: " + player.getEngineerSkillPoints());
        credits.setText(String.format("Credits: %.2f", Player.getInstance().getCredits()));
    }
}
