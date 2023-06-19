/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubble;

/**
 *
 * @author FITIA ARIVONY
 */
import back.annotations.Attribut;
import back.annotations.Table;
import back.objects.BddObject;
import java.time.Duration;

@Table(name="mytable")
public class MyTableRow extends BddObject<MyTableRow>{
    @Attribut(name="id",primary_key=true)
     Integer id;
    @Attribut(name="name")
     String name;
    @Attribut(name="duration")
     Duration duration;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "MyTableRow{" + "id=" + id + ", name=" + name + ", duration=" + duration + '}';
    }

   
}
    

