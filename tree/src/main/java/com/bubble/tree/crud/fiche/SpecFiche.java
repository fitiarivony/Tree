/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubble.tree.crud.fiche;

/**
 *
 * @author FITIA ARIVONY
 */
public class SpecFiche {
    String field;
    String representation;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public SpecFiche(String field, String representation) {
        this.field = field;
        this.representation = representation;
    }
    
}
