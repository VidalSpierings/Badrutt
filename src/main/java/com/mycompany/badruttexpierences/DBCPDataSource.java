/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;



/**
 *
 * @author inter
 */
public class DBCPDataSource {
    
private DBCPDataSource() {}
    
private static final BasicDataSource ds = new BasicDataSource();
    static {
    ds.setUrl("jdbc:mysql://localhost:3306/test_schema?useSSL=false");
    ds.setUsername("test_acount");
    ds.setPassword("P@ssw0rd#2023");
    ds.setMinIdle(5);
    ds.setMaxIdle(10);
    ds.setMaxOpenPreparedStatements(100);
 }
    /*
    
    - initialiseer het 'BasicDataSource' object,
      dat verantwoordelijk is voor de connectie naar het database schema voor de applicatie
      
    
    */
    
public static Connection getConnection() throws SQLException {
return ds.getConnection();
 }

// ontvang database connectie naar het specifieke database schema voor deze applicatie

}
