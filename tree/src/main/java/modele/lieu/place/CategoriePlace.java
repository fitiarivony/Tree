/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.lieu.place;

import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.BddObject;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.annotations.TreeField;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="categorieplace")
public class CategoriePlace extends BddObject<CategoriePlace>{
    @Attribut(name="idcategorieplace",primary_key=true)
    Integer id;
    @InputIgnore
    @Attribut(name="numerocategorieplace")
    String numero;
    @TreeField("Nom categorie")
     @Attribut(name="nomcategorieplace")
    String nom;

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
