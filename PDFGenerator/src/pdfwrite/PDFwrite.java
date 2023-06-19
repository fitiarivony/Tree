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
import java.util.ArrayList;

/**
 *
 * @author FITIA ARIVONY
 */
public class PDFwrite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, DocumentException, IOException, Exception {
        // TODO code application logic here
          // Creating a PdfWriter       
      //Create a new PDF document
      
       Document document = new Document();

            
            //Add metadata to the document


//Create a PDF writer
           PdfWriter writer =  PdfWriter.getInstance(document, new FileOutputStream("MyPDF.pdf"));

//Open the document
            document.open();

       String html = "<html>\n" +
"<head>\n" +
" <title>Emploi du temps</title>\n" +
"</head>\n" +
"<body>\n" +
"<style>\n" +
"</style>\n" +
"<h1>Emploi du temps</h1>\n" +
"<div class=\"scene\">\n" +
"<p>nomscene:Aller au lycee</p>\n" +
"<p>nomplateau:Lycee Jefferson</p>\n" +
"<p>nomfilm:Jumanji</p>\n" +
"<p>ordre:4</p>\n" +
"<p>duree:00:13:00</p>\n" +
"<p>description:Un lycee au beverly hills</p>\n" +
"<p>tournage:2023-03-30 08:18:00.0</p>\n" +
"</div>\n" +
"<table  class=\"actions\">\n" +
               "<tr>\n" +
"<th>evenement</th>\n" +
"<th>ordre</th>\n" +
"<th>nomperso</th>\n" +
"<th>prenomperso</th>\n" +
"<th>duree</th>\n" +
               "</tr>\n" +
"<tr>\n" +
"<td>Mouse court a cause de son retard\"tout essoufle\"</td>\n" +
"<td>1</td>\n" +
"<td>Finbar</td>\n" +
"<td>Mouse</td>\n" +
"<td>00:10:00</td>\n" +
"</tr>\n" +
"<tr>\n" +
"<td>Ruby ne se soucie de rien\"\"</td>\n" +
"<td>2</td>\n" +
"<td>Runhouse</td>\n" +
"<td>Ruby</td>\n" +
"<td>00:03:00</td>\n" +
"</tr>\n" +
"</table>\n" +
"</body>\n" +
"</html>";
XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(html.getBytes()));

            document.close();
           
            Person p=new Person();
        Object[]obj=new Object[0];
        ArrayList<PDFElement>l=new ArrayList<>();
        l.add(new PDFElement(obj,"table"));
        l.add(new PDFElement(new Person("Rabe","vava"),"person"));
        CSS css=new CSS("person");
        css.addCSSElement(new CSSElement("text-align","center"));
        
        CSS csstable=new CSS("table");
        csstable.addCSSElement(new CSSElement("color","aqua"));
        PDFGenerator pdf=new PDFGenerator("Info",l);
        pdf.addCSS(css);
        pdf.addCSS(csstable);
        pdf.setOutput(new FileOutputStream("MyPDF.pdf"));
        pdf.generePDF();
          

    }
    
}
