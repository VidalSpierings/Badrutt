/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author inter
 */
public class LocatieVanLocatieType {
    
    public String location = "(location default value)";
    public String location_type = new LocatieType().location_type;
    
    // initialiseer globale variabelen, zoals per het UML-Class diagram
    
    public void create(String locationString, String locationTypeString){
    
        String strSQL = String.format(
                "insert into locatievanlocatietype values ('%s', '%s')", locationString, locationTypeString);
        
        /*
        
        - opgestelde SQL query die de naam van een nieuwe locatie invoert in de locatievanlocatietype tabel,
          tezamen met het bijbehorende locatietype
        
        */
        
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
    
        - Doet een poging om een nieuwe record aan te maken in de locatievanlocatietype database tafel. Indien dit niet lukt,
          Geef weer waarom
        
        */
            
    }
    
    public List<LocationAndLocationTypesObject> read(){
        
        List<LocationAndLocationTypesObject> entries = new ArrayList<>();
    
        Connection con = null;
        
        try{
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM locatievanlocatietype");
            while(result.next()){
                String location = result.getString("location");
                String location_type = result.getString("location_type");
                entries.add(new LocationAndLocationTypesObject(location, location_type));
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
    
        - Doet een poging om alle locaties met bijbehorende locatie types op te halen. Indien dit niet lukt,
          Geef weer waarom
        
        */
    
    
    
    }
    
    public void update(String columnName, String newValue, String rowPrimaryKey){
    
        String strSQL = String.format("update locatievanlocatietype set %s = '%s' where location = '%s'", columnName, newValue, rowPrimaryKey);               
        
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
    
    public void delete(String locationPrimaryKey){
    
        String strSQL = String.format("delete from locatievanlocatietype where location = '%s'", locationPrimaryKey);
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
    
        - Doet een poging om een bestaande record uit de locatievanlocatietype tafel te verwijderen.
          Indien dit niet lukt, geef weer waarom
        
        */
    
    }
    
}
