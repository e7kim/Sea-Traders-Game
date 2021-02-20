import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


class InitConfigScreen {

    private static ToggleGroup difficultyButtons;
    private static TextField nameText;
    private static VBox mainScreen = new VBox(25);
    private static Label pilotText;
    private static Label fighterText;
    private static Label merchantText;
    private static Label engineerText;
    private static Label skillPointsLabel;

    static void generateInitConfigScreen() {
        Game game = Game.getInstance();
        Player player = Player.getInstance();
        //Set Background of Main screen
        BackgroundImage myBI = null;

        try {
            myBI = new BackgroundImage(
                    new Image(new FileInputStream("res/SeaBackground.png")),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        mainScreen.setBackground(new Background(myBI));

        HBox nameSelect = new HBox(); //creating HBox for name selection stuff

        nameText = new TextField();
        nameText.setPromptText("Enter name here");
        nameText.setFocusTraversable(false);

        nameSelect.getChildren().add(nameText);
        nameSelect.setAlignment(Pos.CENTER);
        //the layout is all temporary for now, we can change it later

        HBox navigation = new HBox(50);

        Button next = new Button("Next"); //when pressed, should go to the "character sheet screen"

        next.setOnAction(e -> {
            String name = nameText.getText();
            if (name.equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Please enter a valid non-empty name.");
                alert.showAndWait();
            } else if (player.getRemainingPoints() != 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Please allocate all available skill points.");
                alert.showAndWait();
            } else {
                player.setName(nameText.getText());
                switch (Player.getInstance().getDifficulty()) {
                case "Easy":
                    player.setCredits(150);
                    break;
                case "Medium":
                    player.setCredits(100);
                    break;
                case "Hard":
                    player.setCredits(50);
                    break;
                default:
                    break;
                }
                nameText.setText("");
                fighterText.setText("Fighter: 0");
                pilotText.setText("Pilot: 0");
                merchantText.setText("Merchant: 0");
                engineerText.setText("Engineer: 0");
                skillPointsLabel.setText("Skill Points: " + player.getMaxSkillPoints());
                MapScreen.generateMapScreen();
                SceneManager.setSceneMethod(3);
            }

        });

        navigation.getChildren().addAll(next);
        navigation.setAlignment(Pos.CENTER);

        mainScreen.getChildren().add(nameSelect); //adding all HBox modules to the main screen
        createSkillPointButtons();
        mainScreen.getChildren().add(navigation);
        mainScreen.setAlignment(Pos.CENTER);

        SceneManager.modifyScene(2, new Scene(mainScreen, 1000, 600));
    }

    private static void createSkillPointButtons() {
        Game game = Game.getInstance();
        Player player = Player.getInstance();
        skillPointsLabel = new Label("Skill Points: " + player.getRemainingPoints());
        double skillPointsTotalFontSize = 16.0;
        skillPointsLabel.setFont(new Font(skillPointsTotalFontSize));

        HBox pilotAttribute = new HBox(26); //pilot attribute configuration
        pilotText = new Label("Pilot: 0");
        //Font sizes for the various parts of the screen
        double attributeFontSize = 14.0;
        pilotText.setFont(new Font(attributeFontSize));
        Button pilotMinusAttribute = new Button("-");
        Button pilotPlusAttribute = new Button("+");
        pilotAttribute.getChildren().addAll(pilotMinusAttribute, pilotText, pilotPlusAttribute);
        pilotAttribute.setAlignment(Pos.CENTER);

        pilotMinusAttribute.setOnAction(e -> {
            player.decrementPilot();
            skillPointsLabel.setText("Skill Points: " + player.getRemainingPoints());
            pilotText.setText("Pilot: " + player.getPilotSkillPoints());
        });

        pilotPlusAttribute.setOnAction(e -> {
            player.incrementPilot();
            skillPointsLabel.setText("Skill Points: " + player.getRemainingPoints());
            pilotText.setText("Pilot: " + player.getPilotSkillPoints());
        });

        HBox fighterAttribute = new HBox(18); //fighter attribute configuration
        fighterText = new Label("Fighter: 0");
        fighterText.setFont(new Font(attributeFontSize));
        Button fighterMinusAttribute = new Button("-");
        Button fighterPlusAttribute = new Button("+");
        fighterAttribute.getChildren().addAll(fighterMinusAttribute,
                fighterText, fighterPlusAttribute);
        fighterAttribute.setAlignment(Pos.CENTER);

        fighterMinusAttribute.setOnAction(e -> {
            player.decrementFighter();
            skillPointsLabel.setText("Skill Points: " + player.getRemainingPoints());
            fighterText.setText("Fighter: " + player.getFighterSkillPoints());
        });

        fighterPlusAttribute.setOnAction(e -> {
            player.incrementFighter();
            skillPointsLabel.setText("Skill Points: " + player.getRemainingPoints());
            fighterText.setText("Fighter: " + player.getFighterSkillPoints());
        });

        HBox merchantAttribute = new HBox(10); //merchant attribute configuration
        merchantText = new Label("Merchant: 0");
        merchantText.setFont(new Font(attributeFontSize));
        Button merchantMinusAttribute = new Button("-");
        Button merchantPlusAttribute = new Button("+");
        merchantAttribute.getChildren().addAll(merchantMinusAttribute,
                merchantText, merchantPlusAttribute);
        merchantAttribute.setAlignment(Pos.CENTER);

        merchantMinusAttribute.setOnAction(e -> {
            player.decrementMerchant();
            skillPointsLabel.setText("Skill Points: " + player.getRemainingPoints());
            merchantText.setText("Merchant: " + player.getMerchantSkillPoints());
        });

        merchantPlusAttribute.setOnAction(e -> {
            player.incrementMerchant();
            skillPointsLabel.setText("Skill Points: " + player.getRemainingPoints());
            merchantText.setText("Merchant: " + player.getMerchantSkillPoints());
        });

        HBox engineerAttribute = new HBox(12); //engineer attribute configuration
        engineerText = new Label("Engineer: 0");
        engineerText.setFont(new Font(attributeFontSize));
        Button engineerMinusAttribute = new Button("-");
        Button engineerPlusAttribute = new Button("+");
        engineerAttribute.getChildren().addAll(engineerMinusAttribute,
                engineerText, engineerPlusAttribute);
        engineerAttribute.setAlignment(Pos.CENTER);

        engineerMinusAttribute.setOnAction(e -> {
            player.decrementEngineer();
            skillPointsLabel.setText("Skill Points: " + player.getRemainingPoints());
            engineerText.setText("Engineer: " + player.getEngineerSkillPoints());
        });

        engineerPlusAttribute.setOnAction(e -> {
            player.incrementEngineer();
            skillPointsLabel.setText("Skill Points: " + player.getRemainingPoints());
            engineerText.setText("Engineer: " + player.getEngineerSkillPoints());
        });

        VBox difficulty = new VBox(10); //creating VBox to house all difficulty stuff

        Label difficultyLabel = new Label("Select Difficulty: ");

        difficultyButtons = new ToggleGroup();

        RadioButton easyDifficulty = new RadioButton("Easy");
        easyDifficulty.setToggleGroup(difficultyButtons);

        RadioButton mediumDifficulty = new RadioButton("Medium");
        mediumDifficulty.setToggleGroup(difficultyButtons);
        mediumDifficulty.setSelected(true);

        RadioButton hardDifficulty = new RadioButton("Hard");
        hardDifficulty.setToggleGroup(difficultyButtons);

        easyDifficulty.setOnAction(e -> {
            player.setDifficulty("Easy");
            player.setMaxSkillPoints(0);
            player.resetPoints();
            game.setDifficulty(0);
            skillPointsLabel.setText("Skill Points: " + player.getRemainingPoints());
            pilotText.setText("Pilot: 0");
            fighterText.setText("Fighter: 0");
            merchantText.setText("Merchant: 0");
            engineerText.setText("Engineer: 0");
        });

        mediumDifficulty.setOnAction(e -> {
            player.setDifficulty("Medium");
            player.setMaxSkillPoints(1);
            player.resetPoints();
            game.setDifficulty(1);
            skillPointsLabel.setText("Skill Points: " + player.getRemainingPoints());
            pilotText.setText("Pilot: 0");
            fighterText.setText("Fighter: 0");
            merchantText.setText("Merchant: 0");
            engineerText.setText("Engineer: 0");
        });

        hardDifficulty.setOnAction(e -> {
            player.setDifficulty("Hard");
            player.setMaxSkillPoints(2);
            player.resetPoints();
            game.setDifficulty(2);
            skillPointsLabel.setText("Skill Points: " + player.getRemainingPoints());
            pilotText.setText("Pilot: 0");
            fighterText.setText("Fighter: 0");
            merchantText.setText("Merchant: 0");
            engineerText.setText("Engineer: 0");
        });

        difficulty.getChildren().addAll(difficultyLabel, easyDifficulty,
                mediumDifficulty, hardDifficulty);
        difficulty.setAlignment(Pos.CENTER);
        mainScreen.getChildren().addAll(difficulty, skillPointsLabel, pilotAttribute,
                fighterAttribute, merchantAttribute, engineerAttribute);
    }

    public static void updateScreen() {
        mainScreen.getChildren().removeAll(mainScreen.getChildren());
        Game game = Game.getInstance();
        Player player = Player.getInstance();
        //Set Background of Main screen
        BackgroundImage myBI = null;

        try {
            myBI = new BackgroundImage(
                    new Image(new FileInputStream("res/SeaBackground.png")),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        mainScreen.setBackground(new Background(myBI));

        HBox nameSelect = new HBox(); //creating HBox for name selection stuff

        nameText = new TextField();
        nameText.setPromptText("Enter name here");
        nameText.setFocusTraversable(false);

        nameSelect.getChildren().add(nameText);
        nameSelect.setAlignment(Pos.CENTER);
        //the layout is all temporary for now, we can change it later

        HBox navigation = new HBox(50);

        Button next = new Button("Next"); //when pressed, should go to the "character sheet screen"

        next.setOnAction(e -> {
            String name = nameText.getText();
            if (name.equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Please enter a valid non-empty name.");
                alert.showAndWait();
            } else if (player.getRemainingPoints() != 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Please allocate all available skill points.");
                alert.showAndWait();
            } else {
                player.setName(nameText.getText());
                switch (Player.getInstance().getDifficulty()) {
                case "Easy":
                    player.setCredits(150);
                    break;
                case "Medium":
                    player.setCredits(100);
                    break;
                case "Hard":
                    player.setCredits(50);
                    break;
                default:
                    break;
                }
                MapScreen.generateMapScreen();
                SceneManager.setSceneMethod(3);
            }

        });

        navigation.getChildren().addAll(next);
        navigation.setAlignment(Pos.CENTER);

        mainScreen.getChildren().add(nameSelect); //adding all HBox modules to the main screen
        createSkillPointButtons();
        mainScreen.getChildren().add(navigation);
        mainScreen.setAlignment(Pos.CENTER);

    }
}
