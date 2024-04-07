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
public class Klant {
    
    public String customer = "(customer default value)";
    
    public void create(String string){
    
        String strSQL = String.format("insert into klant values ('%s')", string);
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
    
    public List<String> read(){
        
        List<String> entries = new ArrayList<>();
    
        Connection con = null;
        
        try{
            con = DBCPDataSource.getConnection();
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM klant");
            // probeer alle klanten op te halen
            while(result.next()){
                String strFysm = result.getString("customer");
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
    
        - Doet een poging om alle klanten op te halen. Indien dit niet lukt,
          Geef weer waarom
        
        */
    
    
    
    }
    
    public void update(String currentValue, String newValue){
    
        String strSQL = String.format("update klant set customer = '%s' where customer = '%s'", newValue, currentValue);               
        
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
    
        - Doet een poging om een bestaande database record  bij te werken binnen de klant tafel.
          Indien dit niet lukt, geef weer waarom
        
        */
            
    }
    
    public void delete(String customerNameValue){
    
        String strSQL = String.format("delete from klant where customer = '%s'", customerNameValue);
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
    
        - Doet een poging om een bestaande record te verwijderen in de klant tafel. Indien dit niet lukt,
          Geef weer waarom
        
        */
    
    }
    
}
