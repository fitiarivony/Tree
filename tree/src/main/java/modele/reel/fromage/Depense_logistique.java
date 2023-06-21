/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.reel.fromage;

import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.BddObject;
import back.objects.Vue;
import exception.MappingException;
import java.util.ArrayList;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="depense_logistique")
public class Depense_logistique extends Vue<Depense_logistique>{
     @Attribut(name="iddevis",primary_key=true)
    Integer iddevis;
    @Attribut(name="depense")
    Double depense;

    public Integer getIddevis() {
        return iddevis;
    }

    public void setIddevis(Integer iddevis) {
        this.iddevis = iddevis;
    }

    public Double getDepense() {
        return depense;
    }

    public void setDepense(Double depense) {
        this.depense = depense;
    }
    public Double returnDepense(Integer iddevis,Connect conn) throws MappingException, Exception{
        this.setIddevis(iddevis);
        ArrayList<Depense_logistique>depense=this.getById(conn);
        if(depense.isEmpty())return 0.0;
        return depense.get(0).getDepense();
    }
    
}