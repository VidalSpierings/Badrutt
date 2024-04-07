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
public class KlantOpTijdSlotVoorActiviteitsNaamOpLocatieScene extends Scene {
    
    private KlantOpTijdSlotVoorActiviteitsNaamOpLocatie klantOpTijdSlotVoorActiviteitsNaamOpLocatie =
            new KlantOpTijdSlotVoorActiviteitsNaamOpLocatie();
    
    private TableColumn activity_name, location;
    
    public KlantOpTijdSlotVoorActiviteitsNaamOpLocatieScene(Parent parent, double d, double d1) {
        super(parent, d, d1);
        
        final AnchorPane root = new AnchorPane();
        
        final ImageView programImageView = new LogoImage().initialiseAndGetImageView();
        
        // custom class for program Logo Image initialisation. Will be used as the logo icon in the top of screen
        
        final ImageView backgroundImageView = new ImageView(new Image(new File(Constants.BACKGROUND_IMAGE_PATH).toURI().toString()));

        backgroundImageView.setFitWidth(Constants.WINDOW_SIZE_X);
        backgroundImageView.setFitHeight(Constants.WINDOW_SIZE_Y);
        
        // setup background image of screen
        
        TableView bookingsTable = new TableView<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject>();
        
        bookingsTable.setEditable(true);
        
        TableColumn timeslot = new TableColumn<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String>("Timeslot");
        timeslot.setCellFactory(TextFieldTableCell.forTableColumn());
        timeslot.setCellValueFactory(new PropertyValueFactory<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String>("timeslot"));
        
        /*
        
        - initialiseer de 'timeslot' kolom. deze kolom geeft alle start_time waardes 
          gekoppeled aan  de desbetreffende customer_name, activity_name en location waardes weer.
          en wanneer er op 1 van de specifieke timeslots word gedubbelkikt, heeft de gebruiker de mogelijkheid
          om de waarde van de geselecteerde timeslot aan te passen met behulp van een TextField
        
        */
        
        timeslot.setOnEditCommit(new EventHandler<CellEditEvent<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String>>() {
            @Override
            public void handle(CellEditEvent<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String> event) {
                
                String databasePrimaryKeyValue = event.getRowValue().getTimeslot();
                                
                klantOpTijdSlotVoorActiviteitsNaamOpLocatie.update(Constants.START_TIME_COLUMN, event.getNewValue(), databasePrimaryKeyValue);
                
                KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject klantOpTijdSlotVoorActiviteitsNaamOpLocatieObject = event.getRowValue();
                klantOpTijdSlotVoorActiviteitsNaamOpLocatieObject.setTimeslot(event.getNewValue());
                
            }
            
            // zodra de nieuwe waarde is opgegeven door de gebruiker, update de waarde in de database en toon de geupdatete waarde in het TableView
            
        });
        
        TableColumn customer_name = new TableColumn<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String>("Customer");
        customer_name.setCellValueFactory(new PropertyValueFactory<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String>("customer_name"));
        customer_name.setCellFactory(ComboBoxTableCell.forTableColumn(
                FXCollections.observableArrayList(new Klant().read())));
        
        /*
        
        - initialiseer de 'customer_name' kolom. deze kolom geeft alle customer_name waardes 
          gekoppeled aan  de desbetreffende timeslot, activity_name en location waardes weer.
          en wanneer er op 1 van de specifieke customer_name waardes word gedubbelkikt, heeft de gebruiker de mogelijkheid
          om de waarde van de geselecteerde customer_name aan te passen met behulp van een ComboBox.
          Deze ComboBox is gevuld met de meest recente waardes uit de 'klant' tabel uit de database middels een
          SQL 'SELECT *' Query.
        
        */
        
        customer_name.setOnEditCommit(new EventHandler<CellEditEvent<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String>>() {
            @Override
            public void handle(CellEditEvent<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String> event) {
                
                KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject klantOpTijdSlotVoorActiviteitsNaamOpLocatieObject = event.getRowValue();
                
                klantOpTijdSlotVoorActiviteitsNaamOpLocatie.update(
                        Constants.CUSTOMER_COLUMN,
                        event.getNewValue(),
                        klantOpTijdSlotVoorActiviteitsNaamOpLocatieObject.getTimeslot());
                
                klantOpTijdSlotVoorActiviteitsNaamOpLocatieObject.setCustomer_name(event.getNewValue());
                
            }
        });
        
        // zodra de nieuwe waarde is opgegeven door de gebruiker, update de waarde in de database en toon de geupdatete waarde in het TableView
                                
        activity_name = new TableColumn<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String>("Activity");
        activity_name.setCellValueFactory(new PropertyValueFactory<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String>("activity_name"));
        
        /*
        
        - initialiseer de 'activity_name' kolom. deze kolom geeft alle activity_name waardes 
          gekoppeled aan de desbetreffende timeslot, customer_name en location waardes weer.
          en wanneer er op 1 van de specifieke activity_name waardes word gedubbelkikt, heeft de gebruiker de mogelijkheid
          om de waarde van de geselecteerde activity_name evenals de gekoppelde location waarde binnen
          de geselecteerde record aan te passen in een Dialog.
        
        */
        
        activity_name.setOnEditStart(new EventHandler<CellEditEvent<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String>>() {
            @Override
            public void handle(CellEditEvent<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String> event) {
                                            
            Dialog editActivityAndLocationDialog = new Dialog();
            editActivityAndLocationDialog.setTitle("edit selected activity name and location");
            editActivityAndLocationDialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
            editActivityAndLocationDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            
            editActivityAndLocationDialog.show();
            
            Button saveButton = (Button) editActivityAndLocationDialog.getDialogPane().lookupButton(ButtonType.APPLY);
                                                            
            ComboBox<String> activityComboBox = new ComboBox();
            
            ComboBox<String> locationComboBox = new ComboBox();
            
            // initialiseer de dialog in kwestie
            
            activityComboBox.setItems(FXCollections.observableArrayList(new UniversalModel().fetchAllActivities()));
                                                            
            // populeer de waardes in de activity ComboBox door de meest recente waardes van alle te selecteren activiteiten op te halen
            
            BorderPane borderPane = new BorderPane();
            
            borderPane.setLeft(activityComboBox);
            borderPane.setRight(locationComboBox);
            
            // voeg een borderpane layout toe aan de dialog
                                    
            activityComboBox.setOnAction(event2 -> {
                
            locationComboBox.getSelectionModel().clearSelection();
                
            String selectedValue = activityComboBox.getValue();
            
            locationComboBox.setItems(FXCollections.observableArrayList(new UniversalModel().fetchAllLocations(selectedValue)));
            
            /*
            
            - verwijder alle opties en herpopuleer deze alléén met locations die hetzelfde locatieType delen als de gekozen activiteit.
              Dit zorgt ervoor dat als de gebruiker bijvoorbeeld een activiteit kiest, en daarna een locatie, en daarna toch de activiteit
              weer verandert, dat er niet (per ongeluk) een activiteit kan worden toegevoegd op een locatie die vergeleken met de specifieke
              activiteit onzinnig is, zoals skiën en snowboarden op een specifiek meer of schaatsen op een specifieke berg
            
            */
                        
       });                                    
            editActivityAndLocationDialog.getDialogPane().setContent(borderPane);
            
            locationComboBox.disableProperty().bind(activityComboBox.valueProperty().isNull());
                        
            // zorg eroor dat de locatie pas geselecteerd kan worden op het moment dat er een activiteit is gekozen.
                        
            saveButton.disableProperty().bind(
                    Bindings.or(activityComboBox.valueProperty().isNull(),
                            locationComboBox.valueProperty().isNull()));
            
            // zorgt ervoor dat de 'opslaan' knop pas ingedrukt kan worden zodra er voor alle velden een waarde is geselecteerd
                        
            saveButton.setOnAction(e2 -> {      
                                                                    
                    new UniversalModel().updateActivityNameAndLocation(
                            event.getRowValue().getTimeslot(),
                            activityComboBox.getValue(),
                            locationComboBox.getValue());
                    
                    bookingsTable.getItems().clear();
                    
                    getAndPopulateBookings(bookingsTable);
                    
                    // voeg nieuwe waardenparadigma toe, zowel in de database als in het tableView
                
            });
                                        
            }
        });
        
        // zodra de nieuwe waarde is opgegeven door de gebruiker, update de waarde in de database en toon de geupdatete waarde in het TableView
                        
        location = new TableColumn<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String>("Location");
        location.setCellValueFactory(new PropertyValueFactory<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String>("location"));
        
        /*
        
        - initialiseer de 'location' kolom. deze kolom geeft alle location waardes 
          gekoppeled aan de desbetreffende timeslot, customer_name en location waardes weer.
          en wanneer er op 1 van de specifieke location waardes word gedubbelkikt, heeft de gebruiker de mogelijkheid
          om de waarde van de geselecteerde activity_name evenals de gekoppelde location waarde binnen
          de geselecteerde record aan te passen in een Dialog.
        
        */
        
        location.setOnEditStart(new EventHandler<CellEditEvent<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String>>() {
            @Override
            public void handle(CellEditEvent<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject, String> event) {
                
            Dialog editActivityAndLocationDialog = new Dialog();
            editActivityAndLocationDialog.setTitle("edit selected activity name and location");
            editActivityAndLocationDialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
            editActivityAndLocationDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            
            editActivityAndLocationDialog.show();
            
            Button saveButton = (Button) editActivityAndLocationDialog.getDialogPane().lookupButton(ButtonType.APPLY);
                                                
            ComboBox<String> activityComboBox = new ComboBox();
            
            ComboBox<String> locationComboBox = new ComboBox();
            
            // initialiseer de dialog in kwestie
            
            activityComboBox.setItems(FXCollections.observableArrayList(new UniversalModel().fetchAllActivities()));
            
            // populeer de waardes in de activity ComboBox door de meest recente waardes van alle te selecteren activiteiten op te halen
                                                
            BorderPane borderPane = new BorderPane();
            
            borderPane.setLeft(activityComboBox);
            borderPane.setRight(locationComboBox);
            
            // voeg een borderpane layout toe aan de dialog
                                    
            activityComboBox.setOnAction(event2 -> {
                
            locationComboBox.getSelectionModel().clearSelection();
                            
            String selectedValue = activityComboBox.getValue();
            
            locationComboBox.setItems(FXCollections.observableArrayList(new UniversalModel().fetchAllLocations(selectedValue)));
                        
            /*
            
            - verwijder alle opties en herpopuleer deze alléén met locations die hetzelfde locatieType delen als de gekozen activiteit.
              Dit zorgt ervoor dat als de gebruiker bijvoorbeeld een activiteit kiest, en daarna een locatie, en daarna toch de activiteit
              weer verandert, dat er niet (per ongeluk) een activiteit kan worden toegevoegd op een locatie die vergeleken met de specifieke
              activiteit onzinnig is, zoals skiën en snowboarden op een specifiek meer of schaatsen op een specifieke berg
            
            */
            
       });                                    
            editActivityAndLocationDialog.getDialogPane().setContent(borderPane);
            
            // zorgt ervoor dat de 'opslaan' knop pas ingedrukt kan worden zodra er voor alle velden een waarde is geselecteerd
            
            locationComboBox.disableProperty().bind(activityComboBox.valueProperty().isNull());
            
            // zorg eroor dat de locatie pas geselecteerd kan worden op het moment dat er een activiteit is gekozen.
            
            saveButton.disableProperty().bind(
                    Bindings.or(activityComboBox.valueProperty().isNull(),
                            locationComboBox.valueProperty().isNull()));
            
            // zorgt ervoor dat de 'opslaan' knop pas ingedrukt kan worden zodra er voor alle velden een waarde is geselecteerd
                        
            saveButton.setOnAction(e2 -> {      
                                                                    
                    new UniversalModel().updateActivityNameAndLocation(
                            event.getRowValue().getTimeslot(),
                            activityComboBox.getValue(),
                            locationComboBox.getValue());
                    
                    bookingsTable.getItems().clear();
                    
                    getAndPopulateBookings(bookingsTable);
                    
            // voeg nieuwe waardenparadigma toe, zowel in de database als in het tableView
                
            });
                
            }
        });
                
        bookingsTable.getColumns().addAll(timeslot, customer_name, activity_name, location);
        
        bookingsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        // zorgt ervoor dat het aantal gebruikte kolommen zich uitbreid naar de gehele grootte van de tableView
        
        List<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject> allData = new KlantOpTijdSlotVoorActiviteitsNaamOpLocatie().read();
        
        for (int i = 0; i < allData.size(); i++) {
        
        bookingsTable.getItems().add(
                new KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject(
                        allData.get(i).getTimeslot(),
                                    allData.get(i).getCustomer_name(),
                allData.get(i).getActivity_name(),
                        allData.get(i).getLocation()));
        
        }
        
        /*
        
        - vraag de meest recenet beschikbare gegevens op voor de database tafel 'klantoptijdslotvooractiviteitsnaamoplocatie'
          en voeg deze stuk voor stuk toe aan het tableView met behulp van een for loop
        
        */
        
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");
        
        HBox addDeleteButtonLayout = new HBox();
        
        addDeleteButtonLayout.setAlignment(Pos.BASELINE_RIGHT);
        
        addDeleteButtonLayout.setSpacing(10);
                
        addDeleteButtonLayout.getChildren().addAll(addButton, deleteButton);
        
        // Voeg een layout toe voor knoppen om gegevens te verwijderen en toe te voegen
        
        addButton.setOnAction((ActionEvent e) -> {
        
        Dialog addBookingDialog = new Dialog();
            addBookingDialog.setTitle("add booking");
            addBookingDialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
            addBookingDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            addBookingDialog.getDialogPane().setMinWidth(650);
            
            addBookingDialog.show();
            
            Button saveButton = (Button) addBookingDialog.getDialogPane().lookupButton(ButtonType.APPLY);
            
            TextField newStartTimeTextField = new TextField();
            
            ComboBox<String> customerComboBox = new ComboBox();
                                                
            ComboBox<String> activityComboBox = new ComboBox();
            
            ComboBox<String> locationComboBox = new ComboBox();
            
            // initialiseer de dialog in kwestie
            
            activityComboBox.setItems(FXCollections.observableArrayList(new UniversalModel().fetchAllActivities()));
            
            // populeer de waardes in de activity ComboBox door de meest recente waardes van alle te selecteren activiteiten op te halen
            
            newStartTimeTextField.setPromptText("yyyy-mm-dd hh:mm:ss");
            
            // informeer gebruiker over hoe een nieuw tijdstip genoteerd dient te worden
            
            customerComboBox.setItems(FXCollections.observableArrayList(new Klant().read()));
                                                
            HBox hBox = new HBox();
            
            hBox.setAlignment(Pos.CENTER);
                                    
            hBox.getChildren().addAll(newStartTimeTextField, customerComboBox, activityComboBox, locationComboBox);
            
            // voeg een borderpane layout toe aan de dialog
                                    
            activityComboBox.setOnAction(event2 -> {
                
            locationComboBox.getSelectionModel().clearSelection();
                            
            String selectedValue = activityComboBox.getValue();
            
            locationComboBox.setItems(FXCollections.observableArrayList(new UniversalModel().fetchAllLocations(selectedValue)));
                        
            /*
            
            - verwijder alle opties en herpopuleer deze alléén met locations die hetzelfde locatieType delen als de gekozen activiteit.
              Dit zorgt ervoor dat als de gebruiker bijvoorbeeld een activiteit kiest, en daarna een locatie, en daarna toch de activiteit
              weer verandert, dat er niet (per ongeluk) een activiteit kan worden toegevoegd op een locatie die vergeleken met de specifieke
              activiteit onzinnig is, zoals skiën en snowboarden op een specifiek meer of schaatsen op een specifieke berg
            
            */
            
       });                                    
            addBookingDialog.getDialogPane().setContent(hBox);
            
            // zorgt ervoor dat de 'opslaan' knop pas ingedrukt kan worden zodra er voor alle velden een waarde is geselecteerd
            
            locationComboBox.disableProperty().bind(activityComboBox.valueProperty().isNull());
            
            // zorg eroor dat de locatie pas geselecteerd kan worden op het moment dat er een activiteit is gekozen.
            
            saveButton.disableProperty().bind(
         locationComboBox.valueProperty().isNull()
            .or(activityComboBox.valueProperty().isNull())
            .or(customerComboBox.valueProperty().isNull())
            .or(newStartTimeTextField.textProperty().isEmpty())
            .or(newStartTimeTextField.textProperty().length().isNotEqualTo(19)));
            
            /*
            
            - zorgt ervoor dat de 'opslaan' knop pas ingedrukt kan worden zodra de velden aan de volgende eisen voldoen:
            
            * Er dient een waarde geselecteerd te zijn in alle comboboxen
            
            * er dient tekst ingevuld te zijn in het newStartTimeTextField
            
            * het aantal karakters in de newStartTimeTextField gelijk is aan het aantal karakters binnen
              een SQL timestamp notatie (exact 19)
            
            */
                        
            saveButton.setOnAction(e2 -> { 
                
                if (!newStartTimeTextField.getText().isBlank() && newStartTimeTextField.getText().matches("[-: 0-9]+")) {
                    
                    System.out.println("regex yes");
                    
                    /*
                                        
                    - voert onderstaande code alleen uit als het textfield niet blanco is 
                      en deze alleen cijfers of de karakters '-', ':' of ' ' (spatie) bevat,
                      (zo veel mogelijk) conform de syntax van een SQL timestamp object
                    
                    */
                                        
                    klantOpTijdSlotVoorActiviteitsNaamOpLocatie.create(
                            newStartTimeTextField.getText(),
                            customerComboBox.getValue(),
                            activityComboBox.getValue(),
                            locationComboBox.getValue(),
                            addBookingDialog,
                            newStartTimeTextField);
                    
                    bookingsTable.getItems().clear();
                    
                    getAndPopulateBookings(bookingsTable);
                    
                    /*
                    
                    // voeg nieuwe waardenparadigma toe, zowel in de database als in het tableView. 
                       Indien er zich een SQL-gerelateerde foutmelding voordoet, toon dit aan de gebruiker middels een warning dialog
                    
                      (Zie de methode 'create' in de Class 'KlantOpTijdSlotVoorActiviteitsNaamOpLocatie' voor toelichting)
                            
                    */
                    
                    
                
                } else {
                    
                    addBookingDialog.show();
                                        
                    newStartTimeTextField.clear();
                    
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("non-applicable");
                    alert.setHeaderText("Entered value(s) not allowed");
                    alert.setContentText("Entered value(s) not allowed within the context of a new booking");
                                        
                    alert.showAndWait();
                    
                    // informeer gebruiker, middels een warning alert, dat opgegeven waarde(s) niet toegestaan zijn binnen deze context
                
                }
                
            });
        
        });
        
        deleteButton.setOnAction(e2 -> {
            
                KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject klantOpTijdSlotVoorActiviteitsNaamOpLocatieObject =
                        (KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject) bookingsTable.getSelectionModel().getSelectedItem();
            
                deleteRow(
                        bookingsTable,
                        bookingsTable.getSelectionModel().getSelectedIndex(),
                        klantOpTijdSlotVoorActiviteitsNaamOpLocatieObject);
                                                                
            });
        
        // verwijdert de database record geselecteerd door de gebruiker uit zowel de database als het tableView
                
        AnchorPane.setTopAnchor(programImageView, 10.0);
        
        AnchorPane.setTopAnchor(bookingsTable, 125.0);
        AnchorPane.setLeftAnchor(bookingsTable, 10.0);
        AnchorPane.setRightAnchor(bookingsTable, 10.0);
        AnchorPane.setBottomAnchor(bookingsTable, 50.0);
        
        AnchorPane.setBottomAnchor(addDeleteButtonLayout, 10.0);
        AnchorPane.setLeftAnchor(addDeleteButtonLayout, 10.0);
        AnchorPane.setRightAnchor(addDeleteButtonLayout, 10.0);
        
        // initialiseer root layout van Scene
                
        root.getChildren().addAll(backgroundImageView, programImageView, bookingsTable, addDeleteButtonLayout);
        
        setRoot(root);
        
    }
    
    private void deleteRow(TableView tableView, int row,
            KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject klantOpTijdSlotVoorActiviteitsNaamOpLocatieObject){
        
        klantOpTijdSlotVoorActiviteitsNaamOpLocatie.delete(klantOpTijdSlotVoorActiviteitsNaamOpLocatieObject.getTimeslot());
    
        if (row >= 0) {
        
            tableView.getItems().remove(row);
                    
        }
        
        // verwijdert de opgegeven waarde zowel in het tableView als in de database
        
    }
        
       private void getAndPopulateBookings (TableView bookingsTable){
            
        List<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject> allData = new KlantOpTijdSlotVoorActiviteitsNaamOpLocatie().read();
        
        for (int i = 0; i < allData.size(); i++) {
        
                bookingsTable.getItems().add(
                new KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject(
            allData.get(i).getTimeslot(),
         allData.get(i).getActivity_name(),
         allData.get(i).getCustomer_name(),
            allData.get(i).getLocation()
                ));        
        }
        
        /*
        
        - haal de meest recente gegevens op van de klantoptijdslotvooractiviteitsnaamoplocatie database tafel
          en populeer daarmee het tableView
                
        */
    
    }
    
}
