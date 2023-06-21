/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.autre;

import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.BddObject;
import com.bubble.tree.annotations.InputIgnore;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="prestation")
public class Autre extends BddObject<Autre>{
     @Attribut(name="idprestation",primary_key=true)
    Integer id;
    @InputIgnore
    @Attribut(name="numeroprestation")
    String numeroPrestation;
    @Attribut(name="nomprestation")
    String nomPrestation;
    @InputIgnore
    @Attribut(name="idtypeprestation")
    Integer idtypeprestation;

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

    public Integer getIdtypeprestation() {
        if(idtypeprestation==null)this.setIdtypeprestation(4);
        return idtypeprestation;
    }

    public void setIdtypeprestation(Integer idtypeprestation) {
        this.idtypeprestation = idtypeprestation;
    }
    
}
