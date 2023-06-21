/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.lieu.place;

import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.Vue;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="prixplace_info")
public class PrixPlaceInfo extends Vue<PrixPlaceInfo>{
   @Attribut(name="idprixplace",primary_key=true)
    Integer idprixplace;
  
   @Attribut(name="idcategorieplace")
    Integer idcategorieplace;
   @Attribut(name="idlieu")
    Integer idlieu;
   @Attribut(name="iddevis")
    Integer iddevis;
   @Attribut(name="prix")
    Double prix;
   @Attribut(name="nomcategorieplace")
    String nomcategorieplace;
   @Attribut(name="nomprestation")
    String nomprestation;

    public Integer getIdprixplace() {
        return idprixplace;
    }

    public void setIdprixplace(Integer idprixplace) {
        this.idprixplace = idprixplace;
    }

 
    public Integer getIdcategorieplace() {
        return idcategorieplace;
    }

    public void setIdcategorieplace(Integer idcategorieplace) {
        this.idcategorieplace = idcategorieplace;
    }

    public Integer getIdlieu() {
        return idlieu;
    }

    public void setIdlieu(Integer idlieu) {
        this.idlieu = idlieu;
    }

    public Integer getIddevis() {
        return iddevis;
    }

    public void setIddevis(Integer iddevis) {
        this.iddevis = iddevis;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getNomcategorieplace() {
        return nomcategorieplace;
    }

    public void setNomcategorieplace(String nomcategorieplace) {
        this.nomcategorieplace = nomcategorieplace;
    }

    public String getNomprestation() {
        return nomprestation;
    }

    public void setNomprestation(String nomprestation) {
        this.nomprestation = nomprestation;
    }
    
    
}
