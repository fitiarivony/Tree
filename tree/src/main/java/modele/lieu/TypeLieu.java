/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.lieu;

import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.BddObject;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.annotations.TreeField;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="typelieu")
public class TypeLieu extends BddObject<TypeLieu>{
    @Attribut(name="idtypelieu",primary_key=true)
    Integer id;
    @InputIgnore
    @Attribut(name="numerotypelieu")
    String numerotypelieu;
    @TreeField(" type de lieu")
    @Attribut(name="nomtypelieu")
    String nomtypelieu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    public String getNumerotypelieu() {
        return numerotypelieu;
    }

    public void setNumerotypelieu(String numerotypelieu) {
        this.numerotypelieu = numerotypelieu;
    }

    public String getNomtypelieu() {
        return nomtypelieu;
    }

    public void setNomtypelieu(String nomtypelieu) {
        this.nomtypelieu = nomtypelieu;
    }
    
}
