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
public enum KeyWord {
    current_timestamp,current_date;
    public String returnkeyWord(KeyWord teny){
         switch(teny){
            case current_timestamp:
                return "current_timestamp";
            case current_date:
                return "current_date";
         }
         return "";
    }
}
