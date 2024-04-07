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
public class ActivityAndLocationTypeScene extends Scene {
    
    private ActiviteitsNaamVanLocatieType activiteitsNaamVanLocatieType = new ActiviteitsNaamVanLocatieType();
    
    public ActivityAndLocationTypeScene(Parent parent, double d, double d1) {
        super(parent, d, d1);
        
        final ImageView programImageView = new LogoImage().initialiseAndGetImageView();
        
        // custom class for program Logo Image initialisation. Will be used as the logo icon in the top of screen
        
        final ImageView backgroundImageView = new ImageView(new Image(new File(Constants.BACKGROUND_IMAGE_PATH).toURI().toString()));

        backgroundImageView.setFitWidth(Constants.WINDOW_SIZE_X);
        backgroundImageView.setFitHeight(Constants.WINDOW_SIZE_Y);
        
        // setup background image of screen
        
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        
        HBox addDeleteButtonLayout = new HBox();
        
        addDeleteButtonLayout.setAlignment(Pos.BASELINE_RIGHT);
        
        addDeleteButtonLayout.setSpacing(10);
                
        addDeleteButtonLayout.getChildren().addAll(addButton, deleteButton);
        
        // Voeg een layout toe voor knoppen om gegevens te verwijderen en toe te voegen
        
        TableView activityAndLocationTypeTable = new TableView<ActivityAndLocationTypeObject>();
        
        activityAndLocationTypeTable.setEditable(true);
        
        TableColumn activityNameColumn = new TableColumn<ActivityAndLocationTypeObject, String>("activity_names");
        activityNameColumn.setCellValueFactory(new PropertyValueFactory<ActivityAndLocationTypeObject, String>("activity_name"));
        activityNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
        /*
        
        - initialiseer de 'activity_name' kolom. deze kolom geeft alle activiteiten weer gekoppeld aan de desbetreffende location_type waardes,
          en wanneer er op 1 van de specifieke activiteiten word gedubbelkikt, heeft de gebruiker de mogelijkheid
          om de waarde van de geselecteerde activiteit aan te passen met behulp van een TextField
        
        */
        
        activityNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ActivityAndLocationTypeObject, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ActivityAndLocationTypeObject, String> event) {
                
                String databasePrimaryKeyValue = event.getOldValue();
                
                activiteitsNaamVanLocatieType.update(Constants.ACTIVITY_NAME_COLUMN, event.getNewValue(), databasePrimaryKeyValue);
                
                ActivityAndLocationTypeObject activityAndLocationTypeObject = event.getRowValue();
                activityAndLocationTypeObject.setActivity_name(event.getNewValue());
                
            }
        });
        
        // zodra de nieuwe waarde is opgegeven door de gebruiker, update de waarde in de database en toon de geupdatete waarde in het TableView
        
        TableColumn locationTypeColumn = new TableColumn<ActivityAndLocationTypeObject, String>("location_type");
        locationTypeColumn.setCellValueFactory(new PropertyValueFactory<ActivityAndLocationTypeObject, String>("location_type"));
        locationTypeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(
                FXCollections.observableArrayList(new LocatieType().read())));
        
        /*
        
        - initialiseer de 'location_type'. deze kolom geeft alle location_type waardes gekoppeld aan de desbetreffende activiteiten weer,
          en wanneer er op 1 van de specifieke location_type waarde word gedubbelkikt, heeft de gebruiker de mogelijkheid
          om de waarde van de geselecteerde location_type aan te passen met behulp van een ComboBox. Alleen de meest recent gegevens
          over beschikbare location_type waardes worden weergegeven door, op het moment dat de gebruiker de waarde wenst te bewerken,
          er een SQL query wordt uitgevoerd die alle huidige location_type waardes opvraagt
        
        */
        
        locationTypeColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ActivityAndLocationTypeObject, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ActivityAndLocationTypeObject, String> event) {
                
                ActivityAndLocationTypeObject activityAndLocationTypesObject = event.getRowValue();
                
                activiteitsNaamVanLocatieType.update(Constants.LOCATION_TYPE_COLUMN,
                        event.getNewValue(),
                        activityAndLocationTypesObject.getActivity_name());
                
                activityAndLocationTypesObject.setLocation_type(event.getNewValue());
                
            }
        });
        
        // zodra de nieuwe waarde is opgegeven door de gebruiker, update de waarde in de database en toon de geupdatete waarde in het TableView
        
        activityAndLocationTypeTable.getColumns().addAll(activityNameColumn, locationTypeColumn);
        
        activityAndLocationTypeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        // zorgt ervoor dat het aantal gebruikte kolommen zich uitbreid naar de gehele grootte van de tableView
        
        List<ActivityAndLocationTypeObject> allData = new ActiviteitsNaamVanLocatieType().read();
        
        for (int i = 0; i < allData.size(); i++) {
        
        activityAndLocationTypeTable.getItems().add(new ActivityAndLocationTypeObject(
                        allData.get(i).getActivity_name(),
                                    allData.get(i).getLocation_type()));
        
        }
        
        /*
        
        - vraag de meest recenet beschikbare gegevens op voor de database tafel 'activiteitsnaamvanlocatietype'
          en voeg deze stuk voor stuk toe aan het tableView met behulp van een for loop
        
        */
        
        deleteButton.setOnAction(e2 -> {
            
                ActivityAndLocationTypeObject locationAndLocationTypeObject =
                        (ActivityAndLocationTypeObject) activityAndLocationTypeTable.getSelectionModel().getSelectedItem();
            
                deleteRow(
                        activityAndLocationTypeTable,
                        activityAndLocationTypeTable.getSelectionModel().getSelectedIndex(),
                        locationAndLocationTypeObject);
                                                                
            });
        
        // verwijdert de database record geselecteerd door de gebruiker uit zowel de database als het tableView
        
        addButton.setOnAction((ActionEvent e) -> {
           
            Dialog newActivityOfLocationTypeDialog = new Dialog();
            newActivityOfLocationTypeDialog.setTitle("Add new activity of location_type");
            newActivityOfLocationTypeDialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
            newActivityOfLocationTypeDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
                        
            Button saveButton = (Button) newActivityOfLocationTypeDialog.getDialogPane().lookupButton(ButtonType.APPLY);
                        
            TextField newActivityTextField = new TextField();
            
            newActivityTextField.setPromptText("Enter new activity name here");
            
            ComboBox<String> newLocationTypeComboBox = new ComboBox();
                                    
            newLocationTypeComboBox.setItems(FXCollections.observableArrayList(new LocatieType().read()));
            
            /*
            
            -  haalt de meest recente 'locatie_type' waardes op en converteert deze naar een ObservableList object zodat iedere waarde als
               unieke comboBox selectie in de comboBox kan worden ingevoerd
            
            */
            
            BorderPane borderPane = new BorderPane();
            
            borderPane.setLeft(newActivityTextField);
            borderPane.setRight(newLocationTypeComboBox);
                                    
            newActivityOfLocationTypeDialog.getDialogPane().setContent(borderPane);
            
            // voeg een borderpane layout toe aan de dialog
            
            saveButton.disableProperty().bind(Bindings.or(newActivityTextField.textProperty().isEmpty(),
                            newLocationTypeComboBox.valueProperty().isNull()));
            
            // er kan pas geinteracteerd worden met de 'opslaan' knop zodra in beide velden iets ingevuld is
            
            newActivityOfLocationTypeDialog.getDialogPane().getChildren().add(saveButton);
            
            saveButton.setOnAction(e2 -> {      
                            
                if (!newActivityTextField.getText().isBlank() 
                        && newActivityTextField.getText().matches("^[\\p{IsLatin}\\- ]+$")
                        && newLocationTypeComboBox.getValue() != null) {
                    
                    /*
                                        
                    - voert onderstaande code alleen uit als het textfield niet blanco is 
                      en deze alleen letters van het Latijnse alphabet bevat of het leesteken '-'
                      én er een waarde geselecteerd is voor de combobox
                    
                    */
                    
                    activiteitsNaamVanLocatieType.create(newActivityTextField.getText(), newLocationTypeComboBox.getValue());
                    
                    activityAndLocationTypeTable.getItems().clear();
                    
                    getAndPopulateActivitiesOfLocationTypes(activityAndLocationTypeTable);
                    
                    // voeg nieuwe waardenparadigma toe, zowel in de database als in het tableView
                
                } else {
                    
                    newActivityOfLocationTypeDialog.show();
                    
                    newActivityTextField.clear();
                    
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("non-applicable");
                    alert.setHeaderText("Entered value(s) not allowed");
                    alert.setContentText("Entered value(s) not allowed within the context of an activity with a location_type");
                                        
                    alert.showAndWait();
                    
                    // informeer gebruiker, middels een warning alert, dat opgegeven waarde(s) niet toegestaan zijn binnen deze context
                
                }           
                
            });
            
            newActivityOfLocationTypeDialog.show();
        });
        
        final AnchorPane root = new AnchorPane();
        
        AnchorPane.setTopAnchor(programImageView, 10.0);
        
        AnchorPane.setTopAnchor(activityAndLocationTypeTable, 125.0);
        AnchorPane.setLeftAnchor(activityAndLocationTypeTable, 10.0);
        AnchorPane.setRightAnchor(activityAndLocationTypeTable, 10.0);
        AnchorPane.setBottomAnchor(activityAndLocationTypeTable, 50.0);
        
        AnchorPane.setBottomAnchor(addDeleteButtonLayout, 10.0);
        AnchorPane.setLeftAnchor(addDeleteButtonLayout, 10.0);
        AnchorPane.setRightAnchor(addDeleteButtonLayout, 10.0);
        
        // initialiseer root layout van Scene
                
        root.getChildren().addAll(backgroundImageView,programImageView, activityAndLocationTypeTable, addDeleteButtonLayout);
        
        setRoot(root);
        
    }
    
    private void deleteRow(TableView tableView, int row, ActivityAndLocationTypeObject activityAndLocationTypesObject){
        
        activiteitsNaamVanLocatieType.delete(activityAndLocationTypesObject.getActivity_name());
    
        if (row >= 0) {
        
            tableView.getItems().remove(row);
                    
        }
        
        // verwijdert de opgegeven waarde zowel in het tableView als in de database
    
    }
    
    private void getAndPopulateActivitiesOfLocationTypes (TableView activityAndLocationTypesTable){
            
        List<ActivityAndLocationTypeObject> allData = new ActiviteitsNaamVanLocatieType().read();
        
        for (int i = 0; i < allData.size(); i++) {
        
                activityAndLocationTypesTable.getItems().add(
                new ActivityAndLocationTypeObject(
        allData.get(i).getActivity_name(),
    allData.get(i).getLocation_type()));        
        }
    
    }
    
    // haal de meest recente gegevens op van de activiteitsnaamvanlocatietype database tafel en populeer daarmee het tableView
    
}
