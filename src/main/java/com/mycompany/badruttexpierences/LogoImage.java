/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author inter
 */
public class LogoImage {
    
    private Image programIconImage;
    private ImageView programImageView;
    
    // initialiseer globale variabelen
    
    private void initialiseImage(){
        
        programIconImage = new Image(new File(Constants.LOGO_IMAGE_PATH).toURI().toString());
            
    }

    public LogoImage() {
        
        initialiseImage();
                      
    }
    
    // initiliase Image object first in case an imageView does not need to be created,
    // but purely an image object of the icon

    public ImageView initialiseAndGetImageView(){
        
        programImageView = new ImageView();
        		
        programImageView.setX(350);
		
        programImageView.setY(50);
                
        programImageView.setFitHeight(94);
        programImageView.setFitWidth(76);
		
        programImageView.setImage(programIconImage); 
        
        return programImageView;
    
    }
    
    // create imageView object that contains the created Image object, if so desired
    
    public Image getImage(){
    
        return programIconImage;
    
    }
    
    
    
}
