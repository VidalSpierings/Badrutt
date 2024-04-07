/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

import javafx.scene.control.skin.TextFieldSkin;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

/**
 *
 * @author inter
 */
public class AppTextFieldSkin extends TextFieldSkin{
    
    public AppTextFieldSkin(TextField tf) {
        super(tf);
        
        tf.setBackground(new Background(new BackgroundFill(Color.web(AppColors.primaryColor), null, null)));
        tf.setStyle("-fx-text-fill: white;");
        tf.setAlignment(Pos.CENTER);
        
        /*
        
        - zorgt ervoor dat op een opgegeven TextField het TextField als achtergrondkleur
          de primaire kleur van de applicatie heeft, de ingevulde tekst wit is en de ingevoerde tekst
          steeds vanuit het centrum van het TextField wordt uitgebreid, en niet vanuit de linker- of rechterkant
        
        */
        
    }
    
}
