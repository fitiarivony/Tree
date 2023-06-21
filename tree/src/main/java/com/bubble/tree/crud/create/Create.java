/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubble.tree.crud.create;

import back.annotations.ForeignKey;
import back.objects.BddObject;
import com.bubble.tree.annotations.Ckeditor;
import com.bubble.tree.crud.Form;
import com.bubble.tree.crud.PutIn;
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
 */
public class Create<T extends BddObject> extends Form<T>{

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
 
   public Create(T obj,Connect conn) throws Exception{
       this.setObj(obj);
       this.setConn(conn);
       this.initFields();
   }
     @Override
   public PutIn initMultiCrits(Field f)throws Exception{
       PutIn update=new PutIn();
       update.setInput("select");
       ArrayList<Option>opt=this.getOptions(f);
       update.setValue(opt);
       this.getObj().set(f,opt.get(0).getValue());
       update.setName(f.getName());
       return update;
   }
   
   
   public  PutIn initnormal(Field f) throws Exception{
       PutIn update=new PutIn();
       HTMLTypeMapping typeMap=new HTMLTypeMapping();
       update.setType(typeMap.getType(f));
       update.setInput("input");
       this.setCkeditor(update, f);
       System.out.println("init image");
       this.setImage(update, f);
       update.setName(f.getName());
       return update;
   }
 
  
   
    
}
