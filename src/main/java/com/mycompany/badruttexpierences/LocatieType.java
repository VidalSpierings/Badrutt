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
public class LocatieType {
    
    public String location_type = "(location_type default value)";
    
    // initialiseer globale variabel(en), zoals per het UML-Class diagram
    
    public void create(String string){
    
        String strSQL = String.format("insert into locatietype values ('%s')", string);
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
    
        - Doet een poging om een nieuwe record aan te maken in de locatietype database tafel. Indien dit niet lukt,
          Geef weer waarom
        
        */
            
    }
    
    public List<String> read(){
        
        List<String> entries = new ArrayList<>();
    
        Connection con = null;
        
        try{
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM locatietype");
            // probeer alle locatie types op te halen
            while(result.next()){
                String strFysm = result.getString("location_type");
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
    
        - Doet een poging om alle locatie types op te halen. Indien dit niet lukt,
          Geef weer waarom
        
        */
    
    
    
    }
    
    public void update(String currentValue, String newValue){
    
        String strSQL = String.format("update locatietype set location_type = '%s' where location_type = '%s'", newValue, currentValue);               
        
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
    
    public void delete(String locationType){
    
        String strSQL = String.format("delete from locatietype where location_type = '%s'", locationType);
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
    
        - Doet een poging om een nieuwe record aan te maken in de klant database tafel. Indien dit niet lukt,
          Geef weer waarom
        
        */
    
    }
    
}
