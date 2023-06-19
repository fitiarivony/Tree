/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.dao.where;

/**
 *
 * @author FITIA ARIVONY
 */
public enum GenCase {
    equal,eqless,less,eqgreat,great,interval,like,between,in,different,isnull,notnull;
    
    public static String getQuery(GenCase cas,String attribut,Valeur value){
        
         if(cas==null)return attribut+"= ?";
        switch(cas){
            case equal:
                return attribut+"=?";     
            case eqless:
                return attribut+"<=?";
            case less:
                return attribut+"<?";
            case eqgreat:
                return attribut+">=?";
            case great:
                return attribut+">?";
            case like:
                return attribut+" like ?";
            case between:
                return attribut+">? and "+attribut+" <?";
            case interval:
                return attribut+">=? and "+attribut+" <=?";
            case different:
                return attribut+"!=?";
            case isnull:
            return attribut+" is null";
            case notnull:
            return attribut+" is not null";
            case in:
                String data=attribut+"  in (";
                Object[]obj=(Object[])value.getMax();
                for(int i=0;i<obj.length;i++)data+="?,";
                data=data.substring(0,data.length()-1);
                data+=")";
                return data;
        }
        return attribut+"=?";
    }
   
    
}
