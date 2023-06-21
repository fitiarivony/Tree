/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.devis;

import java.util.ArrayList;

/**
 *
 * @author FITIA ARIVONY
 */
public class ListeElement {
    ArrayList<Element_devis>artiste;
    ArrayList<Element_devis>sono;
    ArrayList<Element_devis>logistique;
    ArrayList<Element_devis>lieu;
    ArrayList<Element_devis>autre;

    public ArrayList<Element_devis> getArtiste() {
        if(artiste==null)this.setArtiste(new ArrayList<>());
        return artiste;
    }

    public void setArtiste(ArrayList<Element_devis> artiste) {
        this.artiste = artiste;
    }

    public ArrayList<Element_devis> getSono() {
         if(sono==null)this.setSono(new ArrayList<>());
        return sono;
    }

    public void setSono(ArrayList<Element_devis> sono) {
        this.sono = sono;
    }

    public ArrayList<Element_devis> getLogistique() {
         if(logistique==null)this.setLogistique(new ArrayList<>());
        return logistique;
    }

    public void setLogistique(ArrayList<Element_devis> logistique) {
        this.logistique = logistique;
    }

    public ArrayList<Element_devis> getLieu() {
         if(lieu==null)this.setLieu(new ArrayList<>());
        return lieu;
    }

    public void setLieu(ArrayList<Element_devis> lieu) {
        this.lieu = lieu;
    }

    public ArrayList<Element_devis> getAutre() {
         if(autre==null)this.setAutre(new ArrayList<>());
        return autre;
    }

    public void setAutre(ArrayList<Element_devis> autre) {
        this.autre = autre;
    }
    
}
