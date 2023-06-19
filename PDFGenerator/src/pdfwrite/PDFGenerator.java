/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfwrite;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Reflect;

/**
 *
 * @author FITIA ARIVONY
 */
public class PDFGenerator extends Document{
    ArrayList<PDFElement>obj;
    ArrayList<CSS>css;
    String title;
    OutputStream output;
    String html;
    String cssStyle;

    public String getCssStyle() {
        return cssStyle;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }
    

    public OutputStream getOutput() throws FileNotFoundException {
        if(output==null){
           this.setOutput(new FileOutputStream(this.getTitlePDF()));
        }
        return output;
    }

    public void setOutput(OutputStream output) {
        this.output = output;
    }

    
    public ArrayList<CSS> getCss() {
         if(css==null)this.setCss(new ArrayList<CSS>());
        return css;
    }

    public void setCss(ArrayList<CSS> css) {
        this.css = css;
    }
     public void addCSS(CSS element){
        if(this.getCss()==null)this.setCss(new ArrayList<CSS>());
        this.getCss().add(element);
    }

    public ArrayList<PDFElement> getObj() {
        return obj;
    }
    public void addElement(PDFElement element){
        if(this.getObj()==null)this.setObj(new ArrayList<PDFElement>());
        this.getObj().add(element);
    }

    public void setObj(ArrayList<PDFElement> obj) {
        this.obj = obj;
    }
    
  

    public String getHtml() {
        if(html==null){
            try {
                HTML html=new HTML(this.getObj(),this.getTitle(),this.getCss());
                if(this.getCssStyle()!=null)html.setCssStyle(this.getCssStyle());
                this.setHtml(html.getHTML());
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        }
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public PDFGenerator(String title,ArrayList<PDFElement> obj) {
        super();
        this.obj = obj;
        this.title = title;
    }
    

    public String getTitle() {
        return title;
    }
    public String getTitlePDF(){
        return this.getTitle()+".pdf";
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void generePDF() throws FileNotFoundException, DocumentException, IOException, Exception {
        try {
          
            PdfWriter writer =  PdfWriter.getInstance(this,this.getOutput());
           this.open();
            System.out.println(this.getHtml());
            XMLWorkerHelper.getInstance().parseXHtml(writer, this, new ByteArrayInputStream(this.getHtml().getBytes()));
        } finally{
            this.close();
        }
    }
   
  
    
    
    
}
