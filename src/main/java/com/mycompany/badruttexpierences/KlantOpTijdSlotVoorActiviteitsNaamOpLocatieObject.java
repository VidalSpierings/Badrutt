/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

/**
 *
 * @author inter
 */
public class KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject {
    
    // Deze class is een container die String waardes beheert die aanverwant zijn aan de KlantOpTijdSlotVoorActiviteitsNaamOpLocatieScene
    
    private String timeslot;
    private String activity_name;
    private String customer_name;
    private String location;

    public KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject() {
        
        timeslot = "1970-01-01 00:00:00";
        // Unix epoch, the 'default' value for this timestamp object
        activity_name = "(no activity name)";
        customer_name = "(no customer name)";
        location = "(no location name)";
        
    }

    public KlantOpTijdSlotVoorActiviteitsNaamOpLocatieObject(String timeslot, String activity_name, String customer_name, String location) {
        this.timeslot = timeslot;
        this.activity_name = activity_name;
        this.customer_name = customer_name;
        this.location = location;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timestamp) {
        this.timeslot = timestamp;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    
    
}
