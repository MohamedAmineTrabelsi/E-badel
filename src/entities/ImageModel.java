/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author allal
 */
public class ImageModel {
    
   
    private StringProperty imagePath;
    private ObjectProperty<ImageView> imageView;

    public ImageModel(String imagePath) {
        this.imagePath = new SimpleStringProperty(imagePath);
        this.imageView = new SimpleObjectProperty<>(new ImageView(new Image(imagePath)));
    }

    public StringProperty imagePathProperty() {
        return imagePath;
    }

    public ImageView getImageView() {
        return imageView.get();
    }

    public ObjectProperty<ImageView> imageViewProperty() {
        return imageView;
    }
}
   
