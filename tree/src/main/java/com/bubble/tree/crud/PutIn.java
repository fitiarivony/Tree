/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubble.tree.crud;



/**
 *
 * @author FITIA ARIVONY
 */
public class PutIn {
    String name;
    String type;
    String input;
    Object value;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

  

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public PutIn() {
    }

    public PutIn(String name, String type, String input) {
        this.name = name;
        this.type = type;
        this.input = input;
    }
    
    
    
    
}
