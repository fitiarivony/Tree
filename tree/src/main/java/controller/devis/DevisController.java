/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.devis;

import back.dao.Criteri;
import com.bubble.tree.crud.fiche.Fiche;
import com.bubble.tree.crud.list.ListTree;
import com.bubble.tree.crud.multisearch.MultiSearch;
import com.utils.ScMarker;
import com.utils.TreeUtils;
import com.utils.controller.Failure;
import com.utils.controller.Response;
import com.utils.controller.Success;
import excel.CSV;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.devis.Devis;
import modele.devis.ListePrestation;
import modele.devis.affiche.ListeAfficher;
import modele.reel.fromage.Fromage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pdfwrite.PDFElement;
import pdfwrite.PDFGenerator;
import utils.Connect;
import utils.Outils;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="devis")
public class DevisController {
    @GetMapping("getrequired")
    public Response getRequired(){
        try{
            return new Success("200",new ListePrestation());
        }catch(Exception e){
            e.printStackTrace();
            return new Failure("201",e);
        }
    }
     @GetMapping("/{id}")
    public Response getRequired(@PathVariable Integer id){
        try{
            Devis dev=new Devis();
            dev.setId(id);
            dev.getById(null);
            return new Success("200",dev);
        }catch(Exception e){
            e.printStackTrace();
            return new Failure("201",e);
        }
    }
    @PostMapping
    public Response createDevise(@RequestBody Devis dev){
        Connect conn=null;
       try{
           dev.save(conn);
            return new Success("200",dev);
       }catch(Exception e){
           e.printStackTrace();
            return new Failure("300",e); 
       }
       
    }
     @PutMapping("/{id}")
    public Response UpdateDevise(@RequestBody Devis dev,@PathVariable Integer id){
        Connect conn=null;
       try{
           dev.setId(id);
           dev.update(conn);
           
            return new Success("200",dev);
       }catch(Exception e){
           e.printStackTrace();
            return new Failure("300",e); 
       }
       
    }
     @PutMapping("/{id}/{etat}")
    public Response setVita(@PathVariable Integer etat,@PathVariable Integer id){
        Connect conn=null;
       try{
           conn=new Connect();
           conn.initSpring();
           Devis dev=new Devis();
           
           dev.setId(id);
           dev.setVita(etat);
           dev.initVita(conn);
            return new Success("200",dev);
       }catch(Exception e){
           e.printStackTrace();
            return new Failure("300",e); 
       }
       
    }
    
    @PostMapping("calculdevis")
    public Response CalculDevis(@RequestBody Devis dev){
        Connect conn=null;
       try{
           dev.calculDevis();
            return new Success("200",dev);
       }catch(Exception e){
           e.printStackTrace();
            return new Failure("300",e); 
       }
       
    }
    
     @GetMapping
     public Response selectAll(HttpServletRequest request){
         System.out.println("Tonga ato");
       Connect conn=null;
       ListTree l=new ListTree();
        try{
            Devis dev=new Devis();
            conn=new Connect();
            conn.initSpring();
            conn.setuses(true);
            this.initSpecial(dev,request,conn);
            if(request.getParameter("limit")!=null){
            Criteri crit=new Criteri();
            Integer limite=Integer.parseInt(request.getParameter("limit"));
            Integer offset=Integer.parseInt(request.getParameter("offset"));
            crit.setLimit(limite);
            crit.setOffset(offset*limite);
            Integer total=dev.countall(conn);
            
            dev.setCrit(crit);
             
            
             l=new ListTree(dev.selectAll(conn),dev,dev.getPrimary_key().getName());
             l.setNbpage(Outils.getNbPagination(limite,total));
               
             l.setMultisearch(new MultiSearch(dev,conn));
            return new Success("200",l);
            }else{
                
                if(dev.getPrimary_key()!=null)l=new ListTree(dev.selectAll(conn),dev,dev.getPrimary_key().getName());
                else l=new ListTree(dev.selectAll(conn),dev);
                l.setNbpage(0);
                 l.setMultisearch(new MultiSearch(dev,conn));
                 return new Success("200",l);
            }
           
        }catch(Exception e){
            e.printStackTrace();
            return new Failure("300",e);
        }finally{
           try {
               conn.forceClose();
           } catch (SQLException ex) {
               ex.printStackTrace();
               return new Failure("300",ex);
           }
        }
    }

   
     @GetMapping("/fiche/{id}")
    public Response get(@PathVariable Integer id){
         System.out.println("Atooooo");
        Connect conn=null;
        Devis model=new Devis();
        try{
            conn=new Connect();
            conn.initSpring();
            model.setPrimary_key(id);
           Devis mod=model.getDevisOnly();
             return new Success("200",new Fiche(mod));
        }catch(Exception e){
            e.printStackTrace();
            return new Failure("201",e);
        }finally{
            try {
                conn.forceClose();
            } catch (SQLException ex) {
                return new Failure("201",ex);
            }
        }
       
    }
      @GetMapping(value="/topdf/{id}", produces =MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdfAlea(HttpServletResponse response,@RequestParam String titre,@PathVariable Integer id){
            response.setContentType("application/pdf");
        try{
              Devis dev=new Devis();
              dev.setId(id);
           dev=dev.getDevisOnly();
            response.setHeader("Content-Disposition", "attachment; filename=\"traj-"+titre+".pdf\"");
            ScMarker marker=new ScMarker("liste.ftl");
            marker.put("devis", dev);
           marker.put("prestation", new ListeAfficher(id));
        byte[] pdfBytes = marker.toPdf("alea.pdf");
        
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }catch(Exception e){
    e.printStackTrace();
    
    }
        return null;
    }

    private void initSpecial(Devis dev, HttpServletRequest request, Connect conn) {
       
    }
    
     @PostMapping("search")
    public Response multisearch(@RequestBody HashMap<String,Object>map,HttpServletRequest request){
        Connect conn=null;
        try {
            Devis dev=new Devis();
            //System.out.println(map);
            conn=new Connect();
             conn.initSpring();
              this.initSpecial(dev,request,conn);
            if(request.getParameter("limit")!=null){
            conn.setuses(true);

            Criteri crit=new Criteri();
            Integer limite=Integer.parseInt(request.getParameter("limit"));
            Integer offset=Integer.parseInt(request.getParameter("offset"));
            crit.setLimit(limite);
            crit.setOffset(offset*limite);
            dev.setCrit(crit);
            
            MultiSearch<Devis> multi=new MultiSearch<>(map,dev,conn);
            multi.search();
                System.out.println(multi.getObj().getCrit()+"----");    
            Integer total=multi.getObj().countall(conn);
              multi.setNbpage(Outils.getNbPagination(limite,total));
            
             return new Success("200", multi);
            }
            MultiSearch<Devis> multi=new MultiSearch<>(map,dev,conn);
            multi.search();
            return new Success("200",multi);
        } catch (Exception ex) { 
            ex.printStackTrace();
            return new Failure("201",ex);
        }finally{
            try {
                conn.forceClose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                return new Failure("201",ex);
            }
            
        }
    }
   
     @PostMapping(value="pdf", produces =MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdf(@RequestBody ArrayList<Devis>pdf,@RequestParam("titre")String titre,HttpServletResponse response){
            response.setContentType("application/pdf");
            Devis dev=new Devis();
            System.out.println("Tonga ato");
            String title=titre;
            System.out.println(title);
            ArrayList<PDFElement>l=new ArrayList<>();
            l.add(new PDFElement(pdf.toArray(),dev.getClass().getSimpleName(),dev.getClass().getSimpleName()));
          //  System.out.println(l.get(0).getTitre());
            PDFGenerator gen=new PDFGenerator(title,l);
            TreeUtils.returnCSSTable(dev.getClass().getSimpleName(), gen);
            response.setHeader("Content-Disposition", "attachment; filename=\"traj-"+titre+".pdf\"");
        try{
            
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        gen.setOutput(byteArrayOutputStream);
        gen.generePDF();
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }catch(Exception e){
    e.printStackTrace();
    
    }
        return null;
    }
    
     @PostMapping("excel")
   public void downloadExcel(HttpServletResponse response,@RequestBody ArrayList<Devis>csv) throws IOException, Exception{
    OutputStream out = response.getOutputStream();
    Devis dev=new Devis();
        System.out.println("je suis la");
         CSV val=new CSV(csv.toArray(),";");
    Workbook work=val.getExcel(dev.getClass().getSimpleName());
 // Generate the file bytes
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        work.write(byteArrayOutputStream);
        byte[] fileBytes = byteArrayOutputStream.toByteArray();

        // Set content type and attachment header
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=users.csv");

        // Send the file bytes as response
        response.getOutputStream().write(fileBytes);

        
        //try cellStyle
         CellStyle cellStyle = work.createCellStyle();
         cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
         cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //  work.getSheetAt(0).getRow(i).getCell(header).setCellStyle(cellStyle);
         
         
        // Close resources
        work.close();
        byteArrayOutputStream.close();

  }
   
    @PostMapping("csv")
   public void downloadCSV(HttpServletResponse response,@RequestBody ArrayList<Devis>csv) throws IOException, Exception{
      response.setContentType("text/csv");
      Devis dev=new Devis();
  response.setHeader("Content-Disposition", "attachment; filename="+dev.getClass().getSimpleName()+".csv");
    OutputStream out = response.getOutputStream();
       // System.out.println("je suis la");
         CSV val=new CSV(csv.toArray(),";");
        //Download mycsv
     //   System.out.println(val.returnCSV());
    out.write(val.returnCSV().getBytes());
    out.flush();  
  }
   
    @DeleteMapping("{id}")
    public Response delete(@PathVariable("id") Integer id){
       
       Connect conn=null;
        try {
            Devis obj=new Devis();
            conn=new Connect();
            conn.initSpring();
            
            obj.setId(id);
           obj.delete(conn);
           return new Success("200",obj);
        }catch (Exception e){
            e.printStackTrace();
            return new Failure("201",e);
        }finally{
            try {
                conn.forceClose();
            } catch (SQLException ex) {
                return new Failure("201",ex);
            }
        }
        
    }
    
    @GetMapping("/fromage/{id}")
    public Response getFromage(@PathVariable Integer id){
        try {
            return new Success("200",new Fromage(id));
        } catch (Exception ex) {
           return new Failure("201",ex);
        }
    }
    

}
