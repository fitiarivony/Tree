/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.reel;

import back.annotations.Attribut;
import back.annotations.ForeignKey;
import back.annotations.Table;
import back.objects.BddObject;
import com.bubble.tree.annotations.TreeField;
import modele.devis.Devis;
import modele.lieu.V_lieu;
import modele.lieu.place.CategoriePlace;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="billet")
public class Billet extends BddObject<Billet>{
    @Attribut(name="idbillet",primary_key=true)
    Integer id;
    @ForeignKey(classe=V_lieu.class,fieldlabel="nomPrestation",fieldvaleur="id")
    @Attribut(name="idlieu")
    @TreeField("Lieu")
    Integer idlieu;
     @ForeignKey(classe=Devis.class,fieldlabel="nomdevis",fieldvaleur="id")
    @Attribut(name="iddevis")
     @TreeField("Devis")
    Integer iddevis;
      @ForeignKey(classe=CategoriePlace.class,fieldlabel="nom",fieldvaleur="id")
    @Attribut(name="idcategorieplace")
      @TreeField("Categorie ")
    Integer idcategorieplace;
     @Attribut(name="nombre")
    @TreeField("Nombre")
    Integer nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIdcategorieplace() {
        return idcategorieplace;
    }

    public void setIdcategorieplace(Integer idcategorieplace) {
        this.idcategorieplace = idcategorieplace;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }
    
}
