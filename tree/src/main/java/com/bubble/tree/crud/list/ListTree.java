/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubble.tree.crud.list;

import com.utils.components.MappingLabel;
import back.objects.BddObject;
import com.bubble.tree.annotations.TreeField;
import com.bubble.tree.crud.multisearch.MultiSearch;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *
 * @author FITIA ARIVONY
 */
public class ListTree<T extends BddObject> {
    ArrayList<T> liste;
    ArrayList<MappingLabel>entete;
    T obj;
    String valuekey;
    boolean infoperso;
    MappingLabel link;
    Integer nbpage;
    MultiSearch<T>multisearch;

    public MultiSearch<T> getMultisearch() {
        return multisearch;
    }

    public void setMultisearch(MultiSearch<T> multisearch) {
        this.multisearch = multisearch;
    }

    
    public Integer getNbpage() {
        return nbpage;
    }

    public void setNbpage(Integer nbpage) {
        this.nbpage = nbpage;
    }
    

    
    @JsonIgnore
    public BddObject getObj() {
        return obj;
    }

    public void addHeader(MappingLabel head){
        this.getEntete().add(head);
    }
    public void setObj(T obj) {
        this.obj = obj;
    }

    public String getValuekey() {
        return valuekey;
    }

    public void setValuekey(String valuekey) {
        this.valuekey = valuekey;
    }

    public boolean isInfoperso() {
        return infoperso;
    }

    public void setInfoperso(boolean infoperso) {
        this.infoperso = infoperso;
    }

    public MappingLabel getLink() {
        return link;
    }

    public void setLink(MappingLabel link) {
        this.link = link;
    }

  
    

    public ArrayList<T> getListe() {
        return liste;
    }

    public void setListe(ArrayList<T> liste) {
        this.liste = liste;
    }
    


    public ArrayList<MappingLabel> getEntete() {
        if(entete==null)entete=new ArrayList<MappingLabel>();
        return entete;
    }

    public void setEntete(ArrayList<MappingLabel> entete) {
        this.entete = entete;
    }
    public ListTree(ArrayList<T> data,T obj,String valuekey){
        this.setListe(data);
        this.setObj(obj);
        this.setInfoperso(true);
        this.setValuekey(valuekey);
        this.initHeaders();
       
    }
    public ListTree(ArrayList<T> data,T obj){
        this.setListe(data);
        this.setObj(obj);
        this.setInfoperso(false);
        this.initHeaders();
       
    }

    public ListTree() {
    }
    
    
    public void initHeaders(){
        Field[]showable=this.getShowableField();
        for(Field f:showable){
            String label=f.getAnnotation(TreeField.class).value();
            if(label==null)label=f.getName();
            MappingLabel head=new MappingLabel(label,f.getName());
            this.addHeader(head);
        }
    }
    @JsonIgnore
    public Field[] getShowableField(){
        Field[] fields=this.getObj().getClass().getDeclaredFields();
        ArrayList<Field>fs=new ArrayList<>();
        for(Field f:fields){
            if(f.getAnnotation(TreeField.class)!=null)fs.add(f);
        }
        return fs.toArray(new Field[fs.size()]);
    }
}
