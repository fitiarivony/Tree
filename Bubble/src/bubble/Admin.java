/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubble;

import back.annotations.Attribut;
import back.annotations.Expiration;
import back.annotations.InitToken;
import back.annotations.Mdp;
import back.annotations.Table;
import back.annotations.Token;
import back.objects.Loggable;
import java.sql.Timestamp;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="administrateur")
public class Admin extends Loggable<Admin>{
    @Attribut(name="id",primary_key=true)
    Integer id;
    @Attribut(name="idadmin")
    String idadmin;
    @Attribut(name="identifiant")
    String identifiant;
    @Attribut(name="mdp")
    @Mdp
    String mdp;
    @Attribut(name="token")
    @Token        
    String token;
    @Expiration
    @Attribut(name="dateexpiration")
    Timestamp dateexpiration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(String idadmin) {
        this.idadmin = idadmin;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(Timestamp dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    @InitToken(hashing="sha1")
    @Override
    public String initToken() {
        return this.getIdentifiant();
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", idadmin=" + idadmin + ", identifiant=" + identifiant + ", mdp=" + mdp + ", token=" + token + ", dateexpiration=" + dateexpiration + '}';
    }
    
    
}
