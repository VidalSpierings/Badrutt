package com.mycompany.badruttexpierences;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Window;


/**
 * JavaFX App
 */
public class App extends Application {
    
    public static void main (String[] args) {launch(args);}
    
    List<String> entries = new ArrayList<>();
    
    private int currentInstructionIndex = 0;
    
    private String[] onBoardingImagePaths = {
        Constants.USER_ONBOARDING_IMAGE_1_PATH,
        Constants.USER_ONBOARDING_IMAGE_2_PATH,
        Constants.USER_ONBOARDING_IMAGE_3_PATH,
        Constants.USER_ONBOARDING_IMAGE_4_PATH
    };
    
    private String[] instructionTexts = {
        "Press one of the options to navigate to the contents of a certain table of values",
        "Click the 'Add' button to add a new row of values.",
        "A dialog appears. Type or select a value for all fields, then click on the 'Apply' buton",
        "Press the 'Delete' button to delete the value that is selected in the table"
    };
    
    public static Stage stage;

    @Override
    public void start(Stage stage) {
        
            this.stage = stage;
            
            final LogoImage programIconImage = new LogoImage();
                                                 
            // custom class for program Logo Image initialisation. 
            // Will be used as the logo icon in the top of screen and as the program's icon
		
            final Image backgroundImage = new Image(new File(Constants.BACKGROUND_IMAGE_PATH).toURI().toString());
        
            final Image palaceIcon = new Image(new File(Constants.PALACE_ICON_PATH).toURI().toString());
            final Image skiIcon = new Image(new File(Constants.SKI_ICON_PATH).toURI().toString());
            final Image new_booking_icon = new Image(new File(Constants.NEW_BOOKING_ICON_PATH).toURI().toString());
            
            // all images to be used
                    		
	final ImageView programIconImageView = programIconImage.initialiseAndGetImageView();
        
        // init program icon imageView displayed on near top of the scene
        
        final Group root = new Group();
                
        final Scene scene = new Scene(root, Constants.WINDOW_SIZE_X, Constants.WINDOW_SIZE_Y);
        
        // Make the scene a fixed size. 
        // At the time of writing this comment the fixed size of the scene is a width of 750 and a height of 500.
        
        scene.setFill(new ImagePattern(backgroundImage, 0, 0, Constants.WINDOW_SIZE_X, Constants.WINDOW_SIZE_Y, false));
        
        // set Scene background image
        
        final Text bookingsButtonText = new Text("bookings");
        final Text activityAndLocationButtonTypesText = new Text("activities & location_types");
        final Text customersButtonText = new Text("customers");      
        final Text locationTypesButtonText = new Text("location_types");
        final Text locationAndLocationTypesButtonText = new Text("location & location_types");
                		
		final VBox bookingsButton = new SquareButton().instantiateButton(
                        75, 
                        Constants.SQUARE_BUTTON_Y_COORDINATE,
                        palaceIcon,
                        bookingsButtonText,
                        stage,
                        new PasswordScene(new Group(), Constants.WINDOW_SIZE_X, Constants.WINDOW_SIZE_Y));
		
		final VBox activityAndLocationTypesButton = new SquareButton().instantiateButton(
                        313, 
                        Constants.SQUARE_BUTTON_Y_COORDINATE,
                        skiIcon,
                        activityAndLocationButtonTypesText,
                        stage,
                        new ActivityAndLocationTypeScene(new Group(), Constants.WINDOW_SIZE_X, Constants.WINDOW_SIZE_Y));
                
                final VBox customersButton = new SquareButton().instantiateButton(
                        550, 
                        Constants.SQUARE_BUTTON_Y_COORDINATE, 
                        null, 
                        customersButtonText, 
                        stage, 
                        new CustomersScene(new Group(), Constants.WINDOW_SIZE_X, Constants.WINDOW_SIZE_Y));
                
                final VBox locationTypesButton = new SquareButton().instantiateButton(
                        550, 
                        Constants.SQUARE_BUTTON_Y_COORDINATE_TOP, 
                        null, 
                        locationTypesButtonText, 
                        stage, 
                        new LocationTypesScene(new Group(), Constants.WINDOW_SIZE_X, Constants.WINDOW_SIZE_Y));
                
                final VBox locationAndLocationTypesButton = new SquareButton().instantiateButton(
                        75, 
                        Constants.SQUARE_BUTTON_Y_COORDINATE_TOP, 
                        null, 
                        locationAndLocationTypesButtonText, 
                        stage, 
                        new LocationAndLocationTypesScene(new Group(), Constants.WINDOW_SIZE_X, Constants.WINDOW_SIZE_Y));
                
                        // declare and initialise all the 'square buttons' in this Scene
				
                root.getChildren().addAll(
                       programIconImageView,
                        bookingsButton,
                        activityAndLocationTypesButton,
                        locationTypesButton,
                        customersButton,
                        locationAndLocationTypesButton);
                                
        stage.getIcons().add(programIconImage.getImage());
        
        // Set the icon image of the program that shows up
        // to the left of the program title and as the icon of the program in the Windows OS task bar,
        // amongst visibility in other environments
        
        stage.setResizable(false);
        
        // disable window resizing (this makes the square button / fullscreen button in the top right corner of the screen not clickable and also disables
        // the ability to move the mouse to the edges of the screen to resize the window to a more exact preffered width and height)
        
        stage.setTitle("Badrutt Management System");
        stage.setScene(scene);
        stage.show();
        
        // initialise and show stage
        
        showUserOnBoarding();
        
        

}
    
    private void showUserOnBoarding(){
    
        Dialog userOnboardingDialog = new Dialog();
            userOnboardingDialog.setTitle("Program instructions");
            userOnboardingDialog.getDialogPane().setMinWidth(Constants.WINDOW_SIZE_X - 100);
            userOnboardingDialog.getDialogPane().setMinHeight(Constants.WINDOW_SIZE_Y - 75);
            
            // initialiseer user onbaording dialog
            
            Button nextInstructionButton = new Button("Next");
            
            Button skipUserOnboardingButton = new Button("Skip all");
                                    
            ImageView instructionImageView = new ImageView();
            
            instructionImageView.setFitHeight(300);
            instructionImageView.setFitWidth(500);
                        
            Text instructionText = new Text();
            
            instructionText.setStyle("-fx-font-size: 14;");
            
            HBox hBox = new HBox();
            
            hBox.getChildren().addAll(skipUserOnboardingButton, nextInstructionButton);
            
            hBox.setAlignment(Pos.BOTTOM_RIGHT);
            
            hBox.setSpacing(10);
            
            VBox vBox = new VBox();
            
            vBox.getChildren().addAll(instructionImageView, instructionText, hBox);
            
            vBox.setAlignment(Pos.CENTER);
            
            vBox.setSpacing(20);
                                    
            userOnboardingDialog.getDialogPane().setContent(vBox);
            
            /*
            
            - initialiseer de layout componenten voor het dialog, inclusief spatiëring binnen de verschillende layoutparadigma's,
              en voeg deze toe aan de dialog
            
            */
                        
            Window window = userOnboardingDialog.getDialogPane().getScene().getWindow();
            window.setOnCloseRequest(event -> window.hide());
            
            /*
            
            - het bovenstaande code snippet zorgt ervoor dat wanneer er op de rode sluit knop wordt gedrukt rechtsboven in het venster,
              het dialog zich sluit (waarom moet ik dit expliciet inprogrammeren en is dit niet een standaard-functionaliteit?)
            
            */
            
            showInstruction(instructionText, instructionImageView);
            
            /*
            
            - begin met het tonen van de instructies. Iedere keer dat deze methode uitgevoert word,
              wordt de huidige instructie-index geïncrementeerd
            
            */
                        
            nextInstructionButton.setOnAction(e -> {
                
                showInstruction(instructionText, instructionImageView);

                if (currentInstructionIndex == onBoardingImagePaths.length + 1) {
                
                    window.hide();
                    
                    /*
                    
                    - sluit het venster zodra de gebruiker op de 'Volgende' knop
                      drukt terwijl de laatste instructie zichtbaar is
                            
                    */
                
                }
                                            
            });
            
            skipUserOnboardingButton.setOnAction(e -> {      
                            
                window.hide();
                
                // sluit het venster indien de gebruiker op de 'Allemaal overslaan' knop drukt
                
            });
            
            userOnboardingDialog.show();
        
    }
    
    public static Stage getStage(){
        
            return stage;
        
        }
    
    private void showInstruction(Text text, ImageView instructionImageView){
    
        if (currentInstructionIndex >= 0 && currentInstructionIndex < onBoardingImagePaths.length) {
            Image image = new Image((new File(onBoardingImagePaths[currentInstructionIndex])).toURI().toString());
            instructionImageView.setImage(image);
            
            // Toon de afbeelding voor de huidige instructie-index
            
            text.setText(instructionTexts[currentInstructionIndex]);
            
            // Toon de bijbehorende instructietekst voor de huidige instructie-index
        }
        
        currentInstructionIndex++;
        
        // incrementeert de huidige instructie-index
    
    }

}