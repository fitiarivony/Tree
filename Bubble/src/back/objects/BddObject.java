
package back.objects;


import back.dao.where.Filtre;
import back.dao.where.GenCase;
import back.dao.where.Intervalle;
import exception.DAOException;
import exception.MappingException;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import utils.Connect;
import utils.OutilsBdd;


/**
 *
 * @author FITIA ARIVONY
 */
public class BddObject<T extends BddObject> extends OutilsBdd{        
   
    public void setPrimary_key(Object value) throws IllegalArgumentException, Exception{
        Field f=this.getPrimary_key();
        this.set(f, value);
    }
    
    
    
     public  void save(Connect conn)throws ClassNotFoundException,SQLException, Exception{
        PreparedStatement stat=null;
//        System.out.println(o);
        try{
          conn.getConnection();
            String sql="INSERT INTO "+this.getTable().name()+"(";
            Field[]fields=this.getTraitable();
            for(int i=0;i<fields.length;i++){
                if(i==fields.length-1)sql+=OutilsBdd.getNonPrimary(fields[i]).name()+")";
                else sql+=OutilsBdd.getNonPrimary(fields[i]).name()+",";
                
            }
         
        sql+=" values (";
       
        ArrayList<Object>valeurs=this.valeurs(fields);
        for(int i=0;i<valeurs.size();i++){
            if(i==fields.length-1)sql+="? )";
            else sql+="? ,";
        }
        stat=conn.prepareStatement(sql);
          for(int i=0,j=1;i<valeurs.size();i++,j++){
              
            //  System.out.println(valeurs.get(i).toString()+"-------").?
              stat.setObject(j,valeurs.get(i));
        }
      System.out.println(stat);
         stat.executeUpdate();
        }finally{
           
            if(stat!=null){
                stat.close();
            }
             conn.close();
        }
        
          
    }
     public  void update(Connect conn) throws SQLException, Exception{
     
        PreparedStatement stat=null;
         String sql="UPDATE "+this.getTable().name()+" set ";
        try{
             conn.getConnection();
             Field[] attributs=this.getTraitable();
             for(int i=0;i<attributs.length;i++){
               //  System.out.println(attributs[i]);
                 sql+=OutilsBdd.getNonPrimary(attributs[i]).name()+" = ";
                 if(i!=attributs.length-1){
                   sql+="? ,";
                }else {
                     sql+="? ";
                 }
             }
             sql+="   where  ";
          
                sql+=OutilsBdd.getPrimary(this.getPrimary_key()).name()+"=";
                sql+=" ? ";
             stat=conn.prepareStatement(sql);
           ArrayList<Object>valeurs=this.valeurs(attributs);
            int j;
            int i;
           for(i=0,j=1;i<valeurs.size();i++,j++){
              
              stat.setObject(j,valeurs.get(i));
        }
              stat.setObject(j,this.get(this.getPrimary_key()));
       
          System.out.println(stat);
           stat.executeUpdate();
        }finally{
            if(stat!=null)stat.close();
            
            if(conn!=null)conn.close();
            
        }
    }
     
     
     public  void update(Connect conn,T vaovao) throws SQLException, Exception{
     
        PreparedStatement stat=null;
         String sql="UPDATE "+this.getTable().name()+" set ";
        try{
             conn.getConnection();
             Field[] attributsvaovao=vaovao.getTraitable();
             for(int i=0;i<attributsvaovao.length;i++){
               //  System.out.println(attributs[i]);
                 sql+=OutilsBdd.getNonPrimary(attributsvaovao[i]).name()+" = ";
                 if(i!=attributsvaovao.length-1){
                   sql+="? ,";
                }else {
                     sql+="? ";
                 }
             }
             sql+="   where  ";
               Field[] attributs=this.getTraitable();
              for(int i=0;i<attributs.length;i++){
               //  System.out.println(attributs[i]);
                 sql+=OutilsBdd.getNonPrimary(attributs[i]).name()+" = ";
                 if(i!=attributs.length-1){
                   sql+="? and";
                }else {
                     sql+="? ";
                 }
             }
             stat=conn.prepareStatement(sql);
           ArrayList<Object>valeurs=vaovao.valeurs(attributsvaovao);
            int j;
            int i;
            
           for(i=0,j=1;i<valeurs.size();i++,j++){
              
              stat.setObject(j,valeurs.get(i));
        }
           valeurs=this.valeurs(attributsvaovao);
           int k=0;
             for(i=0,k=j;i<valeurs.size();i++,j++){
              
              stat.setObject(k,valeurs.get(i));
        }
       
          System.out.println(stat);
           stat.executeUpdate();
        }finally{
            if(stat!=null)stat.close();
            
            if(conn!=null)conn.close();
            
        }
    }
     
     
     
     public  void delete(Connect conn) throws SQLException, MappingException,Exception{
        
         PreparedStatement stat=null;
         String sql="DELETE FROM "+this.getTable().name();
         try{
             sql+="  where ";
            sql+=OutilsBdd.getPrimary(this.getPrimary_key()).name()+"=";
                     sql+=" ? ";
           //  System.out.println(sql);
          
           conn.getConnection();
              stat=conn.prepareStatement(sql);      
              stat.setObject(1,this.get(this.getPrimary_key()));
           System.out.println(stat);
            stat.executeUpdate();
         }finally{
             if(stat!=null){
                 stat.close();
             }
             if(conn!=null){
                 conn.close();
             }
         }
         
     }
     
     
     
     
     public Integer countall(Connect conn) throws MappingException, SQLException, Exception{
         String sql="SELECT count(*) from  "+this.getTable().name();
          PreparedStatement stat=null; 
         ResultSet res=null;
         Integer val=0;
         Filtre[] rest=this.storeRestrictions();
        System.out.println(rest.length);
       for(int i=0;i<rest.length;i++){
           sql=OutilsBdd.getWhere(i, sql);
         
           sql+=rest[i].genereCase();
       }
         try{
             conn.getConnection();
             System.out.println(this.getCrit()+"  init critere");
             this.getCrit().setLimit(null);
             this.getCrit().setOffset(null);
             String critere=this.getCrit().initCritere();
             sql+=critere;
             System.out.println("Critere="+critere);
             System.out.println("Avant traitement:"+sql);
             stat=conn.prepareStatement(sql);
              for(int i=0,j=1;i<rest.length;i++,j++){
                 if(rest[i].getCas()==GenCase.isnull || rest[i].getCas()==GenCase.notnull ){
                  j--;
                  continue;
              }
             else if(rest[i].getValue().getMax() instanceof Object[]){
                  Object[]data=(Object[])rest[i].getValue().getMax();
                 // System.out.println(j+"----tab");
                  for(int k=0;k<data.length;k++,j++){
                      stat.setObject(j, data[k]);
                  }
                  j--;
                  //System.out.println(j+"----mivoaka tab");     
              }
              else{
                 // System.out.println(j+"---non tab");
                  stat.setObject(j,rest[i].getValue().getMax());
              // System.out.println(rest[i].getValue());
               if(rest[i].getValue() instanceof Intervalle){
                    j++;
                   stat.setObject(j,((Intervalle)rest[i].getValue()).getMin());
               }
              }
               
           }
            res=stat.executeQuery();
             System.out.println("Apres traitement:"+stat);
             while(res.next()){
                 return res.getInt("count");
             }
         }finally{
              if(res!=null){
                 res.close();
             }
             if(stat!=null){
                 stat.close();
             }
             if(conn!=null)conn.close();
         } 
        return val;
     }
     public  ArrayList<T> selectAll(Connect conn) throws SQLException, MappingException, DAOException, Exception{
         String sql="SELECT * from "+this.getTable().name();
        
         PreparedStatement stat=null;
         ResultSet res=null;
       
         try{
             conn.getConnection();
             System.out.println(this.getCrit()+"  init critere");
             String critere=this.getCrit().initCritere();
             sql+=critere;
             System.out.println("Critere="+critere);
             System.out.println("Avant traitement:"+sql);
             stat=conn.prepareStatement(sql);
             res=stat.executeQuery();
             System.out.println("Apres traitement:"+stat);
             
            return this.extractResult(res);             
         }finally{
             if(res!=null){
                 res.close();
             }
             if(stat!=null){
                 stat.close();
             }
             this.setCrit(null);
         }
       
     }
     
     
      public  ArrayList<T> getById(Connect conn) throws SQLException, MappingException, Exception{
         String sql="SELECT * from "+this.getTable().name();
         
         PreparedStatement stat=null;
         ResultSet res=null;
         try{
             sql+="  where ";
             sql+=OutilsBdd.getPrimary(this.getPrimary_key()).name()+"=";
                     sql+=" ? ";
            //System.out.println(sql);
          sql+=this.getCrit().initCritere();
             conn.getConnection();
               System.out.println("Avant traitement:"+sql);
              stat=conn.prepareStatement(sql);
              
              stat.setObject(1,this.get(this.getPrimary_key()));
           System.out.println("Apres traitement:"+stat);
             res=stat.executeQuery();
            return this.extractResult(res);   
         }finally{
             if(res!=null){
                 res.close();
             }
             if(stat!=null){
                 stat.close();
             }
             if(conn!=null){
                 conn.close();
             }
             this.setCrit(null);
         }
        
     }
      
    
        
  
   public  Object getLastId(Connect conn) throws SQLException, Exception{
       String sql="";
       if(this.getPrimary_key().getType().equals(String.class)){
              sql="select "+OutilsBdd.getPrimary(this.getPrimary_key()).name()+ "   dernier from "
               +this.getTable().name()
               +" order by substring("
               +OutilsBdd.getPrimary(this.getPrimary_key()).name()
               +"  from 4)::integer desc limit 1";
       }else{
           sql="select "+OutilsBdd.getPrimary(this.getPrimary_key()).name()+ "   dernier from "
               +this.getTable().name()
               +" order by "
               +OutilsBdd.getPrimary(this.getPrimary_key()).name()
               +"  desc limit 1";
       }
     
       PreparedStatement stat=null;
       ResultSet res=null;
       try{
           conn.getConnection();
           stat=conn.prepareStatement(sql);
           System.out.println(stat);
           res=stat.executeQuery();
           while(res.next())return res.getObject("dernier");
       }catch(Exception e){
           throw e;
       }finally{
           if(res!=null)res.close();
           if(stat!=null)stat.close();
       }
       throw new Exception("No last id");
   }
   
   
   
    public  ArrayList<T> getByIds(Connect conn) throws Exception{
       
        
       // Field[] fields=this.getTraitable();
        
         
       String sql="SELECT * FROM "+this.getTable().name();
       Filtre[] rest=this.storeRestrictions();
       
        System.out.println(rest.length);
       for(int i=0;i<rest.length;i++){
           sql=OutilsBdd.getWhere(i, sql);
         
           sql+=rest[i].genereCase();
       }
       PreparedStatement stat=null;
         ResultSet res=null;
       try{
          conn.getConnection();
           sql+=this.getCrit().initCritere();
             System.out.println("Avant traitement:"+sql);
           stat=conn.prepareStatement(sql);
           
          for(int i=0,j=1;i<rest.length;i++,j++){
              if(rest[i].getCas()==GenCase.isnull || rest[i].getCas()==GenCase.notnull ){
                  j--;
                  continue;
              }
              else if(rest[i].getValue().getMax() instanceof Object[]){
                  Object[]data=(Object[])rest[i].getValue().getMax();
                 // System.out.println(j+"----tab");
                  for(int k=0;k<data.length;k++,j++){
                      stat.setObject(j, data[k]);
                  }
                  j--;
                  //System.out.println(j+"----mivoaka tab");     
              }
              else{
                 // System.out.println(j+"---non tab");
                  stat.setObject(j,rest[i].getValue().getMax());
              // System.out.println(rest[i].getValue());
               if(rest[i].getValue() instanceof Intervalle){
                    j++;
                   stat.setObject(j,((Intervalle)rest[i].getValue()).getMin());
               }
              }
               
           }
           System.out.println("Apres traitement:"+stat);
         res=stat.executeQuery();
         return this.extractResult(res);
       }
       finally{
        if(res!=null)res.close();
        if(stat!=null)stat.close();
        if(conn!=null)conn.close();
    }
       
        
    }
    public Object transform(Object obj,Field field){
        if(field.getType().equals(Duration.class) && obj!=null){
         DateTimeFormatter formater=DateTimeFormatter.ofPattern("HH:mm:ss");
        //    System.out.println(req.getParameter("duree"+i));
         TemporalAccessor accessor = formater.parse((String)obj);
         Duration duration = Duration.ofHours(accessor.get(ChronoField.HOUR_OF_DAY))
                 .plusMinutes(accessor.get(ChronoField.MINUTE_OF_HOUR))
                 .plusSeconds(accessor.get(ChronoField.SECOND_OF_MINUTE));
            return duration;
        }
        return obj;
    }
   public  ArrayList<T> extractResult(ResultSet res)throws Exception{
        Class cl=this.getClass();
        ArrayList<T>liste=new ArrayList<T>();
        Field[]colonne=this.allAttributes();
         while(res.next()){     
                 Object valiny=new Object();
                valiny=cl.newInstance();
            for(int i=0;i<colonne.length;i++){
                if(colonne[i].getType().equals(Duration.class)){
                    Object val=res.getString(OutilsBdd.getAttribut(colonne[i]).name());
                     OutilsBdd.setValeur(valiny,colonne[i].getName(),this.transform(val,colonne[i]));
                     continue;
                }
                if(res.getObject((OutilsBdd.getAttribut(colonne[i]).name()))!=null){
                   Object resultat=res.getObject((OutilsBdd.getAttribut(colonne[i]).name()));
                   OutilsBdd.setValeur(valiny,colonne[i].getName(),resultat);
               }
                
            }
          //  System.out.println(valiny);
            liste.add((T)valiny);
        }
       
         return liste;
    }
   public  ArrayList<T> executeQuery(String sql,Connect conn,String attribut)throws Exception{
       PreparedStatement stat=null;
       ResultSet res=null;
       ArrayList<T>objets=new ArrayList<T>();
       try{
         conn.getConnection();
          sql+=this.getCrit().initCritere();
          
            System.out.println("Avant traitement:"+sql);
           stat=conn.prepareStatement(sql);
          
           System.out.println("Apres traitement:"+stat);
           res=stat.executeQuery();
           while(res.next()){
               objets.add((T)res.getObject(attribut));
           }
           return objets;
       }finally{
           if(res!=null)res.close();
           if(stat!=null)stat.close();
           if(conn!=null)conn.close();
           //this.setCrit(null);
       }
   }
   public  ArrayList<T> executeQuery(String sql,Connect conn)throws Exception{
       PreparedStatement stat=null;
       ResultSet res=null;
       
      try{
          
           conn.getConnection();
           sql+=this.getCrit().initCritere();
             System.out.println("Avant traitement:"+sql);
           stat=conn.prepareStatement(sql);
           res=stat.executeQuery();
            System.out.println("Apres traitement:"+stat);
           return this.extractResult(res);
       
      }finally{
          if(res!=null)res.close();
          if(stat!=null)stat.close();
          if(conn!=null)conn.close();
          //this.setCrit(null);
      }
   }
   
      public  void executeTransaction(String sql,Connect conn)throws Exception{
       PreparedStatement stat=null;
      try{
          
           conn.getConnection();
           sql+=this.getCrit().initCritere();
             System.out.println("Avant traitement:"+sql);
           stat=conn.prepareStatement(sql);
           stat.executeUpdate();
            System.out.println("Apres traitement:"+stat);
       
      }finally{
          if(stat!=null)stat.close();
          if(conn!=null)conn.close();
         // this.setCrit(null);
      }
   }
    
     public void insertArray(T[] intervalles,Connect conn) throws SQLException, Exception{
             
         conn.setuses(true);
         conn.setAutoCommit(false);
         for(T inter:intervalles){
             try {
                 System.out.println("--------------------------");
                 System.out.println(inter);
                inter.save(conn);

             } catch (Exception ex) {
                 ex.printStackTrace();
                conn.rollback();
               throw ex;
             }
         }
         conn.commit();
    }
        
}
