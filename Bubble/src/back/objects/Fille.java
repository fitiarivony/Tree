/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.objects;

import fenetrage.components.Champ;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import utils.Outils;
import utils.OutilsBdd;

/**
 *
 * @author FITIA ARIVONY
 */
public abstract class Fille<T extends Fille> extends BddObject<T>{
  
   
    public abstract  void setForeign(Object obj)throws Exception;
    
   
}
