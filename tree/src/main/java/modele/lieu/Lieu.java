/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.lieu;

import back.annotations.Attribut;
import back.annotations.ForeignKey;
import back.annotations.Table;
import back.objects.BddObject;
import com.bubble.tree.annotations.Image;
import com.bubble.tree.annotations.InputIgnore;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="prestation")
public class Lieu extends BddObject<Lieu>{
     @Attribut(name="idprestation",primary_key=true)
    Integer id;
    @InputIgnore
    @Attribut(name="numeroprestation")
    String numeroPrestation;
    @Attribut(name="nomprestation")
    String nomPrestation;
    @Attribut(name="place")
    Integer place;
    @InputIgnore
    @Attribut(name="idtypeprestation")
    Integer idtypeprestation;
    @Image
    @Attribut(name="photo")
    String photo;
    
    @ForeignKey(classe=TypeLieu.class,fieldlabel="nomtypelieu",fieldvaleur="id")
    @Attribut(name="idtypelieu")
    Integer idtypelieu;

    
    public Integer getIdtypelieu() {
        return idtypelieu;
    }

    public void setIdtypelieu(Integer idtypelieu) {
        this.idtypelieu = idtypelieu;
    }
    

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
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

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getIdtypeprestation() {
        if(idtypeprestation==null)this.setIdtypeprestation(4);
        return idtypeprestation;
    }

    public void setIdtypeprestation(Integer idtypeprestation) {   
        this.idtypeprestation = idtypeprestation;
    }
}
