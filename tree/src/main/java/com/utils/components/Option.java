/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.components;

/**
 *
 * @author FITIA ARIVONY
 */
public class Option {
    Object name;
    Object value;
    String selected;

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    
    

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Option(Object name, Object value) {
        this.name = name;
        this.value = value;
        this.setSelected("");
    }
    

    
    
    
}
