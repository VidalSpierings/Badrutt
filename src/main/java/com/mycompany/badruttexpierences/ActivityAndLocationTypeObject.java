/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

/**
 *
 * @author inter
 */
public class ActivityAndLocationTypeObject {
    
    // Deze class is een container die String waardes beheert die aanverwant zijn aan de ActivityAndLocationTypeScene
    
    private String activity_name;
    private String location_type;
    
    public ActivityAndLocationTypeObject() {
        
        activity_name = "(no activity name)";
        location_type = "(no location_type name)";
                
    }

    public ActivityAndLocationTypeObject(String activity_name, String location_type) {
        this.activity_name = activity_name;
        this.location_type = location_type;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }
    
}
