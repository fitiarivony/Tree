/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.lieu.place;

import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.BddObject;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="prixplace")
public class PrixPlace extends BddObject<PrixPlace>{
    @Attribut(name="idprixplace",primary_key=true)
    Integer id;
    @Attribut(name="numeroprixplace")
    String numero;
    @Attribut(name="idcategorieplace")
    Integer idcategorieplace;
    @Attribut(name="idlieu")
    Integer idlieu;
    @Attribut(name="iddevis")
    Integer iddevis;
    @Attribut(name="prix")
    Double prix;

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
    
}
