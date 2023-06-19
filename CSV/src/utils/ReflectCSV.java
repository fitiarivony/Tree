/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FITIA ARIVONY
 */
public class ReflectCSV {
    public static String convertDate(Object obj){
        if(obj instanceof Timestamp){
                 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                 return format.format((Timestamp)obj);
            }
       
           SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                 return format.format((Date)obj);
            
    }
    public static String conversionDuration(Object obj){
          
        long hours = ((Duration)obj).toHours();
        long totalMinutes = ((Duration)obj).toMinutes();
        long minutes = totalMinutes - (hours * 60);
        return String.format("%02d:%02d", hours, minutes);

    }
  
    public static String get(Object o,String attribut) throws Exception{
        
        Class cl=o.getClass();
        Object obj=new Object();
        String fonction=attribut.substring(0, 1).toUpperCase()+attribut.substring(1);
        try {
            Method meth=cl.getMethod("get"+fonction,null);
            obj=meth.invoke(o,null);
            if(obj instanceof Date)return ReflectCSV.convertDate(obj);
             if(obj instanceof Duration){     
            return ReflectCSV.conversionDuration(obj);
        }
          return obj.toString();
        } catch (NoSuchMethodException ex) {
          Method[]methods=cl.getMethods();
                for(int j=0;j<methods.length;j++){
                   System.out.println(methods[j].getName().toLowerCase()+"-----"+"get"+attribut.toLowerCase());
                    if(methods[j].getName().toLowerCase().equalsIgnoreCase("get"+attribut.toLowerCase())){
                        
                        obj=methods[j].invoke(o,null);
                        if(obj instanceof Date)return ReflectCSV.convertDate(obj);
                           if(obj instanceof Duration){
            return ReflectCSV.conversionDuration(obj);
        }
                        return obj.toString();
                         
                        
                    }
                }
        } 
        System.out.println(attribut);
        throw new Exception("Tsy mitovy attribut getters");
    }
    
    
      public static Object get(Object o,Field attribut) throws Exception{
        try {
            attribut.setAccessible(true);
            return attribut.get(o);
        } catch (Exception ex) {
            return get(o,attribut.getName());
        }
      }
}
