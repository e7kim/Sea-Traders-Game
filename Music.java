import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Music {
    private MediaPlayer mp1;
    private MediaPlayer mp2;

    public Music() {
        mp1 = new MediaPlayer(new Media(new File(
                new File("res/The_Rake_Hornpipe.mp3").getAbsolutePath()).toURI().toString()));
        mp1.setCycleCount(MediaPlayer.INDEFINITE);
        mp2 = new MediaPlayer(new Media(
                new File(new File("res/Tomfoolery.mp3").getAbsolutePath()).toURI().toString()));
        mp2.setCycleCount(MediaPlayer.INDEFINITE);
    }
    public void playHornpipe() {
        mp1.play();
    }
    public void stopHornpipe() {
        mp1.stop();
    }
    public void playTomfoolery() {
        mp2.play();
    }
    public void stopTomfoolery() {
        mp2.stop();
    }


}