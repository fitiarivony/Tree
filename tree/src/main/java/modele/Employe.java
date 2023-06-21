/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import back.annotations.Attribut;
import back.annotations.Mdp;
import back.annotations.Table;
import back.objects.BddObject;
import back.objects.Loggable;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.annotations.TreeField;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="employe")
public class Employe extends Loggable<Employe>{
   @Attribut(name="idemploye",primary_key=true)
    Integer id;
   @InputIgnore
   @Attribut(name="numeroemploye")
    String numeroEmploye;
   @TreeField("Nom employe")
    @Attribut(name="nomemploye")
    String nomEmploye;
   @TreeField("Prenom employe")
   @Attribut(name="prenomemploye")
    String prenomEmploye;
   @Attribut(name="identifiant")
    String identifiant;
   @Mdp
   @Attribut(name="mdp")
    String mdp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

 
    public String getNumeroEmploye() {
        return numeroEmploye;
    }

    public void setNumeroEmploye(String numeroEmploye) {
        this.numeroEmploye = numeroEmploye;
    }

    public String getPrenomEmploye() {
        return prenomEmploye;
    }

    public void setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }
    

    @Override
    public String initToken() {
        return "";
    }
    
}
