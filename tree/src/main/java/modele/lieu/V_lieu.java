/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.lieu;

import back.annotations.Attribut;
import back.annotations.ForeignKey;
import back.annotations.Table;
import back.objects.Vue;
import com.bubble.tree.annotations.Image;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.annotations.TreeField;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="lieu")
public class V_lieu extends Vue<V_lieu>{
     @Attribut(name="idprestation",primary_key=true)
    Integer id;
    @Attribut(name="numeroPrestation")
    @InputIgnore
    String numeroPrestation;
    @TreeField("Nom lieu")
    @Attribut(name="nomprestation")
    String nomPrestation;
    @TreeField("Place")
    @Attribut(name="place")
    Integer place;
    
    
    @ForeignKey(classe=TypeLieu.class,fieldlabel="nomtypelieu",fieldvaleur="id")
    @Attribut(name="idtypelieu")
    Integer idtypelieu;

    @Image
    @Attribut(name="photo")
    String photo;
    
    @TreeField("Type lieu")
    @InputIgnore
    @Attribut(name="nomtypelieu")
    String nomtypelieu;

    public Integer getIdtypelieu() {
        return idtypelieu;
    }

    public void setIdtypelieu(Integer idtypelieu) {
        this.idtypelieu = idtypelieu;
    }

    public String getNomtypelieu() {
        return nomtypelieu;
    }

    public void setNomtypelieu(String nomtypelieu) {
        this.nomtypelieu = nomtypelieu;
    }
    

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroPrestation() {
        return numeroPrestation;
    }

    public void setNumeroPrestation(String numeroPrestation) {
        this.numeroPrestation = numeroPrestation;
    }

    public String getNomPrestation() {
        return nomPrestation;
    }

    public void setNomPrestation(String nomPrestation) {
        this.nomPrestation = nomPrestation;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

   
    

}
