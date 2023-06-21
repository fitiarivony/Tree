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

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="administrateur")
public class Administrateur extends Loggable<Administrateur>{
    @Attribut(name="idadmin",primary_key=true)
    Integer id;
    @Attribut(name="numeroadmin")
    String numeroAdmin;
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

   

    public String getNumeroAdmin() {
        return numeroAdmin;
    }

    public void setNumeroAdmin(String numeroAdmin) {
        this.numeroAdmin = numeroAdmin;
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

    @Override
    public String initToken() {
       return "";
    }

  
    
}
