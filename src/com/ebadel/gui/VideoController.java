/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebadel.gui;

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
 * @author achou
 */
public class VideoController implements Initializable {
    
    @FXML
    private MediaView mediaview;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

    @FXML
    private Button resetButton;
     
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        file = new File("C:\\Users\\achou\\OneDrive\\Desktop\\piste-16-taraji-la-prima-album-incondizionati.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaview.setMediaPlayer(mediaPlayer);
        // TODO
        
    }    
    
    @FXML
    void pauseMedia(ActionEvent event) {
        mediaPlayer.pause();

    }

    @FXML
    void playMedia(ActionEvent event) {
        mediaPlayer.play();

    }

    @FXML
    void resetMedia(ActionEvent event) {
        if(mediaPlayer.getStatus()!=MediaPlayer.Status.READY){
        mediaPlayer.seek(javafx.util.Duration.seconds(0.0));

    }
    }
}
