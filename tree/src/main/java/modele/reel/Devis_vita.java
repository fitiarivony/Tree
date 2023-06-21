/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.reel;

import back.annotations.Attribut;
import back.annotations.ForeignKey;
import back.annotations.Table;
import back.objects.Vue;
import com.bubble.tree.annotations.InputIgnore;
import com.bubble.tree.annotations.TreeField;
import exception.DAOException;
import exception.MappingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import modele.Client;
import modele.devis.Devis;
import modele.devis.Element_devis;
import modele.lieu.place.Placelieu;
import modele.lieu.place.PrixPlace;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
@Table(name="devis_vita")
public class Devis_vita extends Vue<Devis_vita>{
    @Attribut(name="iddevis",primary_key=true)
      Integer id;
    
    
   @InputIgnore
    @Attribut(name="numerodevis")
    String numerodevis;
   
   @TreeField("Nom devis")
    @Attribut(name="nomdevis")
    String nomdevis;
   @ForeignKey(classe=Client.class,fieldlabel="nomclient",fieldvaleur="id")
    @Attribut(name="idclient")
    Integer idclient;
     @TreeField("Date heure")
     @Attribut(name="dateheure")
     Timestamp dateheure;
     @TreeField("recette")
     Double recette;
     @TreeField("depense")
     Double depense;
     @TreeField("beneficenonet")
     Double beneficenonet;
     @TreeField("taxe")
     Double taxe;
     @TreeField("beneficenet")
     Double beneficenet;
     ArrayList<Billet> billet;
     ArrayList<Element_devis>element;

    public ArrayList<Billet> getBillet() {
        return billet;
    }

    public ArrayList<Element_devis> getElement() {
        return element;
    }

    public void setElement(ArrayList<Element_devis> element) {
        this.element = element;
    }

    
    public void setBillet(ArrayList<Billet> billet) {
        this.billet = billet;
    }
    
    public Double getRecette() {
        return recette;
    }

    public void setRecette(Double recette) {
        this.recette = recette;
    }

    public Double getDepense() {
        return depense;
    }

    public void setDepense(Double depense) {
        this.depense = depense;
    }

    public Double getBeneficenonet() {
        return beneficenonet;
    }

    public void setBeneficenonet(Double beneficenonet) {
        this.beneficenonet = beneficenonet;
    }

    public Double getTaxe() {
        return taxe;
    }

    public void setTaxe(Double taxe) {
        this.taxe = taxe;
    }

    public Double getBeneficenet() {
        return beneficenet;
    }

    public void setBeneficenet(Double beneficenet) {
        this.beneficenet = beneficenet;
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

    public Integer getIdclient() {
        return idclient;
    }

    public void setIdclient(Integer idclient) {
        this.idclient = idclient;
    }

    public Timestamp getDateheure() {
        return dateheure;
    }

    public void setDateheure(Timestamp dateheure) {
        this.dateheure = dateheure;
    }

    @Override
    public ArrayList<Devis_vita> getByIds(Connect conn) throws Exception {
        ArrayList<Devis_vita> vita=super.getByIds(conn);
        
        return this.initSpectacle(vita); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Devis_vita> getById(Connect conn) throws SQLException, MappingException, Exception {
        return this.initSpectacle(super.getById(conn)); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Devis_vita> selectAll(Connect conn) throws SQLException, MappingException, DAOException, Exception {
        return this.initSpectacle(super.selectAll(conn)); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public Devis initDevis(Connect conn) throws Exception{
        Devis dev=new Devis();
        Element_devis element=new Element_devis();
        element.setIddevis(this.getId());
        dev.setElementdevis(element.getByIds(conn));
        return dev;
    }
     
    public ArrayList<Devis_vita> initSpectacle(ArrayList<Devis_vita>vita) throws MappingException, DAOException, Exception{
        Connect conn=new Connect();
        conn.initSpring();
        conn.setuses(true);
        try{
           
        for(Devis_vita tapitra:vita){
           tapitra.initVita(conn);
        }
        return vita;
        }finally{
            conn.forceClose();
        }
    }
    
    public void initVita(Connect conn) throws Exception{
         Devis dev=this.initDevis(conn);
       
            this.setElement(dev.getElementdevis());
            this.setDepense(dev.calculDepense());
            this.setRecette(this.calculRecette());
            this.setBeneficenonet(this.getRecette()-this.getDepense());
            this.setTaxe(this.getBeneficenonet()*this.getTaxe(conn));
            this.setBeneficenet(this.getBeneficenonet()-this.getTaxe());
    }
    
    
    public Double getTaxe(Connect conn) throws MappingException, DAOException, Exception{
        Taxe tax=new Taxe();
        tax=tax.selectAll(conn).get(0);
        return tax.getValeur();
    }
    public void initBillet(Connect conn) throws Exception{
        Billet bill=new Billet();
        bill.setIddevis(this.getId());
        this.setBillet(bill.getByIds(conn));
    }
 
   public Double calculRecette() throws SQLException, Exception{
        Connect conn=new Connect();
        conn.setuses(true);
        conn.initSpring();
        Double reponse=0.0;
        try{
            this.initBillet(conn);
        for(Element_devis el:this.getElement()){
            for(Billet place:this.getBillet()){
                if(el.getIdprestation()==place.getIdlieu()){
                 
                    PrixPlace lieu=new PrixPlace();
                    lieu.setIdlieu(place.getIdlieu());
                    lieu.setIdcategorieplace(place.getIdcategorieplace());
                    lieu.setIddevis(this.getId());
                    lieu=lieu.getByIds(conn).get(0);
                    reponse+=lieu.getPrix()*place.getNombre();
                }
            }
        }  
        }finally{
            conn.forceClose();
        }
        return reponse;
    }

    @Override
    public String toString() {
        return "Devis_vita{" + "id=" + id + ", numerodevis=" + numerodevis + ", nomdevis=" + nomdevis + ", idclient=" + idclient + ", dateheure=" + dateheure + ", recette=" + recette + ", depense=" + depense + ", beneficenonet=" + beneficenonet + ", taxe=" + taxe + ", beneficenet=" + beneficenet + ", billet=" + billet + ", element=" + element + '}';
    }
   
}
