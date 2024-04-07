/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

import java.io.File;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 *
 * @author inter
 */
public class LocationTypesScene extends Scene {
    
    private LocatieType locatieType = new LocatieType();
    
    public LocationTypesScene(Parent parent, double d, double d1) {
        super(parent, d, d1);
        
        final ImageView programImageView = new LogoImage().initialiseAndGetImageView();
        
        // custom class for program Logo Image initialisation. Will be used as the logo icon in the top of screen
        
        final ImageView backgroundImageView = new ImageView(new Image(new File(Constants.BACKGROUND_IMAGE_PATH).toURI().toString()));

        backgroundImageView.setFitWidth(Constants.WINDOW_SIZE_X);
        backgroundImageView.setFitHeight(Constants.WINDOW_SIZE_Y);
        
        // setup background image of screen
        
        TableView locationTypesTable = new TableView<LocationTypesObject>();
        
        locationTypesTable.setEditable(true);
        
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        
        HBox addDeleteButtonLayout = new HBox();
        
        addDeleteButtonLayout.setAlignment(Pos.BASELINE_RIGHT);
        
        addDeleteButtonLayout.setSpacing(10);
                
        addDeleteButtonLayout.getChildren().addAll(addButton, deleteButton);
        
        // Voeg een layout toe voor knoppen om gegevens te verwijderen en toe te voegen
        
        TableColumn locationTypeColumn = new TableColumn<LocationTypesObject, String>("location_type");
        locationTypeColumn.setCellValueFactory(new PropertyValueFactory<LocationTypesObject, String>("location_type"));
        locationTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
        /*
        
        - initialiseer de 'location_type'. deze kolom geeft alle location_type waardes weer,
          en wanneer er op 1 van de specifieke location_type waarde word gedubbelkikt, heeft de gebruiker de mogelijkheid
          om de waarde van de geselecteerde location_type aan te passen met behulp van een TextField.
        
        */
        
        locationTypeColumn.setOnEditCommit(new EventHandler<CellEditEvent<LocationTypesObject, String>>() {
            @Override
            public void handle(CellEditEvent<LocationTypesObject, String> event) {
                
                String databasePrimaryKeyValue = event.getOldValue();
                
                locatieType.update(databasePrimaryKeyValue, event.getNewValue());
                
                LocationTypesObject locationType = event.getRowValue();
                locationType.setLocation_type(event.getNewValue());
                
            }
        });
        
         // zodra de nieuwe waarde is opgegeven door de gebruiker, update de waarde in de database en toon de geupdatete waarde in het TableView
        
        addButton.setOnAction((ActionEvent e) -> {
            Dialog newLocationTypeDialog = new Dialog();
            newLocationTypeDialog.setTitle("Add new location_type");
            newLocationTypeDialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
            newLocationTypeDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
                        
            Button saveButton = (Button) newLocationTypeDialog.getDialogPane().lookupButton(ButtonType.APPLY);
                        
            TextField newLocationTypeTextField = new TextField();
            
            newLocationTypeTextField.setPromptText("Enter new location name here");
            
            StackPane stackPane = new StackPane();
            
            stackPane.getChildren().add(newLocationTypeTextField);
                        
            newLocationTypeDialog.getDialogPane().setContent(stackPane);
            
            // voeg een stackPane layout toe aan de dialog
                        
            saveButton.disableProperty().bind(newLocationTypeTextField.textProperty().isEmpty());
            
            // er kan pas geinteracteerd worden met de 'opslaan' knop zodra er iets ingevuld is in het TextField
            
            newLocationTypeDialog.getDialogPane().getChildren().add(saveButton);
            
            saveButton.setOnAction(e2 -> {
                            
                if (!newLocationTypeTextField.getText().isBlank() && newLocationTypeTextField.getText().matches("^[\\p{IsLatin}\\- ]+$")) {
                    
                    /*
                                        
                    - voert onderstaande code alleen uit als het textfield niet blanco is 
                      en deze alleen letters van het Latijnse alphabet bevat of het leesteken '-'
                    
                    */                                    
                    locatieType.create(newLocationTypeTextField.getText());
                    
                    locationTypesTable.getItems().clear();
                    
                    getAndPopulateLocationTypes(locationTypesTable);
                    
                    // voeg nieuwe waardenparadigma toe, zowel in de database als in het tableView
                
                } else {
                    
                    newLocationTypeDialog.show();
                    
                    newLocationTypeTextField.clear();
                    
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("non-applicable");
                    alert.setHeaderText("Entered value not allowed");
                    alert.setContentText("Entered value not allowed within the context of a location type");
                                        
                    alert.showAndWait();
                    
                    // informeer gebruiker, middels een warning alert, dat opgegeven waarde(s) niet toegestaan zijn binnen deze context
                
                }            
            });
            
            newLocationTypeDialog.show();
        });
                
        deleteButton.setOnAction(e2 -> {
            
                LocationTypesObject locationTypesObject = (LocationTypesObject) locationTypesTable.getSelectionModel().getSelectedItem();
            
                deleteRow(locationTypesTable, locationTypesTable.getSelectionModel().getSelectedIndex(), locationTypesObject);
                                                                
            });
        
        // verwijdert de database record geselecteerd door de gebruiker uit zowel de database als het tableView
        
        locationTypesTable.getColumns().addAll(locationTypeColumn);
        
        locationTypesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        List<String> locationTypesList = new LocatieType().read();
        
        for (int i = 0; i < locationTypesList.size(); i++) {
        
        locationTypesTable.getItems().add(new LocationTypesObject(locationTypesList.get(i)));
        
        }
        
        // haal de meest recente gegevens op van de locatietype database tafel en populeer daarmee het tableView
                
        final AnchorPane root = new AnchorPane();
        
        AnchorPane.setTopAnchor(programImageView, 10.0);
        
        AnchorPane.setTopAnchor(locationTypesTable, 125.0);
        AnchorPane.setLeftAnchor(locationTypesTable, 10.0);
        AnchorPane.setRightAnchor(locationTypesTable, 10.0);
        AnchorPane.setBottomAnchor(locationTypesTable, 50.0);
        
        AnchorPane.setBottomAnchor(addDeleteButtonLayout, 10.0);
        AnchorPane.setLeftAnchor(addDeleteButtonLayout, 10.0);
        AnchorPane.setRightAnchor(addDeleteButtonLayout, 10.0);
        
        // initialiseer root layout van Scene
                
        root.getChildren().addAll(backgroundImageView, programImageView,locationTypesTable, addDeleteButtonLayout);
        
        setRoot(root);
        
    }
    
    private void getAndPopulateLocationTypes (TableView locationTypesTableView){
    
        List<String> loccatieTypeStringList = locatieType.read();
        
        for (int i = 0; i < loccatieTypeStringList.size(); i++) {
        
        locationTypesTableView.getItems().add(new LocationTypesObject(loccatieTypeStringList.get(i)));
        
        }
        
        // haal de meest recente gegevens op van de locatietype database tafel en populeer daarmee het tableView
    
    }
    
    private void deleteRow(TableView tableView, int row, LocationTypesObject locationTypes){
        
        locatieType.delete(locationTypes.getLocation_type());
    
        if (row >= 0) {
        
            tableView.getItems().remove(row);
                    
        }
        
        // verwijdert de opgegeven waarde zowel in het tableView als in de database
    
    }
    
}
