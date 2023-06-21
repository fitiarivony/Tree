/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.reel.fromage;

import back.objects.BddObject;
import java.sql.SQLException;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */

public class Fromage {
    Double artiste;
    Double sono;
    Double logistique;
    Double lieu;
    Double autre;
    Integer iddevis;
    

    public Double getArtiste() {
        return artiste;
    }

    public void setArtiste(Double artiste) {
        this.artiste = artiste;
    }

    public Double getSono() {
        return sono;
    }

    public void setSono(Double sono) {
        this.sono = sono;
    }

    public Double getLogistique() {
        return logistique;
    }

    public void setLogistique(Double logistique) {
        this.logistique = logistique;
    }

    public Double getLieu() {
        return lieu;
    }

    public void setLieu(Double lieu) {
        this.lieu = lieu;
    }

    public Double getAutre() {
        return autre;
    }

    public void setAutre(Double autre) {
        this.autre = autre;
    }

    public Integer getIddevis() {
        return iddevis;
    }

    public void setIddevis(Integer iddevis) {
        this.iddevis = iddevis;
    }
    
    public Fromage(Integer iddevis) throws SQLException, Exception{
        Connect conn=new Connect();
        conn.initSpring();
        try{
            conn.setuses(true);
            Depense_artiste dep=new Depense_artiste();
            this.setArtiste(dep.returnDepense(iddevis, conn));
            
            Depense_sono depsono=new Depense_sono();
            this.setSono(depsono.returnDepense(iddevis, conn));
            
            Depense_logistique deplogistique=new Depense_logistique();
            this.setLogistique(deplogistique.returnDepense(iddevis, conn));
            
            Depense_lieu deplieu=new Depense_lieu();
            this.setLieu(deplieu.returnDepense(iddevis, conn));
            
            Depense_autre depautre=new Depense_autre();
            this.setAutre(depautre.returnDepense(iddevis, conn));
            
           this.setIddevis(iddevis);
        }finally{
            conn.forceClose();
        }
    }
    
}
