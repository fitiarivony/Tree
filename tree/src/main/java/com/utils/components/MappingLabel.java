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
public class MappingLabel {
    String label;
    String value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MappingLabel(String label, String value) {
        this.label = label;
        this.value = value;
    }
    
}
