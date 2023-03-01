/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class VideoBoutiqueController implements Initializable {

    @FXML
    private Button btnPlay;
    @FXML
    private Button btnPause;
    @FXML
    private Button btnReset;
     private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private MediaView mediaview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          file = new File("C:\\Users\\asus\\OneDrive\\Bureau\\promotional-and-explainer-video-motion-graphics-online-shopping-e-commerce-ads-colors-motion.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaview.setMediaPlayer(mediaPlayer);
        // TODO
    }    

    @FXML
    private void play(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    private void pause(ActionEvent event) {
         mediaPlayer.pause();
    }
    

    @FXML
    private void reset(ActionEvent event) {
         if(mediaPlayer.getStatus()!=MediaPlayer.Status.READY){
        mediaPlayer.seek(javafx.util.Duration.seconds(0.0));
    }
    
    }
}
