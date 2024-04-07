/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.badruttexpierences;

/**
 *
 * @author inter
 */
public class CustomerObject {
    
    // Deze class is een container die String waardes beheert die aanverwant zijn aan de CustomersScene
    
    private String customer;

    public CustomerObject() {
        
        customer = "(no customer name)";
        
    }

    public CustomerObject(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
    
}