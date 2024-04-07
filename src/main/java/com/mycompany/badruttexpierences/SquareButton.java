/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author inter
 */
public class SquareButton {
 
    public VBox instantiateButton(int x_coordinate, int y_coordinate, Image image, Text buttonText, Stage stage, Scene newScene) {
						
		final VBox squareButton = new VBox();
                
                squareButton.setAlignment(Pos.CENTER);
                
                squareButton.setMinSize(Constants.SQUARE_BUTTON_XY_DIMENSIONS, Constants.SQUARE_BUTTON_XY_DIMENSIONS);
                squareButton.setMaxSize(Constants.SQUARE_BUTTON_XY_DIMENSIONS, Constants.SQUARE_BUTTON_XY_DIMENSIONS);
                
                squareButton.setBackground(new Background(new BackgroundFill(Color.web(AppColors.primaryColor), null, null)));
                
                squareButton.setLayoutX(x_coordinate);
                squareButton.setLayoutY(y_coordinate);
						
		squareButton.setCursor(Cursor.HAND);

		// hovering over the square button changes from 'DEFAULT' (regular cursor) to 'HAND' ('click' cursor),
		// letting the user know the square area can be clicked
				
		ImageView buttonIconImageView = new ImageView();
		
		buttonIconImageView.setFitHeight(50);
		buttonIconImageView.setFitWidth(50);
		
                if (image != null) {
                
                    buttonIconImageView.setImage(image);
		
                // --- initialise icon imageView for button icon ---
                
                } else {
                
                buttonIconImageView.setImage(new Image(new File(Constants.PLACEHOLDER_ICON_PATH).toURI().toString()));
                        
                // if no image has been given as a parameter, set standard placeholder image
                    
                }
                
		buttonText.setFill(Color.WHITE);
                
                buttonText.setWrappingWidth(150);
                
                buttonText.setTextAlignment(TextAlignment.CENTER);
                
                VBox.setMargin(buttonText, new Insets(20, 0, 0, 0));
                
                // --- initialise button text for button ---
                
                squareButton.getChildren().addAll(buttonIconImageView, buttonText);
                                
                squareButton.setOnMouseClicked(e -> stage.setScene(newScene));
		
		return squareButton;
		
	}
    
}
