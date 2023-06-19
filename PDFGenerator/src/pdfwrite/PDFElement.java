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
public class PDFElement {
    Object obj;
    String classe;
    String titre;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public Object getObj() {
        return obj;
    }

    public PDFElement(Object obj, String classe) {
        this.obj = obj;
        this.classe = classe;
    }
     public PDFElement(Object obj, String classe,String titre) {
        this.obj = obj;
        this.classe = classe;
        this.titre=titre;
    }
    

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public PDFElement(Object obj) {
        this.obj = obj;
    }
    public String getCSSClass(){
        if(this.getClasse()!=null){
            return "class=\""+this.getClasse()+"\"";
        }
        return "";
    }
}
