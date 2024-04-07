/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

import java.io.File;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

/**
 *
 * @author inter
 */
public class CustomersScene extends Scene {
    
    private Klant klant = new Klant();
    
    public CustomersScene(Parent parent, double d, double d1) {
        super(parent, d, d1);
                
        final ImageView programImageView = new LogoImage().initialiseAndGetImageView();
        
        // custom class for program Logo Image initialisation. Will be used as the logo icon in the top of screen
        
        final ImageView backgroundImageView = new ImageView(new Image(new File(Constants.BACKGROUND_IMAGE_PATH).toURI().toString()));

        backgroundImageView.setFitWidth(Constants.WINDOW_SIZE_X);
        backgroundImageView.setFitHeight(Constants.WINDOW_SIZE_Y);
        
        // setup background image of screen
        
        TableView customersTable = new TableView<CustomerObject>();
        
        customersTable.setEditable(true);
        
        TableColumn customerColumn = new TableColumn<CustomerObject, String>("Customers");
        customerColumn.setCellValueFactory(new PropertyValueFactory<CustomerObject, String>("customer"));
        customerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
        /*
        
        - initialiseer de 'customer' kolom. deze kolom geeft alle customers weer,
          en wanneer er op 1 van de specifieke customers word gedubbelkikt, heeft de gebruiker de mogelijkheid
          om de waarde van de geselecteerde customer aan te passen met behulp van een TextField
        
        */
        
        customerColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CustomerObject, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<CustomerObject, String> event) {
                
                String databasePrimaryKeyValue = event.getOldValue();
                
                klant.update(databasePrimaryKeyValue, event.getNewValue());
                
                CustomerObject customer = event.getRowValue();
                customer.setCustomer(event.getNewValue());
                                
            }
        });
        
        // zodra de nieuwe waarde is opgegeven door de gebruiker, update de waarde in de database en toon de geupdatete waarde in het TableView
        
        customersTable.getColumns().add(customerColumn);
        
        customersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        // zorgt ervoor dat het aantal gebruikte kolommen zich uitbreid naar de gehele grootte van de tableView
        
        getAndPopulateCustomers(customersTable);
        
        // haal alle waardes van de 'klant' tafel op vanuit de database en populeer hiermee het tableView
        
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        
        HBox addDeleteButtonLayout = new HBox();
        
        addDeleteButtonLayout.setAlignment(Pos.BASELINE_RIGHT);
        
        addDeleteButtonLayout.setSpacing(10);
                
        addDeleteButtonLayout.getChildren().addAll(addButton, deleteButton);
        
        // Voeg een layout toe voor knoppen om gegevens te verwijderen en toe te voegen
        
        addButton.setOnAction((ActionEvent e) -> {
            Dialog newCustomerDialog = new Dialog();
            newCustomerDialog.setTitle("Add new customer");
            newCustomerDialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
            newCustomerDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
                        
            Button saveButton = (Button) newCustomerDialog.getDialogPane().lookupButton(ButtonType.APPLY);
                        
            TextField newCustomerTextField = new TextField();
            
            newCustomerTextField.setPromptText("Enter new customer first name here");
            
            StackPane stackPane = new StackPane();
            
            stackPane.getChildren().add(newCustomerTextField);
            
            newCustomerDialog.getDialogPane().setContent(stackPane);
            
            // voeg een stackPane layout toe aan de dialog
                        
            saveButton.disableProperty().bind( newCustomerTextField.textProperty().isEmpty());
            
            // er kan pas geinteracteerd worden met de 'opslaan' knop zodra er iets ingevuld is in het TextField
            
            newCustomerDialog.getDialogPane().getChildren().add(saveButton);
            
            saveButton.setOnAction(e2 -> {
                            
                if (!newCustomerTextField.getText().isBlank() && newCustomerTextField.getText().matches("^[\\p{IsLatin}\\- ]+$")) {
                                 
                    /*
                                        
                    - voert onderstaande code alleen uit als het textfield niet blanco is 
                      en deze alleen letters van het Latijnse alphabet bevat of het leesteken '-'
                    
                    */
                    
                    klant.create(newCustomerTextField.getText());
                    
                    customersTable.getItems().clear();
                    
                    getAndPopulateCustomers(customersTable);
                    
                    // voeg nieuwe waardenparadigma toe, zowel in de database als in het tableView
                
                } else {
                    
                    newCustomerDialog.show();
                    
                    newCustomerTextField.clear();
                    
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("non-applicable");
                    alert.setHeaderText("Entered value not allowed");
                    alert.setContentText("Entered value not allowed within the context of a customer name");
                                        
                    alert.showAndWait();
                
                    // informeer gebruiker, middels een warning alert, dat opgegeven waarde(s) niet toegestaan zijn binnen deze context
                    
                }            
            });
            
            newCustomerDialog.show();
        });
        
        deleteButton.setOnAction(e2 -> {
            
                CustomerObject customerObject = (CustomerObject) customersTable.getSelectionModel().getSelectedItem();
            
                deleteRow(customersTable, customersTable.getSelectionModel().getSelectedIndex(), customerObject);
                                                                
            });
        
        // verwijdert de database record geselecteerd door de gebruiker uit zowel de database als het tableView
        
        final AnchorPane root = new AnchorPane();
        
        AnchorPane.setTopAnchor(programImageView, 10.0);
        
        AnchorPane.setTopAnchor(customersTable, 125.0);
        AnchorPane.setLeftAnchor(customersTable, 10.0);
        AnchorPane.setRightAnchor(customersTable, 10.0);
        AnchorPane.setBottomAnchor(customersTable, 50.0);
        
        AnchorPane.setBottomAnchor(addDeleteButtonLayout, 10.0);
        AnchorPane.setLeftAnchor(addDeleteButtonLayout, 10.0);
        AnchorPane.setRightAnchor(addDeleteButtonLayout, 10.0);
        
        // initialiseer root layout van Scene
                
        root.getChildren().addAll( backgroundImageView, programImageView, customersTable, addDeleteButtonLayout);
        
        setRoot(root);
        
    }
    
    private void getAndPopulateCustomers (TableView customersTable){
    
        List<String> customersStringList = klant.read();
        
        for (int i = 0; i < customersStringList.size(); i++) {
        
        customersTable.getItems().add(new CustomerObject(customersStringList.get(i)));
        
        }
        
     // haal de meest recente gegevens op van de klant database tafel en populeer daarmee het tableView
    
    }
    
    private void deleteRow(TableView tableView, int row, CustomerObject customerObject){
        
        klant.delete(customerObject.getCustomer());
    
        if (row >= 0) {
        
            tableView.getItems().remove(row);
                    
        }
        
        // verwijdert de opgegeven waarde zowel in het tableView als in de database
    
    }
    
}
