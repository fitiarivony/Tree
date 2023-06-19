/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

/**
 *
 * @author FITIA ARIVONY
 */
public class Donnee {
    String donne;
    Integer data;

    public String getDonne() {
        return donne;
    }

    public void setDonne(String donne) {
        this.donne = donne;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Donnee(String donne, Integer data) {
        this.donne = donne;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Donnee{" + "donne=" + donne + ", data=" + data + '}';
    }
    
}
