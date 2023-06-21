/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubble.tree.crud.update;

import com.bubble.tree.crud.PutIn;
import back.annotations.ForeignKey;
import back.objects.BddObject;
import com.bubble.tree.crud.Form;
import com.bubble.tree.annotations.InputIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import exception.DAOException;
import exception.MappingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import utils.Connect;
import com.utils.HTMLTypeMapping;
import com.utils.components.Option;

/**
 *
 * @author FITIA ARIVONY
 * @param <T>
 */
public class Update<T extends BddObject> extends Form<T>{
   T before;
   

    public T getBefore() {
        return before;
    }

    public void setBefore(T before) {
        this.before = before;
    }
   
   public Update(T obj,Connect conn) throws Exception{
       this.setObj(obj);
       this.setBefore(obj);
       this.setConn(conn);
       this.initFields();
   }
   @Override
   public void initFields() throws Exception{
       Field[]fields=this.getTraitable();
      
       for(Field f:fields){
           ForeignKey key=f.getAnnotation(ForeignKey.class);
           if(key==null)this.addField(this.initnormal(f));
           else{
              this.addField(this.initMultiCrits(f));
           }
       }
   }
 
   @Override
   public ArrayList<Option>getOptions(Field f) throws InstantiationException, IllegalAccessException, MappingException, DAOException, Exception{
       ForeignKey key=f.getAnnotation(ForeignKey.class);
       Class reference=key.classe();
       T obj=(T)reference.newInstance();
       System.out.println(obj+"  "+this.getConn());
       ArrayList<T> object=obj.selectAll(this.getConn());
       ArrayList<Option>opt=new ArrayList<>();
        Field referencename=obj.getClass().getDeclaredField(key.fieldlabel());
        Field referencevalue=obj.getClass().getDeclaredField(key.fieldvaleur());
       for(T bdd:object){
           System.out.println(referencename);
         opt.add(new Option(bdd.get(referencevalue)+"-"+bdd.get(referencename),bdd.get(referencevalue)));
         if(opt.get(opt.size()-1).getValue().equals(this.getObj().get(f))){
             opt.get(opt.size()-1).setSelected("selected");
         }
          
       }
       return opt;
   }
   
   public  PutIn initnormal(Field f) throws Exception{
       PutIn update=new PutIn();
       HTMLTypeMapping typeMap=new HTMLTypeMapping();
       update.setType(typeMap.getType(f));
       update.setInput("input");
       this.setCkeditor(update, f);
       update.setName(f.getName());
       update.setValue(this.getObj().get(f));
       this.setImage(update, f);
       return update;
   }

    @Override
    public void setImage(PutIn input, Field f) {
        super.setImage(input, f); //To change body of generated methods, choose Tools | Templates.
        input.setValue(null);
    }
   
  
   
  
}
