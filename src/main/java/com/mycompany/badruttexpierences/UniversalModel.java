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

public class UniversalModel {
    
    public List<String> fetchAllLocations(String activityName){
        
        List<String> entries = new ArrayList<>();
    
        Connection con = null;
        
        try{
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            
            String strSQL = String.format(
                "SELECT lvlt.location " +
"FROM locatietype lt JOIN activiteitsnaamvanlocatietype anvlt ON lt.location_type = anvlt.location_type JOIN locatievanlocatietype lvlt ON lt.location_type = lvlt.location_type " +
"WHERE anvlt.location_type = lvlt.location_type AND anvlt.activity_name = '%s'", activityName);
            
            // (raadpleeg het ERD schema voor essentiële context van deze SQL query)
            
            ResultSet result = stat.executeQuery(strSQL);
            while(result.next()){
                String strFysm = result.getString("location");
                entries.add(strFysm);
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
    
        - Doet een poging om alle locaties op te halen die dezelfde location_type waarde hebben
          als de opgegeven activity_name. Indien dit niet lukt,
          Geef weer waarom.
        
        */
    
    
    
    }
    
    public void updateActivityNameAndLocation(String start_time, String actitivtyNameString, String locationString){
    
        String strSQL = String.format(
                "UPDATE klantoptijdslotvooractiviteitsnaamoplocatie " +
                "SET activity_name = '%s', location = '%s' " +
                "WHERE start_time = '%s'",
                actitivtyNameString, locationString, start_time);
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
    
        - Doet een poging om binnen een reeds bestaande record de waarde van de activity_name en location te updaten.
          Indien dit niet lukt, geef weer waarom
        
        */
            
    }
    
    public List<String> fetchAllActivities(){
        
        List<String> entries = new ArrayList<>();
    
        Connection con = null;
        
        try{
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT activity_name FROM activiteitsnaamvanlocatietype");
            while(result.next()){
                String activity_name = result.getString("activity_name");
                entries.add(activity_name);
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
    
        - Doet een poging om alle activiteitsnamen op te halen. Indien dit niet lukt,
          Geef weer waarom
        
        */
    
    
    
    }
    
}
