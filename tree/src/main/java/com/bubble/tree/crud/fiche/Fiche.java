/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubble.tree.crud.fiche;

import back.objects.BddObject;
import com.bubble.tree.annotations.Ckeditor;
import com.bubble.tree.annotations.Image;
import com.bubble.tree.crud.Form;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *
 * @author FITIA ARIVONY
 */
public class Fiche<T extends BddObject> {
   ArrayList<SpecFiche>special;
   T obj;

    public ArrayList<SpecFiche> getSpecial() {
        if(special==null)this.setSpecial(new ArrayList<>());
        return special;
    }
    public void addSpecial(SpecFiche fiche){
        this.getSpecial().add(fiche);
    }

    public void setSpecial(ArrayList<SpecFiche> special) {
        this.special = special;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
    
   public Fiche(T obj){
        this.setObj(obj);
       this.initFiche();
   }
   public void initFiche(){
       Field[]fields=this.getObj().getClass().getDeclaredFields();
       for(Field f:fields){
           Ckeditor ck=f.getAnnotation(Ckeditor.class);
           Image img=f.getAnnotation(Image.class);
          if(ck!=null){
              this.addSpecial(new SpecFiche(f.getName(),"ckeditor"));
          }else if(img!=null){
              this.addSpecial(new SpecFiche(f.getName(),"image"));
          }
       }
   }
    
    
}
