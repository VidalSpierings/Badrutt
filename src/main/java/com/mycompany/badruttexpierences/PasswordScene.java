/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author inter
 */
public class PasswordScene extends Scene {

    public PasswordScene(Parent parent, double d, double d1) {
        super(parent, d, d1);
        
        final ImageView programImageView = new LogoImage().initialiseAndGetImageView();
        
        // custom class for program Logo Image initialisation. Will be used as the logo icon in the top of screen
        
        final ImageView backgroundImageView = new ImageView(new Image(new File(Constants.BACKGROUND_IMAGE_PATH).toURI().toString()));

        backgroundImageView.setFitWidth(Constants.WINDOW_SIZE_X);
        backgroundImageView.setFitHeight(Constants.WINDOW_SIZE_Y);
        
        // setup background image of screen
                                     
        final Rectangle transparencyRectangle = new Rectangle();
        
            transparencyRectangle.setFill(Color.BLACK);
            transparencyRectangle.setOpacity(0.6);
            
            transparencyRectangle.setHeight(Constants.WINDOW_SIZE_Y);                
            transparencyRectangle.setWidth(Constants.WINDOW_SIZE_X);
            
            // creates a new black rectangle object with a blak and opaue background,
            // giving the user a visual cue to focus their attention on the 'Enter Password' sreen
            
            final BorderPane enterPasswordScreen = new BorderPane();
            
            enterPasswordScreen.setMinHeight(250);
            enterPasswordScreen.setMaxHeight(250);
                
            enterPasswordScreen.setMinWidth(400);
            enterPasswordScreen.setMaxWidth(400);
            
            enterPasswordScreen.setLayoutX(180);
            
            enterPasswordScreen.setLayoutY(130);
                        
            enterPasswordScreen.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            
            // create a pop-up screen (in this case, merely a visual illusion of a pop-up sreen)
            // informing the user that they need to enter a password in order to proceed
                        
        final Text enterPasswordText = new Text("Please enter password to proceed");
        
        enterPasswordText.setFill(Color.web(AppColors.primaryColor));
        
        enterPasswordText.setTextAlignment(TextAlignment.CENTER);
                
        BorderPane.setMargin(enterPasswordText, new Insets(40, 0, 0, 0));
        
        // --- initialise 'Please enter password to proceed' Text object ---
        
        final PasswordField passwordField = new PasswordField();
                
        passwordField.setSkin(new AppTextFieldSkin(passwordField));
        
        // intialise the passwordfield's background color and bullet point / character color
        
        VBox.setMargin(passwordField, new Insets(0, 40, 0, 40));
        
        // --- initialise password field ---
        
        final Text proceedButtonText = new Text("Proceed");
        
        proceedButtonText.setFill(Color.WHITE);
        
        // --- intialise proceed button text ---
        
        final StackPane proceedButton = new StackPane();
        
        proceedButton.setMinSize(65, 25);
        proceedButton.setMaxSize(65, 25);
        
        proceedButton.setBackground(new Background(new BackgroundFill(Color.web(AppColors.primaryColor), null, null)));
        
        proceedButton.setCursor(Cursor.HAND);
        // set cursor image to the 'Hand' / 'Click' icon when users' cursor hovers over the proceed button
        
        proceedButton.getChildren().add(proceedButtonText);
        
        proceedButton.setOnMouseClicked(e -> {
            
            if (passwordField.getText().equals("123")) {
                
                Group root2 = new Group();
            
                App.getStage().setScene(new KlantOpTijdSlotVoorActiviteitsNaamOpLocatieScene(root2, Constants.WINDOW_SIZE_X, Constants.WINDOW_SIZE_Y));
            
            }
            
            
                
        });
        
        // --- initialise proceed button ---
        
        final VBox passwordFieldAndProceedButton = new VBox();
        
        passwordFieldAndProceedButton.getChildren().addAll(passwordField, proceedButton);
        
        VBox.setMargin(proceedButton, new Insets(15, 0, 0, 0));
        
        enterPasswordScreen.setTop(enterPasswordText);
        enterPasswordScreen.setCenter(passwordFieldAndProceedButton);
        
        passwordFieldAndProceedButton.setAlignment(Pos.CENTER);
        
        BorderPane.setAlignment(passwordFieldAndProceedButton, Pos.CENTER);
        BorderPane.setAlignment(enterPasswordText, Pos.TOP_CENTER);
        
        // --- initialise VBox layout that password field and proceed button will be contained in ---
                
        final Group root = new Group();
        
        root.getChildren().addAll(backgroundImageView, programImageView,transparencyRectangle, enterPasswordScreen);
        
        setRoot(root);
        
        // --- intialise and set Scene root view ----
        
    }
    
    
    
}
