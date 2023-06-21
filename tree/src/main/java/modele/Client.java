/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.BddObject;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.annotations.TreeField;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="client")
public class Client extends BddObject<Client>{
    @Attribut(name="idclient",primary_key=true)
    Integer id;
    @Attribut(name="numeroclient")
    @InputIgnore
    String numeroclient;
    @TreeField("Nom ")
    @Attribut(name="nomclient")
    String nomclient;
     @TreeField("Prenom ")
    @Attribut(name="prenomclient")
    String prenomclient;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNumeroclient() {
        return numeroclient;
    }

    public void setNumeroclient(String numeroclient) {
        this.numeroclient = numeroclient;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    public String getPrenomclient() {
        return prenomclient;
    }

    public void setPrenomclient(String prenomclient) {
        this.prenomclient = prenomclient;
    }
    
}
