/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

/**
 *
 * @author inter
 */
public class LocationTypesObject {
    
        // Deze class is een container die String waardes beheert die aanverwant zijn aan de LocationTypesScene
    
    private String location_type;

    public LocationTypesObject() {
        
        location_type = "(no customer name)";
        
    }

    public LocationTypesObject(String location_type) {
        this.location_type = location_type;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }
    
}
