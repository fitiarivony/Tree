/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubble;

import back.dao.Criteri;
import back.dao.where.Filtre;
import back.dao.where.GenCase;
import back.dao.where.Valeur;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
public class Bubble {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws SQLException, Exception {
        // TODO code application logic here
        

       

       
        
        
//        
//        // Supposons que vous avez une classe appelée "MaClasse" et une chaîne de caractères appelée "maValeur"
//Class<Double> classeObjet = Double.class;
//String maValeur = "123";
//
//// Convertir la chaîne de caractères en objet
//Object objet = classeObjet.getMethod("valueOf", String.class).invoke(null, maValeur);
//        System.out.println(objet);    
//        // La chaîne de caractères représentant la date
//String dateString = "2022-06-01";
//
//// Conversion de la chaîne de caractères en une instance de java.util.Date
//Date date = Date.valueOf(dateString);
//
//// Utilisation de la date convertie
//System.out.println(date);
Connect conn=new Connect();
try{
    conn=new Connect("jdbc:postgresql://localhost:8089/evalmai2022","postgres","ROOT");
    MyTableRow table=new MyTableRow();
    table.setDuration(Duration.ofHours(2).plusMinutes(30));
    table.setName("Coucou");
    //table.save(conn);
    Admin admin=new Admin();
    admin.setIdentifiant("admin");
    admin.setMdp("mdp");
    // admin.login(conn);
    admin=new Admin();
    admin.setToken("d033e22ae348aeb5660fc2140aec35850c4da997");
 System.out.println(admin.isConnected(conn));
    // Admin mimi=new Admin();
     //mimi.setIdadmin("Coucou");
     //mimi.setToken("dffsdfsdfsdfsdfs");
     
    // admin.update(conn, mimi);
}finally{
  conn.forceClose();
}
    }
    
}
