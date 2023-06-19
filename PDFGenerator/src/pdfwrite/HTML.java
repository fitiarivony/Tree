/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfwrite;

import java.lang.reflect.Field;
import java.util.ArrayList;
import utils.Reflect;

/**
 *
 * @author FITIA ARIVONY
 */
public class HTML {
    ArrayList<PDFElement>obj;
    String title;
    ArrayList<CSS>css;
     String cssStyle;

    public String getCssStyle() {
        if(cssStyle==null)this.setCssStyle("");
        return cssStyle;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }
     

    public ArrayList<PDFElement> getObj() {
        return obj;
    }

    public void setObj(ArrayList<PDFElement> obj) {
        this.obj = obj;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public ArrayList<CSS> getCss() {
        return css;
    }

    public void setCss(ArrayList<CSS> css) {
        this.css = css;
    }
     public void addCSS(CSS element){
        if(this.getCss()==null)this.setCss(new ArrayList<CSS>());
        this.getCss().add(element);
    }

    public HTML(ArrayList<PDFElement> obj, String title) {
        this.obj = obj;
        this.title = title;
    }

    public HTML(ArrayList<PDFElement> obj, String title, ArrayList<CSS> css) {
        this.obj = obj;
        this.title = title;
        this.css = css;
    }
    

    public HTML() {
    }
    
 
    public String getHead(){
        return "<head>\n <title>"+this.getTitle()+"</title>\n</head>\n";
        
    }
    public String getBody() throws Exception{
        String html="<body>\n";
        html+=this.genereCSS();
        html+="<h1>"+this.getTitle()+"</h1>\n";
        for(PDFElement objet:this.getObj()){
          
            if(objet.getObj() instanceof Object[])html+=this.getTable(objet);
            else html+=this.getObject(objet);
        }
        html+="</body>\n";
        return html;
    }
    public String getHTML() throws Exception{
        return "<!DOCTYPE html><html>\n"+this.getHead()+this.getBody()+"</html>\n";
    }
    
    public String genereCSS(){
        String css="<style>\n";
        for(CSS cs:this.getCss())css+=cs.genereCSS();
        css+=this.getCssStyle();
        css+="</style>\n";
        return css;
    }
    public String getTable(PDFElement objet) throws Exception{
        String html="<h1>"+objet.getTitre()+"</h1>\n";
        html+="<table "+objet.getCSSClass()+">\n";
        Object[]obj=(Object[]) objet.getObj();
        Class classe=obj[0].getClass();
        Field[]fields=classe.getDeclaredFields();
        html+="<tr>\n";
        for(Field f:fields)html+="<th>"+f.getName()+"</th>\n";
         html+="</tr>\n";
        for(int i=0;i<obj.length;i++){
            html+="<tr>\n";
            for(int j=0;j<fields.length;j++){
                html+="<td>"+Reflect.get(obj[i], fields[j].getName()) +"</td>\n";
            }
             html+="</tr>\n";
        }
        
        html+="</table>\n";
        
        return html;
    }
    public String getObject(PDFElement objet) throws Exception{
        
        String html="<h1>"+objet.getTitre()+"</h1>\n";
       Object obj=objet.getObj();
        Class classe=obj.getClass();
         Field[]fields=classe.getDeclaredFields();
         html+="<div "+objet.getCSSClass()+">\n";
         for(Field f:fields){
             html+="<p>"+f.getName()+":"+Reflect.get(obj, f.getName())+"</p>\n";
         }
          html+="</div>\n";
        return html;
    }
    
}
