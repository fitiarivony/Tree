/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubble.tree.crud.multisearch;

import back.annotations.ForeignKey;
import back.dao.where.Filtre;
import back.dao.where.GenCase;
import back.dao.where.Like;
import back.dao.where.Valeur;
import back.objects.BddObject;
import com.bubble.tree.annotations.Image;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.crud.Form;
import com.bubble.tree.crud.PutIn;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Connect;
import com.utils.HTMLSearchMapping;
import com.utils.HTMLTypeMapping;

/**
 *
 * @author FITIA ARIVONY
 */
public class MultiSearch<T extends BddObject>extends Form {
    HashMap<String,Object>map;
    Integer nbpage;
    ArrayList<T>resultat;

    public ArrayList<T> getResultat() {
        return resultat;
    }

    public void setResultat(ArrayList<T> resultat) {
        this.resultat = resultat;
    }
    

    public Integer getNbpage() {
        return nbpage;
    }

    public void setNbpage(Integer nbpage) {
        this.nbpage = nbpage;
    }

    
    public HashMap<String, Object> getMap() {
        if(map==null)map=new HashMap<>();
        return map;
    }

    public void setMap(HashMap<String, Object> map) {
        this.map = map;
    }
    
    
    @Override
    public void initFields() throws Exception {
        Field[]fields=this.getTraitable();
          for(Field f:fields){
           ForeignKey key=f.getAnnotation(ForeignKey.class);
           if(key==null)this.initnormal(f);
           else{
              this.addField(this.initMultiCrits(f));
               this.getMap().put(f.getName(),new Object[0]);
           }
       }
    }
    
   public MultiSearch(T obj,Connect conn) throws Exception{
       this.setObj(obj);
       this.setConn(conn);
       this.initFields();
       System.out.println("hereee");
   }

    public MultiSearch(HashMap<String, Object> map,T obj,Connect conn)throws Exception {
        this.setMap(map);
        this.setObj(obj);
        this.setConn(conn);
    }
    public void search() throws Exception{
        
        this.getMap().forEach((key,value)->{
            try {
                if(!value.equals(""))this.treat(key, value);
                // System.out.println(key+"="+value);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    });
//        System.out.println(this.getObj().getCrit()+"----before setting select");
        this.setResultat(this.getObj().getByIds(this.getConn()));
//        System.out.println(this.getObj().getCrit()+"----after setting select");
    }
    public void treat(String key,Object value) throws Exception{
        String attribut=key.split("-")[0];
        if(key.split("-").length==1){
            if(value instanceof ArrayList){
                if(!((ArrayList)value).isEmpty())this.getObj().getCrit().where(this.initCheckBox(key, value));
            }else{
                this.getObj().getCrit().where(this.initSimple(key, value));
            }
            
        }else{
           this.getObj().getCrit().where(this.initIntervalle(key, value));
        }
        //System.out.println(attribut);
    }
    public Filtre initIntervalle(String key,Object value)throws Exception{
        Filtre fil=new Filtre();
          Field f =this.getObj().getClass().getDeclaredField(key.split("-")[0]);
        fil.setAttribut(BddObject.getNonPrimary(f).name());
        GenCase cas=GenCase.eqgreat;
        if(key.split("-")[1].equals("max"))cas=GenCase.eqless;
        fil.setCas(cas);
        fil.setValue(new Valeur(this.convert(value, f.getType())));
        return fil;
    }
    public Object convert(Object data,Class cl)throws Exception{
        Object objet = data;
            if(cl.equals(Timestamp.class)){
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
                String date=(String)data;
                 return  new Timestamp(format.parse(date).getTime());
            }else if(cl.equals(Date.class)){
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                String date=(String)data;
                 return  format.parse(date);
            }
            if(data.getClass().equals(String.class) && !cl.equals(String.class)){
                objet=cl.getMethod("valueOf", String.class).invoke(null, data);
            }
           return objet;
    }
    public Filtre initSimple(String key,Object value) throws Exception{
        Filtre fil=new Filtre();
         Field f =this.getObj().getClass().getDeclaredField(key);
        fil.setAttribut(BddObject.getNonPrimary(f).name());
        if(f.getType().equals(String.class)){
            fil.setCas(GenCase.like);
             fil.setValue(new Like(value));
        }
        else{
            
            // Convertir la chaîne de caractères en objet
          
            fil.setValue(new Valeur(this.convert(value, f.getType())));
        }
        return fil;
    }
    public Filtre initCheckBox(String key,Object value)throws Exception{
        Filtre fil=new Filtre();
        ArrayList tab=(ArrayList)value;
        Field f =this.getObj().getClass().getDeclaredField(key);
        fil.setAttribut(BddObject.getNonPrimary(f).name());
        fil.setCas(GenCase.in);
       for(int i=0;i<tab.size();i++){
           Object objet=tab.get(i);
                objet=this.convert(tab.get(i),f.getType());
           tab.set(i, objet);
        }
     //   System.out.println(tab);
        fil.setValue(new Valeur(tab.toArray()));
        return fil;
    }
   
    
    public void initnormal(Field f){
        HTMLSearchMapping search=new HTMLSearchMapping();
        
       this.initSearchInput(search.getType(f), f);
    }
   
    public void initSearchInput(String val,Field f){
        HTMLTypeMapping type=new HTMLTypeMapping();
        if(val.equals("interval")){
            this.addField(new PutIn(f.getName()+"-max",type.getType(f),"input"));
            this.addField(new PutIn(f.getName()+"-min",type.getType(f),"input"));
            this.getMap().put(f.getName()+"-max","");
            this.getMap().put(f.getName()+"-min","");
        }else{
            this.addField(new PutIn(f.getName(),type.getType(f),"input"));
            this.getMap().put(f.getName(),"");
        }
    }

    @Override
    public Field[] getTraitable() throws Exception {
         Field[]fields=this.getObj().allAttributes();
       ArrayList<Field>traitable=new ArrayList<>();
       for(Field f:fields){
           InputIgnore input=f.getDeclaredAnnotation(InputIgnore.class);
            Image img=f.getDeclaredAnnotation(Image.class);
          // System.out.println(input+"   "+f.getName());
           if(BddObject.getNonPrimary(f)!=null && input==null && img==null){
               traitable.add(f);
           }
       }
       return traitable.toArray(new Field[traitable.size()]); //To change body of generated methods, choose Tools | Templates.
    }
    
}
