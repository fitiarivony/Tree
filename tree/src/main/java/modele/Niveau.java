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
@Table(name="niveau")
public class Niveau extends BddObject<Niveau>{
    @Attribut(name="idniveau",primary_key=true)
    Integer id;
    @Attribut(name="numeroniveau")
    @InputIgnore
    String numeroniveau;
    
    @TreeField("Niveau")
    @Attribut(name="nomniveau")
    String nomniveau;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   

    public String getNumeroniveau() {
        return numeroniveau;
    }

    public void setNumeroniveau(String numeroniveau) {
        this.numeroniveau = numeroniveau;
    }

    public String getNomniveau() {
        return nomniveau;
    }

    public void setNomniveau(String nomniveau) {
        this.nomniveau = nomniveau;
    }
    
}
