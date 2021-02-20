import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * STEPS TO CREATE A NEW SCENE
 * 1)  Created a new class with the JavaFX code in a static method named
 *     generate[ClassName]Screen to save the scene to its variable at the end of the
 *     code use a line of code like the following
 *     SceneManager.modifyScene(1, new Scene(pane, WIDTH, HEIGHT));
 *
 *     to navigate to a different scene use a line of code similar to
 *          startButton.setOnAction(e -> {
 *             SceneManager.setSceneMethod(2);
 *         });
 * 2) Load it in start method of this class
 * 3) Create variable Scene s_
 * 4) update modifyScene and setScene method
 */


public class SceneManager extends Application {
    private static Scene s1; //Welcome Screen
    private static Scene s2; //InitConfig
    private static Scene s3; //Map
    private static Scene s4; //CharacterSheetScreen
    private static Scene s5; //IslandLandingScreen
    private static Scene s6; //MarketplaceScreen
    private static Scene s7; //InventoryScreen
    private static Scene s8; //GameOverScreen
    private static Scene s9; //YouWonScreen
    private static Scene s10; //EndCreditsScene
    private static Stage primaryStage;


    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        /*Load all the scenes and set the first screen displayed to
        the welcome screen. This initializes all button
        setOnActions method so that those will work*/

        WelcomeScreen.generateWelcomeScreen();
        InitConfigScreen.generateInitConfigScreen();
        MapScreen.generateMapScreen();
        CharacterSheetScreen.generateCharacterSheetScreen();
        InventoryScreen.generateInventoryScreen();
        GameOverScreen.generateGameOverScreen();
        YouWonScreen.generateYouWonScreen();
        EndCreditsScreen.generateEndCreditsScreen();

        //loading Island display from inside island landing screen
        //loading marketplace display from inside island landing screen

        stage.setScene(s1);
        stage.setTitle("Deep Sea Traders");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Used to set the scene from the classes that contain the layout file
     * ie: WelcomeScreen.java will call modifyScene method to change one of the scenes
     * @param number which scene to change - s1 corresponds to 1, s2 to 2, s3 to 3...
     * @param scene the scene containing all the elements for the specific layouts
     */
    static void modifyScene(int number, Scene scene) {
        switch (number) {
        case 1:
            s1 = scene;
            break;
        case 2:
            s2 = scene;
            break;
        case 3:
            s3 = scene;
            break;
        case 4:
            s4 = scene;
            break;
        case 5:
            s5 = scene;
            break;
        case 6:
            s6 = scene;
            break;
        case 7:
            s7 = scene;
            break;
        case 8:
            s8 = scene;
            break;
        case 9:
            s9 = scene;
            break;
        case 10:
            s10 = scene;
            break;
        default:
            break;
        }
    }

    /**
     * Changes current visible screen
     * @param sceneNumber which scene to change to; 1 changes to s1; 2 to s2; ...
     */
    static void setSceneMethod(int sceneNumber) {
        switch (sceneNumber) {
        case 1:
            primaryStage.setScene(s1);
            break;
        case 2:
            primaryStage.setScene(s2);
            break;
        case 3:
            primaryStage.setScene(s3);
            break;
        case 4:
            CharacterSheetScreen.update();
            primaryStage.setScene(s4);
            break;
        case 5:
            IslandLandingScreen.update(Island.getCurrentIsland());
            primaryStage.setScene(s5);
            break;
        case 6:
            primaryStage.setScene(s6);
            break;
        case 7:
            primaryStage.setScene(s7);
            break;
        case 8:
            primaryStage.setScene(s8);
            break;
        case 9:
            primaryStage.setScene(s9);
            break;
        case 10:
            primaryStage.setScene(s10);
            break;
        default:
            break;
        }
    }

    public static void loadIslandDisplay() {
        IslandLandingScreen.generateIslandLandingScreen(Island.getCurrentIsland());
    }

    public static void loadMarketplaceDisplay() {
        MarketplaceScreen.generateMarketplaceScreen(Island.getCurrentIsland());
    }

    public static void resetScreens() {
        WelcomeScreen.generateWelcomeScreen();
        InitConfigScreen.generateInitConfigScreen();
        MapScreen.generateMapScreen();
        CharacterSheetScreen.generateCharacterSheetScreen();
        InventoryScreen.generateInventoryScreen();
        GameOverScreen.generateGameOverScreen();
        YouWonScreen.generateYouWonScreen();
        EndCreditsScreen.generateEndCreditsScreen();

        //loading Island display from inside island landing screen
        //loading marketplace display from inside island landing screen
    }
}
