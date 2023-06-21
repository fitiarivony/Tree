/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.devis;

import back.annotations.Attribut;
import back.annotations.ForeignKey;
import back.annotations.Table;
import back.objects.BddObject;
import com.bubble.tree.annotations.TreeField;
import modele.help.Prestation;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="element_devis")
public class Element_devis extends BddObject<Element_devis>{
    @Attribut(name="iddepdevis",primary_key=true)
    Integer id;
    @Attribut(name="numerodpdevis")
    String numero;
    @TreeField("Devis")
    @ForeignKey(classe=Devis.class,fieldlabel="nomdevis",fieldvaleur="id")
    @Attribut(name="iddevis")
    Integer iddevis;
    
    @TreeField("Prestation")
     @ForeignKey(classe=Prestation.class,fieldlabel="nomprestation",fieldvaleur="id")
    @Attribut(name="idprestation")
    Integer idprestation;
    
    @Attribut(name="nb")
    Double nb;
    @Attribut(name="tarif")
    Double tarif;
    @Attribut(name="duree")
    Double duree;

    public Double getDuree() {
        return duree;
    }

    public void setDuree(Double duree) {
        this.duree = duree;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public Double getNb() {
        return nb;
    }

    public void setNb(Double nb) {
        this.nb = nb;
    }

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return "Element_devis{" + "id=" + id + ", numero=" + numero + ", iddevis=" + iddevis + ", idprestation=" + idprestation + ", nb=" + nb + ", tarif=" + tarif + '}';
    }
    
    
}
