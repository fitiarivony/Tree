/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;


import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author FITIA ARIVONY
 */
public class HTMLTypeMapping {
    HashMap<Class,String>type;

    public HashMap<Class, String> getType() {
        return type;
    }

    public void setType(HashMap<Class, String> type) {
        this.type = type;
    }
    public HTMLTypeMapping(){
        HashMap<Class,String>typeMapping=new HashMap<Class,String>();
        typeMapping.put(Integer.class,"number");
        typeMapping.put(Double.class,"number");
        typeMapping.put(String.class,"text");
        typeMapping.put(Timestamp.class, "datetime-local");
        typeMapping.put(Date.class,"date");
        typeMapping.put(Duration.class,"time");
        this.setType(typeMapping);
    }
     public  String getType(Field f){
       return this.getType().get(f.getType());
    }
}
