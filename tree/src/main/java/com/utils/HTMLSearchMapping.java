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
public class HTMLSearchMapping {
    HashMap<Class,String>type;

    public HashMap<Class, String> getType() {
        return type;
    }

    public void setType(HashMap<Class, String> type) {
        this.type = type;
    }
    public HTMLSearchMapping(){
        HashMap<Class,String>typeMapping=new HashMap<Class,String>();
        typeMapping.put(Integer.class,"interval");
        typeMapping.put(Double.class,"interval");
        typeMapping.put(String.class,"text");
        typeMapping.put(Timestamp.class,"interval");
        typeMapping.put(Date.class,"interval");
        typeMapping.put(Duration.class,"interval");
        this.setType(typeMapping);
    }
     public  String getType(Field f){
       return this.getType().get(f.getType());
    }
}
