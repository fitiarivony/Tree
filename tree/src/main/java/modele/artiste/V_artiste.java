/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.artiste;

import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.BddObject;
import back.objects.Vue;
import com.bubble.tree.annotations.Image;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.annotations.TreeField;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="artiste")
public class V_artiste extends Vue<V_artiste>{
   
    @Attribut(name="idprestation",primary_key=true)
    Integer id;
    @Attribut(name="numeroPrestation")
    @InputIgnore
    String numeroPrestation;
    @TreeField("Nom artiste")
    @Attribut(name="nomprestation")
    String nomPrestation;
    @TreeField("Tarif")
    @Attribut(name="tarif")
    Double tarif;
    
    @Image
    @Attribut(name="photo")
    String photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

  

    public String getNumeroPrestation() {
        return numeroPrestation;
    }

    public void setNumeroPrestation(String numeroPrestation) {
        this.numeroPrestation = numeroPrestation;
    }

    public String getNomPrestation() {
        return nomPrestation;
    }

    public void setNomPrestation(String nomPrestation) {
        this.nomPrestation = nomPrestation;
    }

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
   
    
}
