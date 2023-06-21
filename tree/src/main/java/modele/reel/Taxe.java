/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.reel;

import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.BddObject;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.annotations.TreeField;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="taxe")
public class Taxe extends BddObject<Taxe>{
    @Attribut(name="idtaxe",primary_key=true)
    Integer id;
    @InputIgnore
    @Attribut(name="numerotaxe")
    String numerotaxe;
    @TreeField("Valeur")
    @Attribut(name="valeur")
    Double valeur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumerotaxe() {
        return numerotaxe;
    }

    public void setNumerotaxe(String numerotaxe) {
        this.numerotaxe = numerotaxe;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
    
}
