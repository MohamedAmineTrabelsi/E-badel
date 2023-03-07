/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebadel.gui;

import java.io.File;
import java.net.URL;
import java.time.Duration;
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
 * @author trabe
 */
public class MusiqueController implements Initializable {

    @FXML
    private Button btnplay;
    @FXML
    private Button btnpause;
    @FXML
    private Button btnreset;
    @FXML
    private MediaView Mediaveiw;
private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        file = new File("C:\\Users\\trabe\\OneDrive\\Documents\\NetBeansProjects\\Ebadel\\src\\Ebadel\\media.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        Mediaveiw.setMediaPlayer(mediaPlayer);
        // TODO
    }    

    @FXML
    private void playMedia(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    private void pauseMedia(ActionEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    private void resetMedia(ActionEvent event) {
        if(mediaPlayer.getStatus()!=MediaPlayer.Status.READY){
        mediaPlayer.seek(javafx.util.Duration.seconds(0.0));

    }
    }
    
}
