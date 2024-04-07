/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

/**
 *
 * @author inter
 */
public class KlantOpTijdSlotVoorActiviteitsNaamOpLocatie {
    
    private Timestamp start_time = new Timestamp(new Date(0).getTime());
    private String customer = new Klant().customer;
    private String activity_name = new ActiviteitsNaamVanLocatieType().activity_name;
    private String location = new LocatieVanLocatieType().location;

    
    public void create(String startTime, String customerName, String activityName, String location, Dialog dialog, TextField textField){
    
        String strSQL = String.format(
                "insert into klantoptijdslotvooractiviteitsnaamoplocatie values ('%s', '%s', '%s', '%s')",
                startTime, customerName, activityName, location);
        
        /*
        
        - opgestelde SQL query die een nieuwe boeking invoert in de klantoptijdslotvooractiviteitsnaamoplocatie tabel,
          met als metadata de starttijd, de naam van de klant, de naam van de activiteit en de locatienaam
        
        */
            Connection con = null;
            try{
                con = DBCPDataSource.getConnection();
                PreparedStatement stat = con.prepareStatement(strSQL);
                int result = stat.executeUpdate();
                if(result == 1){
                    System.out.println("Operatie uitgevoerd");                  
                }
            }catch(SQLException se){
                
                System.out.println(se.getMessage()); 
                
                    dialog.show();
                                        
                    textField.clear();
                    
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("something went wrong");
                    alert.setHeaderText("SQL error and / or values not allowd in context");
                    alert.setContentText("An error has occurred, the error was either SQL related and / or the entered value(s) are not allowed within the context of a new booking");
                                        
                    alert.showAndWait();
                    
                    /*
                    
                    // informeer gebruiker, middels een warning alert, dat opgegeven waarde(s) niet toegestaan zijn binnen deze context,
                       of / en dat er zich een SQL-gerelateerde error voor heeft gedaan
                    
                    */
                
            }
            
            finally{
                try{
                    con.close();
                }catch(SQLException se){
                    System.out.println(se.getMessage());
                }
                
            }
    
            /*
    
        - Doet een poging om een nieuwe record aan te maken in de klantoptijdslotvooractiviteitsnaamoplocatie database tafel.
          Indien dit niet lukt, geef weer waarom
        
        */
    
    }
    
    public List<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject> read(){
        
        List<KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject> entries = new ArrayList<>();
    
        Connection con = null;
        
        try{
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM klantoptijdslotvooractiviteitsnaamoplocatie");
            // alle fysiotherapheuten met de letter 'r' in hun naam
            while(result.next()){
                String start_time = result.getString("start_time");
                String customer = result.getString("customer");
                String activity_name = result.getString("activity_name");
                String location = result.getString("location");

                entries.add(new KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject(start_time, customer, activity_name, location));
            }
        } catch(SQLException se){
            
            System.out.println(se.getMessage());
        } finally{
            try{
                con.close();
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
    
        return entries;
        
        /*
    
        - Doet een poging om alle boekingen met bijbehorende metadata op te halen. Indien dit niet lukt,
          Geef weer waarom
        
        */
    
    
    
    }
    
    public void update(String columnName, String newValue, String rowPrimaryKey){
    
        String strSQL = String.format("update klantoptijdslotvooractiviteitsnaamoplocatie set %s = '%s' where start_time = '%s'", columnName, newValue, rowPrimaryKey);               
        
            Connection con = null;
            try{
                con = DBCPDataSource.getConnection();
                PreparedStatement stat = con.prepareStatement(strSQL);
                int result = stat.executeUpdate();
                if(result == 1){
                    System.out.println("Operatie succesvol uitgevoerd");
                }
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }finally{
                try{
                    con.close();
                }catch(SQLException se){
                    System.out.println(se.getMessage());
                }
            }
    
            /*
    
        - Doet een poging om een bestaande database record  bij te werken.
          Indien dit niet lukt, geef weer waarom
        
        */
            
    }
    
    public void delete(String startTimePrimaryKey){
    
        String strSQL = String.format("delete from klantoptijdslotvooractiviteitsnaamoplocatie where start_time = '%s'", startTimePrimaryKey);
            Connection con = null;
            try{
                con = DBCPDataSource.getConnection();
                PreparedStatement stat = con.prepareStatement(strSQL);
                int result = stat.executeUpdate();
                if(result == 1){
                    System.out.println("Operatie succesvol uitgevoerd");
                }
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }finally{
                try{
                    con.close();
                }catch(SQLException se){
                    System.out.println(se.getMessage());
                }
            }
    
            /*
    
        - Doet een poging om een bestaande record te verwijderen in de database. Indien dit niet lukt,
          Geef weer waarom
        
        */
    
    }
    
}
