/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.devis.affiche;

import java.util.ArrayList;
import modele.artiste.V_artiste;
import modele.autre.V_autre;
import modele.lieu.V_lieu;
import modele.lieu.place.PrixPlaceInfo;
import modele.logistique.V_logistique;
import modele.sono.V_sono;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
public class ListeAfficher {
    ArrayList<Element_artiste>artiste;
    ArrayList<Element_lieu>lieu;
    ArrayList<PrixPlaceInfo>prixplace;

    public ArrayList<PrixPlaceInfo> getPrixplace() {
        return prixplace;
    }

    public void setPrixplace(ArrayList<PrixPlaceInfo> prixplace) {
        this.prixplace = prixplace;
    }

    public ArrayList<Element_artiste> getArtiste() {
        return artiste;
    }

    public void setArtiste(ArrayList<Element_artiste> artiste) {
        this.artiste = artiste;
    }

    public ArrayList<Element_lieu> getLieu() {
        return lieu;
    }

    public void setLieu(ArrayList<Element_lieu> lieu) {
        this.lieu = lieu;
    }
    
  
  

    public ListeAfficher(Integer iddevis) throws Exception{
        Connect conn=new Connect();
        conn.initSpring(); 
        try{
             conn.setuses(true);
        
        Element_lieu toerana=new Element_lieu();
        toerana.setIddevis(iddevis);
        this.setLieu(toerana.getByIds(conn)); 
        Element_artiste artista=new Element_artiste();
        artista.setIddevis(iddevis);
        this.setArtiste(artista.getByIds(conn));
        PrixPlaceInfo prixplace=new PrixPlaceInfo();
        prixplace.setIddevis(iddevis);
        this.setPrixplace(prixplace.getByIds(conn));
        }finally{
            conn.forceClose();
        }
       
        
    }

   
    
}
