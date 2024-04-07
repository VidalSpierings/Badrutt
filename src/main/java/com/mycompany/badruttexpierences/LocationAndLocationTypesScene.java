/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

import java.io.File;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
/**
 *
 * @author inter
 */
public class LocationAndLocationTypesScene extends Scene {
    
    private LocatieVanLocatieType locatieVanLocatieType = new LocatieVanLocatieType();
    
    public LocationAndLocationTypesScene(Parent parent, double d, double d1) {
        super(parent, d, d1);
    
        final ImageView programImageView = new LogoImage().initialiseAndGetImageView();
        
        // custom class for program Logo Image initialisation. Will be used as the logo icon in the top of screen
        
        final ImageView backgroundImageView = new ImageView(new Image(new File(Constants.BACKGROUND_IMAGE_PATH).toURI().toString()));

        backgroundImageView.setFitWidth(Constants.WINDOW_SIZE_X);
        backgroundImageView.setFitHeight(Constants.WINDOW_SIZE_Y);
        
        // setup background image of screen
        
        TableView locationAndLocationTypesTable = new TableView<LocationAndLocationTypesObject>();
        
        locationAndLocationTypesTable.setEditable(true);
        
        TableColumn locationColumn = new TableColumn<LocationAndLocationTypesObject, String>("location");
        locationColumn.setCellValueFactory(new PropertyValueFactory<LocationAndLocationTypesObject, String>("location"));
        locationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
        /*
        
        - initialiseer de 'location'. deze kolom geeft alle location waardes gekoppeld aan de desbetreffende location_type waardes weer,
          en wanneer er op 1 van de specifieke location waardes word gedubbelkikt, heeft de gebruiker de mogelijkheid
          om de waarde van de geselecteerde location aan te passen met behulp van een ComboBox. Alleen de meest recent gegevens
          over beschikbare location waardes worden weergegeven door, op het moment dat de gebruiker de waarde wenst te bewerken,
          er een SQL query wordt uitgevoerd die alle huidige locationwaardes opvraagt
        
        */
        locationColumn.setOnEditCommit(new EventHandler<CellEditEvent<LocationAndLocationTypesObject, String>>() {
            @Override
            public void handle(CellEditEvent<LocationAndLocationTypesObject, String> event) {
                
                String databasePrimaryKeyValue = event.getOldValue();
                
                locatieVanLocatieType.update(Constants.LOCATION_COLUMN, event.getNewValue(), databasePrimaryKeyValue);
                
                LocationAndLocationTypesObject locationAndLocationTypesObject = event.getRowValue();
                locationAndLocationTypesObject.setLocation(event.getNewValue());
                
            }
        });
        
        // zodra de nieuwe waarde is opgegeven door de gebruiker, update de waarde in de database en toon de geupdatete waarde in het TableView
                
        TableColumn locationTypeColumn = new TableColumn<LocationAndLocationTypesObject, String>("location_type");
        locationTypeColumn.setCellValueFactory(new PropertyValueFactory<LocationAndLocationTypesObject, String>("location_type"));
        locationTypeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(
                FXCollections.observableArrayList(new LocatieType().read())));
        
        /*
        
        - initialiseer de 'location_type'. deze kolom geeft alle location_type waardes gekoppeld aan de desbetreffende location waardes weer,
          en wanneer er op 1 van de specifieke location_type waarde word gedubbelkikt, heeft de gebruiker de mogelijkheid
          om de waarde van de geselecteerde location_type aan te passen met behulp van een ComboBox. Alleen de meest recent gegevens
          over beschikbare location_type waardes worden weergegeven door, op het moment dat de gebruiker de waarde wenst te bewerken,
          er een SQL query wordt uitgevoerd die alle huidige location_type waardes opvraagt
        
        */
        
        locationTypeColumn.setOnEditCommit(new EventHandler<CellEditEvent<LocationAndLocationTypesObject, String>>() {
            @Override
            public void handle(CellEditEvent<LocationAndLocationTypesObject, String> event) {
                
                LocationAndLocationTypesObject locationAndLocationTypesObject = event.getRowValue();
                
                locatieVanLocatieType.update(
                        Constants.LOCATION_TYPE_COLUMN,
                        event.getNewValue(),
                        locationAndLocationTypesObject.getLocation());
                
                locationAndLocationTypesObject.setLocation_type(event.getNewValue());
                
            }
        });
        
        // zodra de nieuwe waarde is opgegeven door de gebruiker, update de waarde in de database en toon de geupdatete waarde in het TableView
        
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        
        HBox addDeleteButtonLayout = new HBox();
        
        addDeleteButtonLayout.setAlignment(Pos.BASELINE_RIGHT);
        
        addDeleteButtonLayout.setSpacing(10);
                
        addDeleteButtonLayout.getChildren().addAll(addButton, deleteButton);
        
        // Voeg een layout toe voor knoppen om gegevens te verwijderen en toe te voegen
        
        locationAndLocationTypesTable.getColumns().addAll(locationColumn, locationTypeColumn);
        
        locationAndLocationTypesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        // zorgt ervoor dat het aantal gebruikte kolommen zich uitbreid naar de gehele grootte van de tableView
       
        List<LocationAndLocationTypesObject> allData = new LocatieVanLocatieType().read();
       
       for (int i = 0; i < allData.size(); i++) {
        
        locationAndLocationTypesTable.getItems().add(
                new LocationAndLocationTypesObject(
                        allData.get(i).getLocation(),
                                    allData.get(i).getLocation_type()));
        
        }
       
       /*
        
        - vraag de meest recenet beschikbare gegevens op voor de database tafel 'locatievanlocatietype'
          en voeg deze stuk voor stuk toe aan het tableView met behulp van een for loop
        
        */
       
       deleteButton.setOnAction(e2 -> {
            
                LocationAndLocationTypesObject locationAndLocationTypeObject =
                        (LocationAndLocationTypesObject) locationAndLocationTypesTable.getSelectionModel().getSelectedItem();
            
                deleteRow(
                        locationAndLocationTypesTable,
                        locationAndLocationTypesTable.getSelectionModel().getSelectedIndex(),
                        locationAndLocationTypeObject);
                                                                
            });
       
        // verwijdert de database record geselecteerd door de gebruiker uit zowel de database als het tableView
       
       addButton.setOnAction((ActionEvent e) -> {
           
            Dialog newLocationOfLocationTypeDialog = new Dialog();
            newLocationOfLocationTypeDialog.setTitle("Add new location of location_type");
            newLocationOfLocationTypeDialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
            newLocationOfLocationTypeDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
                        
            Button saveButton = (Button) newLocationOfLocationTypeDialog.getDialogPane().lookupButton(ButtonType.APPLY);
                        
            TextField newLocationTextField = new TextField();
            
            newLocationTextField.setPromptText("Enter new location name here");
            
            ComboBox<String> newLocationTypeComboBox = new ComboBox();
                                    
            newLocationTypeComboBox.setItems(FXCollections.observableArrayList(new LocatieType().read()));
            
            /*
            
            -  haalt de meest recente 'locatie_type' waardes op en converteert deze naar een ObservableList object zodat iedere waarde als
               unieke comboBox selectie in de comboBox kan worden ingevoerd
            
            */
            
            BorderPane borderPane = new BorderPane();
            
            borderPane.setLeft(newLocationTextField);
            borderPane.setRight(newLocationTypeComboBox);
                                    
            newLocationOfLocationTypeDialog.getDialogPane().setContent(borderPane);
            
            // voeg een borderpane layout toe aan de dialog
            
            saveButton.disableProperty().bind(
                    Bindings.or(newLocationTextField.textProperty().isEmpty(),
                            newLocationTypeComboBox.valueProperty().isNull()));
            
            // er kan pas geinteracteerd worden met de 'opslaan' knop zodra in beide velden iets ingevuld is
            
            newLocationOfLocationTypeDialog.getDialogPane().getChildren().add(saveButton);
            
            saveButton.setOnAction(e2 -> {      
                            
                if (!newLocationTextField.getText().isBlank() 
                        && newLocationTextField.getText().matches("^[\\p{IsLatin}\\- ]+$")
                        && newLocationTypeComboBox.getValue() != null) {
                    
                    /*
                                        
                    - voert onderstaande code alleen uit als het textfield niet blanco is 
                      en deze alleen letters van het Latijnse alphabet bevat of het leesteken '-'
                      én er een waarde geselecteerd is voor de combobox
                    
                    */
                                        
                    locatieVanLocatieType.create(newLocationTextField.getText(), newLocationTypeComboBox.getValue());
                    
                    locationAndLocationTypesTable.getItems().clear();
                    
                    getAndPopulateLocationsOfLocationsTypes(locationAndLocationTypesTable);
                    
                    // voeg nieuwe waardenparadigma toe, zowel in de database als in het tableView
                
                } else {
                    
                    newLocationOfLocationTypeDialog.show();
                    
                    newLocationTextField.clear();
                    
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("non-applicable");
                    alert.setHeaderText("Entered value(s) not allowed");
                    alert.setContentText("Entered value(s) not allowed within the context of a location with a location_type");
                                        
                    alert.showAndWait();
                    
                    // informeer gebruiker, middels een warning alert, dat opgegeven waarde(s) niet toegestaan zijn binnen deze context
                
                }           
                
            });
            
            newLocationOfLocationTypeDialog.show();
        });
        
        final AnchorPane root = new AnchorPane();
        
        AnchorPane.setTopAnchor(programImageView, 10.0);
        
        AnchorPane.setTopAnchor(locationAndLocationTypesTable, 125.0);
        AnchorPane.setLeftAnchor(locationAndLocationTypesTable, 10.0);
        AnchorPane.setRightAnchor(locationAndLocationTypesTable, 10.0);
        AnchorPane.setBottomAnchor(locationAndLocationTypesTable, 50.0);
        
        AnchorPane.setBottomAnchor(addDeleteButtonLayout, 10.0);
        AnchorPane.setLeftAnchor(addDeleteButtonLayout, 10.0);
        AnchorPane.setRightAnchor(addDeleteButtonLayout, 10.0);
        
        // initialiseer root layout van Scene
                
        root.getChildren().addAll(backgroundImageView, programImageView, locationAndLocationTypesTable, addDeleteButtonLayout);
        
        setRoot(root);
    
    }
    
    private void deleteRow(TableView tableView, int row, LocationAndLocationTypesObject locationAndLocationTypesObject){
        
        locatieVanLocatieType.delete(locationAndLocationTypesObject.getLocation());
    
        if (row >= 0) {
        
            tableView.getItems().remove(row);
                    
        }
        
        // verwijdert de opgegeven waarde zowel in het tableView als in de database
    
    }
    
    private void getAndPopulateLocationsOfLocationsTypes (TableView locationAndLocationTypesTable){
            
        List<LocationAndLocationTypesObject> allData = new LocatieVanLocatieType().read();
        
        for (int i = 0; i < allData.size(); i++) {
        
                locationAndLocationTypesTable.getItems().add(
                new LocationAndLocationTypesObject(
        allData.get(i).getLocation(),
    allData.get(i).getLocation_type()));        
        }
        
    // haal de meest recente gegevens op van de locatievanlocatietype database tafel en populeer daarmee het tableView
    
    }
    
}
