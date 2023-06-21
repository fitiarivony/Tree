/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.devis;

import back.annotations.Attribut;
import back.annotations.ForeignKey;
import back.annotations.Table;
import back.objects.BddObject;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.annotations.TreeField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import exception.MappingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import modele.Client;
import modele.artiste.V_artiste;
import modele.autre.V_autre;
import modele.lieu.V_lieu;
import modele.lieu.place.Placelieu;
import modele.lieu.place.PrixPlace;
import modele.logistique.V_logistique;
import modele.sono.V_sono;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="devis")
public class Devis extends BddObject<Devis>{
    @Attribut(name="iddevis",primary_key=true)
    Integer id;
   @InputIgnore
    @Attribut(name="numerodevis")
    String numerodevis;
   
   @TreeField("Nom devis")
    @Attribut(name="nomdevis")
    String nomdevis;
    ArrayList<Element_devis> elementdevis;
    Double prixTotal;
    
     @TreeField("Client")
     @ForeignKey(classe=Client.class,fieldlabel="nomclient",fieldvaleur="id")
    @Attribut(name="idclient")
    Integer idclient;
     @TreeField("Date heure")
     @Attribut(name="dateheure")
     Timestamp dateheure;
    ListePrestation prestation;
    ListeElement liste;
    ArrayList<PrixPlace> prixplace;
    
    @Attribut(name="vita")
    Integer vita;

    public Integer getVita() {
        return vita;
    }

    public void setVita(Integer vita) {
        this.vita = vita;
    }
    

    public Timestamp getDateheure() {
        return dateheure;
    }

    public void setDateheure(Timestamp dateheure) {
        this.dateheure = dateheure;
    }

    public ArrayList<PrixPlace> getPrixplace() {
        return prixplace;
    }

    public void setPrixplace(ArrayList<PrixPlace> prixplace) {
        this.prixplace = prixplace;
    }
    
    
    public ListeElement getListe() {
        return liste;
    }

    public void setListe(ListeElement liste) {
        this.liste = liste;
    }
    
    public ListePrestation getPrestation() {
        return prestation;
    }

    public void setPrestation(ListePrestation prestation) {
        this.prestation = prestation;
    }
    

    public Integer getIdclient() {
        return idclient;
    }

    public void setIdclient(Integer idclient) {
        this.idclient = idclient;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }
    

    public ArrayList<Element_devis> getElementdevis() {
        return elementdevis;
    }

    public void setElementdevis(ArrayList<Element_devis> elementdevis) {
        this.elementdevis = elementdevis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumerodevis() {
        return numerodevis;
    }

    public void setNumerodevis(String numerodevis) {
        this.numerodevis = numerodevis;
    }

    public String getNomdevis() {
        return nomdevis;
    }

    public void setNomdevis(String nomdevis) {
        this.nomdevis = nomdevis;
    }
    
    public void initVita(Connect conn) throws Exception{
        super.update(conn);
    }
    
    @Override
    public void save(Connect conn) throws ClassNotFoundException, SQLException, Exception {
        try{
            conn=new Connect();
              conn.initSpring();
        conn.setAutoCommit(false);
        conn.setuses(true);
          super.save(conn);   
          this.makeinsert(conn);
          conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.forceClose();
        }
        //To change body of generated methods, choose Tools | Templates.
    }
     public void makeinsert(Connect conn) throws SQLException, Exception {
             Integer id=(Integer) this.getLastId(conn);
             if(this.getElementdevis()!=null){
                   for(Element_devis el:this.getElementdevis()){
                    el.setIddevis(id);
                    el.save(conn);
                }
             }
          //   System.out.println(this.getPrixplace());
             if(this.getPrixplace()!=null){
                   for(PrixPlace el:this.getPrixplace()){
                    el.setIddevis(id);
                    el.save(conn);
                }
             }
    }
    

    @Override
    public String toString() {
        return "Devis{" + "id=" + id + ", numerodevis=" + numerodevis + ", nomdevis=" + nomdevis + ", devis=" + elementdevis + '}';
    }
    @Override
    public void update(Connect conn) throws SQLException, Exception{
         conn=new Connect();
        try{
            conn.initSpring();
           conn.setuses(true);
           conn.setAutoCommit(false);
            super.update(conn);   
           Element_devis element=new Element_devis();
           element.setIddevis(this.getId());
          
           PrixPlace price=new PrixPlace();
           price.setIddevis(this.getId());
             ArrayList<Element_devis>taloha=element.getByIds(conn);
             ArrayList<PrixPlace>before=price.getByIds(conn);
             this.dropDeleted(taloha, conn);
             this.updateElement(conn);
             this.addNewElement(conn);
             
             this.operatingPrixPlace(before, conn);
             conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.forceClose();
        }
       
    }
  
    public void calculDevis() throws Exception{
        Double prixTotal=0.0;
        
         prixTotal=-this.calculDepense()+this.calculRecette();
        this.setPrixTotal(prixTotal);
    }
    public Double calculDepense(){
        Double prixTotal=0.0;
         if(this.getElementdevis()!=null){
                   for(Element_devis el:this.getElementdevis()){
                      if(el.getDuree()==null)el.setDuree(1.0);
                      if(el.getNb()==null)el.setNb(1.0);
                      prixTotal+=el.getTarif()*el.getDuree()*el.getNb();
                }
             }
         return prixTotal;
    }
    
    public Double calculRecette() throws SQLException, Exception{
        Connect conn=new Connect();
        conn.setuses(true);
        conn.initSpring();
        Double reponse=0.0;
        try{
        for(Element_devis el:this.getElementdevis()){
            for(PrixPlace place:this.getPrixplace()){
                if(el.getIdprestation()==place.getIdlieu()){
                    Placelieu lieu=new Placelieu();
                    lieu.setIdlieu(place.getIdlieu());
                    lieu.setIdcategorieplace(place.getIdcategorieplace());
                    lieu=lieu.getByIds(conn).get(0);
                    reponse+=lieu.getNombre()*place.getPrix();
                }
            }
        }  
        }finally{
            conn.forceClose();
        }
        return reponse;
    }
    @JsonIgnore
    public Devis getDevisOnly() throws MappingException, Exception{
        Connect conn=new Connect();
        conn.initSpring();
        return super.getById(conn).get(0);
    }
    
    public void dropDeleted(ArrayList<Element_devis>taloha,Connect conn) throws Exception{
      
        Integer indice=0;
          System.out.println(taloha+"----checking");      
         for(int i=0;i<taloha.size();i++){
             boolean isthere=false;
        for(int j=0;j<this.getElementdevis().size();j++){
               //update sa insert le vaovao
               indice=i;
               if(taloha.get(i).getId().equals(this.getElementdevis().get(j).getId()) && this.getElementdevis().get(j).getId()!=null){
                   System.out.println(taloha.get(i).getId()+"----"+this.getElementdevis().get(j).getId());
                   isthere=true;
                   break;
               }
           }
            System.out.println(taloha.get(indice)+" ==> "+isthere);
             if(!isthere){
                 taloha.get(indice).delete(conn);
                // taloha.remove(indice);
             }
        }
      
    }
    
    
    public void updateElement(Connect conn) throws Exception{
         for(Element_devis dev:this.getElementdevis()){
             if(dev.getId()!=null){
                 dev.update(conn);
             }
         }
    }
     public void addNewElement(Connect conn) throws Exception{
         for(Element_devis dev:this.getElementdevis()){
             if(dev.getId()==null){
                 dev.setIddevis(this.getId());
                 dev.save(conn);
             }
         }
    }
     
     public void operatingPrixPlace(ArrayList<PrixPlace> taloha,Connect conn)throws Exception{
         this.dropPrixPlace(taloha, conn);
         this.addNewPlace(conn);
         this.updatePlace(conn);
     }
    public void dropPrixPlace(ArrayList<PrixPlace>taloha,Connect conn)throws Exception{
        Integer indice=0;
          System.out.println(taloha+"----checking");  
         for(int i=0;i<taloha.size();i++){
             boolean isthere=false;
        for(int j=0;j<this.getPrixplace().size();j++){
               //update sa insert le vaovao
               indice=i;
               if(taloha.get(i).getId().equals(this.getPrixplace().get(j).getId()) && this.getPrixplace().get(j).getId()!=null){
                   System.out.println(taloha.get(i).getId()+"----"+this.getPrixplace().get(j).getId());
                   isthere=true;
                   break;
               }
           }
            
             if(!isthere){
                
                 taloha.get(indice).delete(conn);
                // taloha.remove(indice);
             }
        }
    }
    public void addNewPlace(Connect conn)throws Exception{
         for(PrixPlace dev:this.getPrixplace()){
             if(dev.getId()==null){
                 dev.setIddevis(this.getId());
                 dev.save(conn);
             }
         }
    }
    public void updatePlace(Connect conn)throws Exception{
           for(PrixPlace dev:this.getPrixplace()){
             if(dev.getId()!=null){
                 dev.update(conn);
             }
         }
    }

    @Override
    public ArrayList<Devis> getById(Connect conn) throws SQLException, MappingException, Exception {
         
        try{
            conn=new Connect();
            conn.setuses(true);
            conn.initSpring();
          ArrayList<Devis> devs=super.getById(conn);
        Devis dev=devs.get(0);
        Element_devis el=new Element_devis();
        el.setIddevis(this.getId());
        PrixPlace place=new PrixPlace();
        place.setIddevis(this.getId());
        
        this.setPrixplace(place.getByIds(conn));
        this.setIdclient(dev.getIdclient());
            
        this.setElementdevis(el.getByIds(conn));
        this.setNomdevis(dev.getNomdevis());
        this.setNumerodevis(dev.getNumerodevis());
        tri();
        return devs ;//To change body of generated methods, choose Tools | Templates.
        }finally{
            conn.forceClose();
        }
       
    }
    public void tri() throws Exception{
        ListePrestation presta=new ListePrestation();
        ListeElement liste=new ListeElement();
        for(Element_devis el:this.getElementdevis().toArray(new Element_devis[this.getElementdevis().size()])){
            if(this.getType(el, presta, liste)==0)throw new Exception("It has no types");
        }
        this.setPrestation(presta);
        this.setListe(liste);
    }
   public Integer getType(Element_devis el,ListePrestation listes,ListeElement liste){
    
       //is artiste
       for(V_artiste v:listes.getArtiste()){
           if(v.getId()==el.getIdprestation()){
               liste.getArtiste().add(el);
               return 1;
           }
       }
       //is sono 
        for(V_sono v:listes.getSono()){
            if(v.getId()==el.getIdprestation()){
                liste.getSono().add(el);
                return 1;
            }
       }
        //is logistique
         for(V_logistique v:listes.getLogistique()){
            if(v.getId()==el.getIdprestation()){
                liste.getLogistique().add(el);
                return 1;
            }
       }
        //is lieu
         for(V_lieu v:listes.getLieu()){
            if(v.getId()==el.getIdprestation()){
                liste.getLieu().add(el);
                return 1;
            }
       }
         //is autre
           for(V_autre v:listes.getAutre()){
            if(v.getId()==el.getIdprestation()){
                liste.getAutre().add(el);
                return 1;
            }
       }
return 0;
       
   }

    @Override
    public void delete(Connect conn) throws SQLException, MappingException, Exception {
       
        conn.setuses(true);
        conn.setAutoCommit(false);
        try{
            Element_devis el=new Element_devis();
            el.setIddevis(this.getId());
            ArrayList<Element_devis>devs=el.getByIds(conn);
            for(Element_devis dev:devs)dev.delete(conn);
            
             PrixPlace place=new PrixPlace();
            place.setIddevis(this.getId());
            ArrayList<PrixPlace>prices=place.getByIds(conn);
            for(PrixPlace toerana:prices)toerana.delete(conn);
            super.delete(conn);
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.forceClose();
        }
      //  super.delete(conn); //To change body of generated methods, choose Tools | Templates.
        
    }
   
    
   
     
     
     
    
    
}
