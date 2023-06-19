/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfwrite;

/**
 *
 * @author FITIA ARIVONY
 */
public class Person {
    String nom;
    String prenom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Person() {
    }

    public Person(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Person{" + "nom=" + nom + ", prenom=" + prenom + '}';
    }
    
}
