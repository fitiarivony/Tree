/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.objects;

import back.annotations.Attribut;
import back.annotations.Expiration;
import back.annotations.InitToken;
import back.annotations.Mdp;
import back.annotations.Token;
import back.dao.where.Filtre;
import back.dao.where.GenCase;
import back.dao.where.Valeur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import exception.LoginException;
import exception.MappingException;
import helper.Hashing;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import utils.Connect;
import utils.OutilsBdd;

/**
 *
 * @author FITIA ARIVONY
 */
public abstract class Loggable<T extends BddObject> extends BddObject<T>{
     public T login(Connect conn) throws Exception{
        conn.setuses(true);
        this.hashingMdp();
        ArrayList<T> o=this.getByIds(conn);
        if(o.size()==0)throw new LoginException("Identifiant ou mot de passe errone");
        T obj=o.get(0);
        this.set(this.getPrimary_key(),obj.get(obj.getPrimary_key()));
        setInfo(conn);
       
       return obj;
    }
    
    public void hashingMdp() throws Exception{
        Field f=this.getMdpAnnotation();
        if(f!=null){
            
             Mdp hashage=f.getAnnotation(Mdp.class);
        String mdp=(String)this.get(f);
        this.set(this.getMdpAnnotation(),Hashing.getHashing(hashage.hashing(), mdp));
            
        }
    }
    @JsonIgnore
    public Field getMdpAnnotation(){
          for(Field f:this.getClass().getDeclaredFields()){
              
            Mdp tok=f.getAnnotation(Mdp.class);
          
            
            if(tok!=null)return f;
        }
        return null;
    }
    
    public void initExpiration() throws IllegalArgumentException, IllegalAccessException{
        Timestamp tmp=new Timestamp(System.currentTimeMillis());
        tmp.setMinutes(tmp.getMinutes()+15);
        tmp.setHours(tmp.getHours()+5);
        this.set(this.getExpirationField(),tmp);
    }
    public void setToken(String token) throws IllegalArgumentException, IllegalAccessException{
         Field f=this.getTokenAnnotation();
            this.set(f, token);
    }
    
    public void setInfo(Connect conn) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception{
        Method meth=this.getInitToken();
        if(meth!=null){
            InitToken hashage=meth.getAnnotation(InitToken.class);
            String token=(String)meth.invoke(this,null);
            if(token!=null){
            token=Hashing.getHashing(hashage.hashing(), token);
            Field f=this.getTokenAnnotation();
            this.set(f, token);
            this.initExpiration();
            System.out.println(this); 
            super.update(conn);
            }
           
        }
    }
   @JsonIgnore
    public Field getExpirationField(){
        for(Field f:this.getClass().getDeclaredFields()){
            Expiration tok=f.getAnnotation(Expiration.class);
            if(tok!=null)return f;
        }
        return null;
    }
    @JsonIgnore
    public Method getInitToken(){
        for(Method o:this.getClass().getMethods()){
            InitToken token=o.getAnnotation(InitToken.class);
            if(token!=null)return o;
        }
        return null;
    }
    @JsonIgnore
    public Field getTokenAnnotation(){
        for(Field f:this.getClass().getDeclaredFields()){
            Token tok=f.getAnnotation(Token.class);
            if(tok!=null)return f;
        }
        return null;
    }
    public abstract String initToken();
    public T isConnected(Connect conn) throws Exception{
        Attribut at=this.getExpirationField().getAnnotation(Attribut.class);
        this.getCrit().where(new Filtre(GenCase.great,at.name(),new Valeur(new Timestamp(System.currentTimeMillis()))));
        ArrayList<T> data=this.getByIds(conn);
        if(data.isEmpty()){
            return null;
        }
        return data.get(0);
    }
    public void logout(Connect conn) throws MappingException, Exception{
        ArrayList<T> data=this.getByIds(conn);
         if(data.isEmpty())throw new Exception("You' re not an admin");
         this.setPrimary_key(data.get(0).get(data.get(0).getPrimary_key()));
        String sql="UPDATE "+this.getTable().name()+" set "+OutilsBdd.getNonPrimary(this.getTokenAnnotation()).name()+"=null,";
        sql+=OutilsBdd.getNonPrimary(this.getExpirationField()).name()+"=null where "+
                OutilsBdd.getPrimary(this.getPrimary_key()).name()+"="+this.get(this.getPrimary_key());
       this.executeTransaction(sql, conn);
    }

    @Override
    public void save(Connect conn) throws ClassNotFoundException, SQLException, Exception {
        this.hashingMdp();
        super.save(conn); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Connect conn) throws SQLException, Exception {
        if(this.get(this.getMdpAnnotation())!=null)this.hashingMdp();
        super.update(conn); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

