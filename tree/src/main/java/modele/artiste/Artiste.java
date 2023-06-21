/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.artiste;


import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.BddObject;
import com.bubble.tree.annotations.Image;
import com.bubble.tree.annotations.InputIgnore;
import exception.DAOException;
import exception.MappingException;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="prestation")
public class Artiste extends BddObject<Artiste>{
    @Attribut(name="idprestation",primary_key=true)
    Integer id;
    @InputIgnore
    @Attribut(name="numeroprestation")
    String numeroPrestation;
    @Attribut(name="nomprestation")
    String nomPrestation;
    @Attribut(name="tarif")
    Double tarif;
    @InputIgnore
    @Attribut(name="idtypeprestation")
    Integer idtypeprestation;
    @Attribut(name="photo")
    @Image
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

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }

    public Integer getIdtypeprestation() {
        if(idtypeprestation==null)this.setIdtypeprestation(1);
        return idtypeprestation;
    }

    public void setIdtypeprestation(Integer idtypeprestation) {
        this.idtypeprestation = idtypeprestation;
    }

    public String getNomPrestation() {
        return nomPrestation;
    }

    public void setNomPrestation(String nomPrestation) {
        this.nomPrestation = nomPrestation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    

   
    
}
