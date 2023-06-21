/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.autre;

import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.Vue;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.annotations.TreeField;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="autre")
public class V_autre extends Vue<V_autre>{
     @Attribut(name="idprestation",primary_key=true)
    Integer id;
    @Attribut(name="numeroPrestation")
    @InputIgnore
    String numeroPrestation;
    @TreeField("Nom autre")
    @Attribut(name="nomprestation")
    String nomPrestation;

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
    
    
}
