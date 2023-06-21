/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import back.objects.BddObject;
import java.lang.reflect.Field;
import pdfwrite.CSS;
import pdfwrite.CSSElement;
import pdfwrite.PDFGenerator;

/**
 *
 * @author FITIA ARIVONY
 */
public class TreeUtils {
  public static Field getField(String attribut,BddObject obj){
      Class cl=obj.getClass();
      return null;
  }
 public static void  returnCSSTable(String classe,PDFGenerator pdf){
      CSS css1=new CSS(classe);
    css1.addCSSElement(new CSSElement("font-family","Arial, Helvetica, sans-serif"));
    css1.addCSSElement(new CSSElement("border-collapse","collapse"));
    css1.addCSSElement(new CSSElement("width","100%"));
    
    CSS css2=new CSS(classe+" td");
    css2.addCSSElement(new CSSElement("border","1px solid #ddd"));
    css2.addCSSElement(new CSSElement("padding","8px"));
    
    CSS css3=new CSS(classe+" th");
    css3.addCSSElement(new CSSElement("padding-top","12px"));
    css3.addCSSElement(new CSSElement("padding-bottom","12px"));
    css3.addCSSElement(new CSSElement("text-align","left"));
    css3.addCSSElement(new CSSElement("background-color","#04AA6D"));
    css3.addCSSElement(new CSSElement("color","white"));
    pdf.addCSS(css1);
      pdf.addCSS(css2);
       pdf.addCSS(css3);
 }
 public static void setCssStyle(PDFGenerator pdf){
  String val= "<style>\n" +
"  body {\n" +
"    font-family: Arial, sans-serif;\n" +
"    margin: 0;\n" +
"    padding: 0;\n" +
"  }\n" +
"\n" +
"  h1 {\n" +
"    color: #333;\n" +
"    font-size: 24px;\n" +
"    font-weight: bold;\n" +
"    margin-bottom: 10px;\n" +
"  }\n" +
"\n" +
"  div {\n" +
"    background-color: #f2f2f2;\n" +
"    padding: 10px;\n" +
"    margin-bottom: 20px;\n" +
"  }\n" +
"\n" +
"  p {\n" +
"    margin: 5px 0;\n" +
"  }\n" +
"\n" +
"  table {\n" +
"    border-collapse: collapse;\n" +
"    width: 100%;\n" +
"  }\n" +
"\n" +
"  th, td {\n" +
"    border: 1px solid #ccc;\n" +
"    padding: 8px;\n" +
"    text-align: left;\n" +
"  }\n" +
"\n" +
"  th {\n" +
"    background-color: #f2f2f2;\n" +
"  }\n" +
"</style>" ; 
    pdf.setCssStyle(val);
 }
}
