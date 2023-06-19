/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfwrite;

import java.util.ArrayList;

/**
 *
 * @author FITIA ARIVONY
 */
public class CSS {
    String classe;
    ArrayList<CSSElement>css;
    String typeClasse;
    
    
    

    public String getTypeClasse() {
        if(typeClasse==null)this.setTypeClasse(".");
        return typeClasse;
    }

    public void setTypeClasse(String typeClasse) {
        this.typeClasse = typeClasse;
    }
    
    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
    

    
    public ArrayList<CSSElement> getCss() {
        return css;
    }
    public void addCSSElement(CSSElement css){
        if(this.getCss()==null){
            this.setCss(new ArrayList<CSSElement>());
        }
        this.getCss().add(css);
    }
     public void addCSSElement(String key,String value){
        if(this.getCss()==null){
            this.setCss(new ArrayList<CSSElement>());
        }
        this.getCss().add(new CSSElement(key,value));
    }
     
    public String genereCSS(){
        String css=this.getTypeClasse()+this.getClasse()+"{\n";
        for(CSSElement cs:this.getCss()){
            css+=cs.genereCSSElement();
        }
        css+="}\n";
        return css;
    }

    public void setCss(ArrayList<CSSElement> css) {
        this.css = css;
    }

    public CSS(String classe) {
        this.classe = classe;
    }

    public CSS(String classe, String typeClasse) {
        this.classe = classe;
        this.typeClasse = typeClasse;
    }
    
    
    
}
