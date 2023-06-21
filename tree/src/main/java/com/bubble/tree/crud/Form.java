/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubble.tree.crud;

import back.annotations.ForeignKey;
import back.objects.BddObject;
import com.bubble.tree.annotations.Ckeditor;
import com.bubble.tree.annotations.Image;
import com.bubble.tree.annotations.InputIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import exception.DAOException;
import exception.MappingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import utils.Connect;
import com.utils.components.Option;

/**
 *
 * @author FITIA ARIVONY
 */
public abstract class Form<T extends BddObject>{
     ArrayList<PutIn>fields;
    T obj;
    Connect conn;

    @JsonIgnore
    public Connect getConn() {
        return conn;
    }

    public void setConn(Connect conn) {
        this.conn = conn;
    }
    
    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
    
   
    public ArrayList<PutIn> getFields() {
        if(fields==null)this.setFields(new ArrayList<PutIn>());
        return fields;
    }
    public void addField(PutIn f){
        this.getFields().add(f);
    }
    public void addFields(ArrayList<PutIn>tableau){
        this.getFields().addAll(tableau);
    }

    public void setFields(ArrayList<PutIn> fields) {
        this.fields = fields;
    }
     public abstract void initFields()throws Exception;
     
      @JsonIgnore
   public Field[] getTraitable() throws Exception{
       Field[]fields=this.getObj().allAttributes();
       ArrayList<Field>traitable=new ArrayList<>();
       for(Field f:fields){
           InputIgnore input=f.getDeclaredAnnotation(InputIgnore.class);
           System.out.println(input+"   "+f.getName());
           if(BddObject.getNonPrimary(f)!=null && input==null)traitable.add(f);
       }
       return traitable.toArray(new Field[traitable.size()]);
   }
   
     public PutIn initMultiCrits(Field f)throws Exception{
       PutIn update=new PutIn();
       update.setInput("select");
       update.setValue(this.getOptions(f));
       
       update.setName(f.getName());
       return update;
   }
   public ArrayList<Option>getOptions(Field f) throws InstantiationException, IllegalAccessException, MappingException, DAOException, Exception{
       ForeignKey key=f.getAnnotation(ForeignKey.class);
       Class reference=key.classe();
       T obj=(T)reference.newInstance();
      // System.out.println(obj+"  "+this.getConn());
       ArrayList<T> object=obj.selectAll(this.getConn());
       ArrayList<Option>opt=new ArrayList<Option>();
        Field referencename=obj.getClass().getDeclaredField(key.fieldlabel());
        Field referencevalue=obj.getClass().getDeclaredField(key.fieldvaleur());
       for(T bdd:object){
         //  System.out.println(referencename);
         Object value=bdd.get(referencevalue);
         opt.add(new Option(value.toString()+"-"+bdd.get(referencename),value));
         if(opt.get(opt.size()-1).getValue().equals(this.getObj().get(f))){
             opt.get(opt.size()-1).setSelected("selected");
         }
          
       }
       return opt;
   }
   
   
    public ArrayList<Option>getOptions(Class classe,String label,String valeur) throws InstantiationException, IllegalAccessException, MappingException, DAOException, Exception{
       T obj=(T)classe.newInstance();
      // System.out.println(obj+"  "+this.getConn());
       ArrayList<T> object=obj.selectAll(this.getConn());
       ArrayList<Option>opt=new ArrayList<Option>();
        Field referencename=obj.getClass().getDeclaredField(label);
        Field referencevalue=obj.getClass().getDeclaredField(valeur);
       for(T bdd:object){
         //  System.out.println(referencename);
         Object value=bdd.get(referencevalue);
         opt.add(new Option(value.toString()+"-"+bdd.get(referencename),value));   
       }
       return opt;
   }
   
   
   
     public void setCkeditor(PutIn input,Field f){
        Ckeditor ck=f.getAnnotation(Ckeditor.class);
       if(ck!=null)input.setInput("textarea");
   }
       public void setImage(PutIn input,Field f){
         
        Image image=f.getAnnotation(Image.class);
           System.out.println(f+"  "+image);
        if(image!=null)input.setInput("image");
   }
}
