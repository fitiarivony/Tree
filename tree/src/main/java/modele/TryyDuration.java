/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.BddObject;
import com.bubble.tree.annotations.TreeField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.utils.conversion.DurationDeserializer;
import java.time.Duration;


/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="tryduration")
public class TryyDuration extends BddObject<TryyDuration>{
    @Attribut(name="id",primary_key=true)
    Integer id;
    @TreeField("Duree")
    @Attribut(name="duree")
    @JsonDeserialize(using = DurationDeserializer.class)
    Duration duree;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Duration getDuree() {
        return duree;
    }

    public void setDuree(Duration duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "TryyDuration{" + "id=" + id + ", duree=" + duree + '}';
    }
    
    
}
