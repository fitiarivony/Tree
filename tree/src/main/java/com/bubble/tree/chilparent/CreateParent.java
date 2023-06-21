/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubble.tree.chilparent;

import back.objects.BddObject;
import back.objects.Fille;
import back.objects.Mere;
import com.bubble.tree.crud.PutIn;
import com.bubble.tree.crud.create.Create;
import com.utils.components.Option;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
public class CreateParent<T extends Mere> extends Create<T>{
    ArrayList<PutIn>childfields;
    public CreateParent(T obj, Connect conn) throws Exception {
        super(obj, conn);
        Create<BddObject>create=new Create((BddObject)this.getHija().newInstance(),conn);
       this.setChildfields(create.getFields());
    }
    public Class getHija() throws Exception{
         Type superClass=this.getObj().getClass().getGenericSuperclass();
        
                if (superClass instanceof ParameterizedType) {
            Type[] typeArguments = ((ParameterizedType) superClass).getActualTypeArguments();
            if (typeArguments.length >= 2) {
                Class<?> eClass = (Class<?>) typeArguments[1];
               return eClass;
            }
        }
                throw new Exception("Maman est suppose avoir une fille");
    }

    public ArrayList<PutIn> getChildfields() {
        return childfields;
    }

    public void setChildfields(ArrayList<PutIn> childfields) {
        this.childfields = childfields;
    }


    
    

   
    
}
