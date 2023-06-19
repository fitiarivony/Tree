/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import exception.MappingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
public abstract  class Mere<T extends BddObject,E extends Fille> extends BddObject{
    E[]filles;
    
    public E[] getFilles() {
        return filles;
    }
     public abstract void initFilles()throws Exception;

    public void setFilles(E[] fille) {
        this.filles = fille;
    }
    
    public void makeinsert(Connect conn) throws SQLException, Exception {
        
         
             String id=this.getLastId( conn).toString();
             if(this.getFilles()!=null){
                   for(int i=0;i<getFilles().length;i++){
             this.getFilles()[i].setForeign(id);
             this.getFilles()[i].save( conn);
            
    }
             }
       
    }
    @JsonIgnore
    public Class getClassChild() throws Exception{
         Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType) {
            Type[] typeArguments = ((ParameterizedType) superClass).getActualTypeArguments();
            if (typeArguments.length >= 2) {
                Class<?> eClass = (Class<?>) typeArguments[1];
               return eClass;
            }
        }
        throw new Exception("You're not a mother");
    }
    
    
    
    @Override
    public void save(Connect conn)throws SQLException,Exception{
        try{
        
        conn.getConnection();
        conn.setuses(true);
        conn.setAutoCommit(false);
        super.save( conn); 
         makeinsert(conn);
            conn.commit(); 
            
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            conn.forceClose();
        }
 
    }

    @Override
    public void delete(Connect conn)  throws SQLException, MappingException,Exception{
        conn.setuses(true);
        conn.setAutoCommit(false);
       conn.getConnection();
        try{
              makedelete(conn);
              super.delete(conn);
            conn.commit();
            
        }catch(Exception ex){
            
        conn.rollback();
        
        }finally{
            conn.forceClose();
        }
      
    }
    public void makedelete(Connect conn)throws Exception{
        this.initFilles();
        for(int i=0;i<this.getFilles().length;i++){
         
           this.getFilles()[i].delete(conn);
        }

    }
    
    
}
