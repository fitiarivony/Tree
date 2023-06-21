/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.help;

import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.BddObject;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.annotations.TreeField;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="typeprestation")
public class TypePrestation extends BddObject<TypePrestation>{
    @Attribut(name="idtypeprestation",primary_key=true)
    Integer id;
    @InputIgnore
    @Attribut(name="numerotypeprestation")
    String numerotypeprestation;
    @TreeField("Type")
    @Attribut(name="nomtypeprestation")
    String nomtypeprestation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   

    public String getNumerotypeprestation() {
        return numerotypeprestation;
    }

    public void setNumerotypeprestation(String numerotypeprestation) {
        this.numerotypeprestation = numerotypeprestation;
    }

    public String getNomtypeprestation() {
        return nomtypeprestation;
    }

    public void setNomtypeprestation(String nomtypeprestation) {
        this.nomtypeprestation = nomtypeprestation;
    }
    
}
