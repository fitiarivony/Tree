/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.devis.affiche;

import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.BddObject;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="element_artiste")
public class Element_artiste extends BddObject<Element_artiste>{
   @Attribut(name="iddepdevis",primary_key=true)
    Integer iddepdevis;
   @Attribut(name="iddevis")
    Integer iddevis;
   @Attribut(name="idprestation")
    Integer idprestation;
   @Attribut(name="nomprestation")
    String nomprestation;
   @Attribut(name="photo")
    String photo;

    public Integer getIddepdevis() {
        return iddepdevis;
    }

    public void setIddepdevis(Integer iddepdevis) {
        this.iddepdevis = iddepdevis;
    }

    
    public Integer getIddevis() {
        return iddevis;
    }

    public void setIddevis(Integer iddevis) {
        this.iddevis = iddevis;
    }

    public Integer getIdprestation() {
        return idprestation;
    }

    public void setIdprestation(Integer idprestation) {
        this.idprestation = idprestation;
    }

    public String getNomprestation() {
        return nomprestation;
    }

    public void setNomprestation(String nomprestation) {
        this.nomprestation = nomprestation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
}
