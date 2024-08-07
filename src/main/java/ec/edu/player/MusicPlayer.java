package ec.edu.player;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicPlayer implements Initializable {

    // Attributes
    private File directory;
    private File[] files;
    private ArrayList<File> songs;
    private int songNumber;
    private Media media;
    private static MediaPlayer mediaPlayer;

    // Constructor
    public void setSongNumber(int songNumber) {
        this.songNumber = songNumber;
    }
    

    // Methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        songs = new ArrayList<>();
        directory = new File("src\\main\\resources\\media");
        files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                songs.add(file);
            }
        }

        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    public void changeMedia(int songNumber){
        mediaPlayer.stop();
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    public void playMedia() {
        mediaPlayer.play();
    }

    public void pauseMedia() {
        mediaPlayer.pause();
    }

    public void stopMedia(){
        mediaPlayer.stop();
    }

    public void resetMedia(){
        mediaPlayer.seek(Duration.seconds(0));
    }
}