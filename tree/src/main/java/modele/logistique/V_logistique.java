/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.logistique;

import back.annotations.Attribut;
import back.annotations.ForeignKey;
import back.annotations.Table;
import back.objects.Vue;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.annotations.TreeField;
import modele.Niveau;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="logistique")
public class V_logistique extends Vue<V_logistique>{
      @Attribut(name="idprestation",primary_key=true)
    Integer id;
    @Attribut(name="nomprestation")
    @TreeField("Nom sono")
    String nomPrestation;
    @TreeField("Tarif")
    @Attribut(name="tarif")
    Double tarif;
    @ForeignKey(classe=Niveau.class,fieldlabel="nomniveau",fieldvaleur="id")
    @Attribut(name="idniveau")
    Integer idNiveau;
    @InputIgnore
    @TreeField("Type")
    @Attribut(name="nomniveau")
    String nomniveau;

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

    public Integer getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Integer idNiveau) {
        this.idNiveau = idNiveau;
    }

   

    public String getNomniveau() {
        return nomniveau;
    }

    public void setNomniveau(String nomniveau) {
        this.nomniveau = nomniveau;
    }
}
