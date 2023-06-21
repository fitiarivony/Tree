/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.lieu.place;

import back.annotations.Attribut;
import back.annotations.ForeignKey;
import back.annotations.Table;

import back.objects.BddObject;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.annotations.TreeField;
import modele.lieu.Lieu;
import modele.lieu.V_lieu;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="placelieu")
public class Placelieu extends BddObject<Placelieu>{
    @Attribut(name="idplacelieu",primary_key=true)
    Integer id;
    @InputIgnore
    @Attribut(name="numeroplacelieu")
    String numeroplacelieu;
    @TreeField("Nom place lieu")
    @Attribut(name="nomplacelieu")
    String nomplacelieu;
    @ForeignKey(classe=V_lieu.class,fieldlabel="nomPrestation",fieldvaleur="id")
     @TreeField("Lieu")
    @Attribut(name="idlieu")
    Integer idlieu;
     @TreeField("Categorie place")
    @ForeignKey(classe=CategoriePlace.class,fieldlabel="nom",fieldvaleur="id")
    @Attribut(name="idcategorieplace")
    Integer idcategorieplace;
     @TreeField("Nb place")
    @Attribut(name="nombre")
    Integer nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroplacelieu() {
        return numeroplacelieu;
    }

    public void setNumeroplacelieu(String numeroplacelieu) {
        this.numeroplacelieu = numeroplacelieu;
    }

    public String getNomplacelieu() {
        return nomplacelieu;
    }

    public void setNomplacelieu(String nomplacelieu) {
        this.nomplacelieu = nomplacelieu;
    }

    public Integer getIdlieu() {
        return idlieu;
    }

    public void setIdlieu(Integer idlieu) {
        this.idlieu = idlieu;
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
