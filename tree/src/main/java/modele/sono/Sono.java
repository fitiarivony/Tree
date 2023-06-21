/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.sono;

import back.annotations.Attribut;
import back.annotations.ForeignKey;
import back.annotations.Table;
import back.objects.BddObject;
import com.bubble.tree.annotations.InputIgnore;
import modele.Niveau;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="prestation")
public class Sono extends BddObject<Sono>{
    @Attribut(name="idprestation",primary_key=true)
    Integer id;
     @Attribut(name="nomprestation")
    String nomPrestation;
    @Attribut(name="tarif")
    Double tarif;
    @ForeignKey(classe=Niveau.class,fieldlabel="nomniveau",fieldvaleur="id")
    @Attribut(name="idniveau")
    Integer idNiveau;
    @InputIgnore
    @Attribut(name="idtypeprestation")
    Integer idTypePrestation;

    public Integer getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Integer idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Integer getIdTypePrestation() {
        if(idTypePrestation==null)this.setIdTypePrestation(2);
        return idTypePrestation;
    }

    public void setIdTypePrestation(Integer idTypePrestation) {
        this.idTypePrestation = idTypePrestation;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    
    
}
