public class Game {
    private int difficulty;
    private Map map;
    private Music music;
    private boolean muted;
    private static Game game;

    private Game() {
        map = new Map(800, 500, 100); //temp values for now
        music = new Music();
    }

    public static Game getInstance() {
        if (game == null) {
            game = new Game();
        }
        return game;
    }


    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }


    public Map getMap() {
        return map;
    }

    public Music getMusic() {
        return music;
    }

    public boolean getIsMusicMuted() {
        return muted;
    }

    public void setIsMusicMuted(boolean value) {
        muted = value;
    }

    public static void startNewGame() {
        Map.clearIslands();
        Player.resetPlayer();
        InitConfigScreen.updateScreen();
        InventoryScreen.generateInventoryScreen();
        game = new Game();
    }

}
