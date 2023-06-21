/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.help;

import back.annotations.Attribut;
import back.annotations.ForeignKey;
import back.annotations.Table;
import back.objects.BddObject;
import com.bubble.tree.annotations.Image;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.annotations.TreeField;
import modele.lieu.TypeLieu;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="prestation")
public class Prestation extends BddObject<Prestation>{
    @Attribut(name="idprestation",primary_key=true)
    Integer id;
    @InputIgnore
    @Attribut(name="numeroprestation")
    String numeroprestation;
    @TreeField("Nom prestation")
    @Attribut(name="nomprestation")
    String nomprestation;
    @Attribut(name="tarif")
    Double tarif;
    @TreeField("type")
    @ForeignKey(classe=TypePrestation.class,fieldlabel="nomtypeprestation",fieldvaleur="id")
    @Attribut(name="idtypeprestation")
    Integer idtypeprestation;
    @Attribut(name="place")
    Integer place;
     @ForeignKey(classe=Niveau.class,fieldlabel="nomniveau",fieldvaleur="id")
    @Attribut(name="idniveau")
    Integer idniveau;
     @Image
     @Attribut(name="photo")
    String photo;
      @ForeignKey(classe=TypeLieu.class,fieldlabel="nomtypelieu",fieldvaleur="id")
     @Attribut(name="idtypelieu")
    Integer idtypelieu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   
    public String getNumeroprestation() {
        return numeroprestation;
    }

    public void setNumeroprestation(String numeroprestation) {
        this.numeroprestation = numeroprestation;
    }

    public String getNomprestation() {
        return nomprestation;
    }

    public void setNomprestation(String nomprestation) {
        this.nomprestation = nomprestation;
    }

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }

    public Integer getIdtypeprestation() {
        return idtypeprestation;
    }

    public void setIdtypeprestation(Integer idtypeprestation) {
        this.idtypeprestation = idtypeprestation;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getIdniveau() {
        return idniveau;
    }

    public void setIdniveau(Integer idniveau) {
        this.idniveau = idniveau;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getIdtypelieu() {
        return idtypelieu;
    }

    public void setIdtypelieu(Integer idtypelieu) {
        this.idtypelieu = idtypelieu;
    }
    
}
