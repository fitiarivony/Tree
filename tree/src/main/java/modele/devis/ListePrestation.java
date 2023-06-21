/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.devis;

import java.util.ArrayList;
import modele.artiste.V_artiste;
import modele.logistique.V_logistique;
import modele.sono.V_sono;
import modele.lieu.V_lieu;
import modele.autre.V_autre;
import modele.lieu.place.CategoriePlace;
import utils.Connect;
/**
 *
 * @author FITIA ARIVONY
 */
public class ListePrestation {
    ArrayList<V_artiste>artiste;
    ArrayList<V_sono>sono;
    ArrayList<V_logistique>logistique;
    ArrayList<V_lieu>lieu;
    ArrayList<V_autre>autre;
    ArrayList<CategoriePlace> categorieplaces;

    public ArrayList<CategoriePlace> getCategorieplaces() {
        return categorieplaces;
    }

    public void setCategorieplaces(ArrayList<CategoriePlace> categorieplaces) {
        this.categorieplaces = categorieplaces;
    }


    public ArrayList<V_artiste> getArtiste() {
        return artiste;
    }

    public void setArtiste(ArrayList<V_artiste> artiste) {
        this.artiste = artiste;
    }

    public ArrayList<V_sono> getSono() {
        return sono;
    }

    public void setSono(ArrayList<V_sono> sono) {
        this.sono = sono;
    }

    public ArrayList<V_logistique> getLogistique() {
        return logistique;
    }

    public void setLogistique(ArrayList<V_logistique> logistique) {
        this.logistique = logistique;
    }


    public ArrayList<V_lieu> getLieu() {
        return lieu;
    }

    public void setLieu(ArrayList<V_lieu> lieu) {
        this.lieu = lieu;
    }

    public ArrayList<V_autre> getAutre() {
        return autre;
    }

    public void setAutre(ArrayList<V_autre> autre) {
        this.autre = autre;
    }

    public ListePrestation() throws Exception {
     
          Connect conn=new Connect();
        conn.setuses(true);
        conn.initSpring();
       try{
            this.setArtiste(new V_artiste().selectAll(conn));
        this.setAutre(new V_autre().selectAll(conn));
        this.setLieu(new V_lieu().selectAll(conn));
        this.setLogistique(new V_logistique().selectAll(conn));
        this.setSono(new V_sono().selectAll(conn));
        this.setCategorieplaces(new CategoriePlace().selectAll(conn));
       }finally{
           conn.forceClose();
       }    
        
       
    }
    
}
