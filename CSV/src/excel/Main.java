/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import java.time.Duration;

/**
 *
 * @author FITIA ARIVONY
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Donnee[]donne=new Donnee[2];
        for(int i=0;i<donne.length;i++)donne[i]=new Donnee("essai",10);
        CSV cs=new CSV(donne,";");
        System.out.println(cs.returnCSV());
    
    }
    
}
