/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

/**
 *
 * @author inter
 */
public class LocationAndLocationTypesObject {
    
    // Deze class is een container die String waardes beheert die aanverwant zijn aan de LocationAndLocationTypesScene
    
    private String location;
    private String location_type;

    public LocationAndLocationTypesObject() {
        
        location = "(no location)";
        location_type = "(no location_type)";
        
    }

    public LocationAndLocationTypesObject(String location, String location_type) {
        this.location = location;
        this.location_type = location_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }
    
}
