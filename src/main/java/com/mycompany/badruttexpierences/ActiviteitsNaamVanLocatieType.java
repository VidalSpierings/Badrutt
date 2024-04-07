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
public class ActiviteitsNaamVanLocatieType {
    
    public String activity_name = "(activity_name default value)";
    public String locationType = new LocatieType().location_type;
    
    // initialiseer globale variabelen, zoals per het UML-Class diagram
    
    public void create(String activityString, String locationTypeString){
    
        String strSQL = String.format("insert into activiteitsnaamvanlocatietype values ('%s', '%s')", activityString, locationTypeString);
        
        /*
        
        - opgestelde SQL query die de naam van een nieuwe activiteit invoert in de activiteitsnaamvanlocatietype tabel,
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
    
        - Doet een poging om een nieuwe record aan te maken in de activiteitsnaamvanlocatietype database tafel. Indien dit niet lukt,
          Geef weer waarom
        
        */
            
    }
    
    public List<ActivityAndLocationTypeObject> read(){
        
        List<ActivityAndLocationTypeObject> entries = new ArrayList<>();
    
        Connection con = null;
        
        try{
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM activiteitsnaamvanlocatietype");
            // haalt alle waardes binnne de activiteitsnaamvanlocatietype tabel op
            while(result.next()){
                String activity_name = result.getString("activity_name");
                String location_type = result.getString("location_type");                
                entries.add(new ActivityAndLocationTypeObject(activity_name, location_type));
            }
        } catch(SQLException se){
            System.out.println(se.getMessage());            
        } finally{
            try{
                con.close();
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
            
            /*
                
                - Doe een poging om de huidige connectie te sluiten, indien deze functie een error veroorzaakt,
                  geef weer waarom in de Logger
                
                */
            
        }
    
        return entries;
        
        /*
    
        - Doet een poging om alle activiteitsnamen waardes met bijbehorende locatie types op te halen en deze in een List object te stoppen.
          Indien dit niet lukt, geef weer waarom
        
        */
    
    
    
    }
    
    public void update(String columnName, String newValue, String rowPrimaryKey){
    
        String strSQL = String.format("update activiteitsnaamvanlocatietype set %s = '%s' where activity_name = '%s'", columnName, newValue, rowPrimaryKey);               
                
        /*
        
        - opgestelde SQL query die de waarde van een betaande record binnen een opgegeven
          kolom binnne de activiteitsnaamvanlocatietype tafel updatet
                
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
    
        - Doet een poging om een bestaande database record  bij te werken.
          Indien dit niet lukt, geef weer waarom
        
        */
            
    }
    
    public void delete(String activityPrimaryKey){
    
        String strSQL = String.format("delete from activiteitsnaamvanlocatietype where activity_name = '%s'", activityPrimaryKey);
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
    
        - Doet een poging om een bestaande record uit de activiteitsnaamvanlocatietype tafel te verwijderen.
          Indien dit niet lukt, geef weer waarom
        
        */
    
    }
    
}
