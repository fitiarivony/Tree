
package com.bubble.tree.controller;

import back.dao.Criteri;
import back.objects.BddObject;



import com.bubble.tree.crud.create.Create;
import com.bubble.tree.crud.fiche.Fiche;
import com.bubble.tree.crud.list.ListTree;

import com.bubble.tree.crud.multisearch.MultiSearch;
import com.bubble.tree.crud.update.Update;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import excel.CSV;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestParam;
import pdfwrite.PDFElement;
import pdfwrite.PDFGenerator;
import utils.Connect;
import com.utils.MailHandling;
import com.utils.ScMarker;
import utils.Outils;
import com.utils.TreeUtils;
import com.utils.controller.Failure;
import com.utils.controller.Response;
import com.utils.controller.Success;
import java.lang.reflect.Array;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;


/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="bubble")
public class BubbleController<T extends BddObject,E extends Object> {
    @Autowired
    JavaMailSender sender;
    Class<T>classe;

    public Class<T> getClasse() {
        return classe;
    }

    public void setClasse(Class<T> classe) {
        this.classe = classe;
    }

   

    
    public BubbleController(Class<T> classe){
        this.setClasse(classe);
    }
    
    public JavaMailSender getSender() {
        return sender;
    }

    public void setSender(JavaMailSender sender) {
        this.sender = sender;
    }

    
    @PostMapping
    public Response insert(@RequestBody T objet,HttpServletRequest request){
       Connect conn=null;
        try{
             conn=new Connect();
             conn.initSpring();
              this.initSpecial(objet,request,conn);
            objet.save(conn);
            // return new Failure("300",new Exception("erreur"));
           return new Success("200",objet);
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
    
    @PostMapping("search")
    public Response multisearch(T obj,@RequestBody HashMap<String,Object>map,HttpServletRequest request){
        Connect conn=null;
        try {
            //System.out.println(map);
            conn=new Connect();
             conn.initSpring();
              this.initSpecial(obj,request,conn);
            if(request.getParameter("limit")!=null){
            conn.setuses(true);

            Criteri crit=new Criteri();
            Integer limite=Integer.parseInt(request.getParameter("limit"));
            Integer offset=Integer.parseInt(request.getParameter("offset"));
            crit.setLimit(limite);
            crit.setOffset(offset*limite);
            obj.setCrit(crit);
            
            MultiSearch<T> multi=new MultiSearch<>(map,obj,conn);
            multi.search();
                System.out.println(multi.getObj().getCrit()+"----");    
            Integer total=multi.getObj().countall(conn);
              multi.setNbpage(Outils.getNbPagination(limite,total));
            
             return new Success("200", multi);
            }
            MultiSearch<T> multi=new MultiSearch<>(map,obj,conn);
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
   
    @GetMapping
     public Response selectAll(T obj,HttpServletRequest request){
       Connect conn=null;
       ListTree l=new ListTree();
        try{
            
            conn=new Connect();
            conn.initSpring();
            conn.setuses(true);
            this.initSpecial(obj,request,conn);
            if(request.getParameter("limit")!=null){
            Criteri crit=new Criteri();
            Integer limite=Integer.parseInt(request.getParameter("limit"));
            Integer offset=Integer.parseInt(request.getParameter("offset"));
            crit.setLimit(limite);
            crit.setOffset(offset*limite);
            Integer total=obj.countall(conn);
            
            obj.setCrit(crit);
             
            
             l=new ListTree(obj.selectAll(conn),obj,obj.getPrimary_key().getName());
             l.setNbpage(Outils.getNbPagination(limite,total));
               
             l.setMultisearch(new MultiSearch(obj,conn));
            return new Success("200",l);
            }else{
                
                if(obj.getPrimary_key()!=null)l=new ListTree(obj.selectAll(conn),obj,obj.getPrimary_key().getName());
                else l=new ListTree(obj.selectAll(conn),obj);
                l.setNbpage(0);
                 l.setMultisearch(new MultiSearch(obj,conn));
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
    
    @PutMapping("{id}")
    public Response update(@PathVariable("id") E id,@RequestBody T obj,HttpServletRequest request){
        Connect conn=null;
        try{
            obj.setPrimary_key(id);
            System.out.println(obj);
            conn=new Connect();
            conn.setuses(true);
             conn.initSpring();
             this.initSpecial(obj,request,conn);
            
            obj.update(conn);
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
        return new Success("200",obj);
    }
    
    
     @PutMapping
    public Response updating(@RequestBody T obj,HttpServletRequest request){
        Connect conn=null;
        try{
            System.out.println(obj);
            conn=new Connect();
            conn.setuses(true);
             conn.initSpring();
             this.initSpecial(obj,request,conn);
            
            obj.update(conn);
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
        return new Success("200",obj);
    }
    
    
    @DeleteMapping("{id}")
    public Response delete(@PathVariable("id") E id,T obj){
       
       Connect conn=null;
        try {
            conn=new Connect();
            conn.initSpring();
            
           obj.setPrimary_key(id);
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
   
    @GetMapping("update/{id}")
    public Response chargeUpdate(@PathVariable("id") E id,T obj,HttpServletRequest request){
        Connect conn=null;
        try{
            conn=new Connect();
            conn.initSpring();
            conn.setuses(true);
            this.initSpecial(obj,request,conn);
            obj.setPrimary_key(id);
            ArrayList<T>liste=obj.getById(conn);
            if(liste.size()==0) return new Failure("201",new Exception("No Element with this id"));
            obj=liste.get(0);
            System.out.println("Vita init");
           
         Update<T> update=new Update(obj,conn);
            System.out.println(update);
             return new Success("200",update);
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
    
     @GetMapping("create")
    public Response chargeInsert(T obj,HttpServletRequest request){
        Connect conn=null;
        try{
            conn=new Connect();
            conn.initSpring();
            conn.setuses(true);
           this.initSpecial(obj,request,conn);
            System.out.println("Vita init");
           
         Create<T> creer=new Create(obj,conn);
            System.out.println(creer);
             return new Success("200",creer);
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
    @PostMapping(value="pdf", produces =MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdf(T model,@RequestBody ArrayList<T>pdf,@RequestParam("titre")String titre,HttpServletResponse response){
            response.setContentType("application/pdf");
            System.out.println("Tonga ato");
            String title=titre;
            System.out.println(title);
            ArrayList<PDFElement>l=new ArrayList<>();
            l.add(new PDFElement(pdf.toArray(),model.getClass().getSimpleName(),model.getClass().getSimpleName()));
          //  System.out.println(l.get(0).getTitre());
            PDFGenerator gen=new PDFGenerator(title,l);
            TreeUtils.returnCSSTable(model.getClass().getSimpleName(), gen);
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
    
     @PostMapping(value="topdf", produces =MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdfElement(T model,@RequestBody T pdf,@RequestParam("titre")String titre,HttpServletResponse response){
            response.setContentType("application/pdf");
            System.out.println("Tonga ato");
            String title=titre;
            System.out.println(title);
            ArrayList<PDFElement>l=new ArrayList<>();
            l.add(new PDFElement(pdf,model.getClass().getSimpleName(),model.getClass().getSimpleName()));
            System.out.println(l.get(0).getTitre());
            PDFGenerator gen=new PDFGenerator(title,l);
            TreeUtils.setCssStyle(gen);
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
    
    @GetMapping("/{id}")
    public Response get(@PathVariable E id,T model){
        Connect conn=null;
        try{
            conn=new Connect();
            conn.initSpring();
            model.setPrimary_key(id);
             return new Success("200",new Fiche((T)model.getById(conn).get(0)));
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
    
    
    
    
    @PostMapping("excel")
   public void downloadExcel(HttpServletResponse response,@RequestBody ArrayList<T>csv,T model) throws IOException, Exception{
    OutputStream out = response.getOutputStream();
        System.out.println("je suis la");
         CSV val=new CSV(csv.toArray(),";");
    Workbook work=val.getExcel(model.getClass().getSimpleName());
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
   public void downloadCSV(HttpServletResponse response,@RequestBody ArrayList<T>csv,T model) throws IOException, Exception{
      response.setContentType("text/csv");
  response.setHeader("Content-Disposition", "attachment; filename="+model.getClass().getSimpleName()+".csv");
    OutputStream out = response.getOutputStream();
       // System.out.println("je suis la");
         CSV val=new CSV(csv.toArray(),";");
        //Download mycsv
     //   System.out.println(val.returnCSV());
    out.write(val.returnCSV().getBytes());
    out.flush();  
  }

   
//   @GetMapping("chargemulti")
//   public Response charge_multisearch(T model){
//       Connect conn=null;
//       try{
//            conn=new Connect();
//            conn.initSpring();
//           MultiSearch<T>multi=new MultiSearch(model,conn);
//       return new Success("200",multi);  
//       }catch(Exception e){
//           e.printStackTrace();
//           return new Failure("400",e);
//       }
//     
//   }
     @GetMapping("/test/mail")
    public Response testMail() {
        String mail = "fitiarivony2002@gmail.com";
        String subject = "Test de fonctionnalité";
        String corps = "Mande lesy io ah";
        try {
            System.out.println(this.getSender()+"-----");
            MailHandling hMail = new MailHandling(this.getSender(), mail, subject, corps, null);
            hMail.send();
            return new Success("200","ok");
        } catch (Exception e) {
            e.printStackTrace();
            return new Failure("201",e);
        }

    }
    public void initSpecial(T obj,HttpServletRequest req,Connect conn)throws Exception{
        
    }
    
     
    
      @PostMapping(value="alea", produces =MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdfAlea(@RequestBody ArrayList<T>pdf,@RequestParam("titre")String titre,HttpServletResponse response){
            response.setContentType("application/pdf");
            System.out.println("Tonga ato");
            String title=titre;
            ScMarker marker=new ScMarker("liste.ftl");
            marker.put("titre", title);
             ArrayList<String> names = new ArrayList<>();
            names.add("Alice");
            names.add("Bob");
            names.add("Charlie");
            marker.put("names", names);
            boolean isLoggedIn = false;
            marker.put("isLoggedIn", isLoggedIn);
            response.setHeader("Content-Disposition", "attachment; filename=\"traj-"+titre+".pdf\"");
        try{
        byte[] pdfBytes = marker.toPdf("alea.pdf");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }catch(Exception e){
    e.printStackTrace();
    
    }
        return null;
    }
    
    T[] toT(ArrayList<T> data){
        T[] retour=(T[]) Array.newInstance(this.getClasse(), data.size());
        for(int i=0;i<data.size();i++){
            retour[i]=data.get(i);
        }
        return retour;
    }
     @PostMapping("multiinsert")
    public Response insertArray(@RequestBody ArrayList<T> data,T objet,HttpServletRequest request){
       Connect conn=null;
        try{
             conn=new Connect();
             conn.initSpring();
             System.out.println(data);
              this.initSpecial(objet,request,conn);
            objet.insertArray(toT(data),conn);
            
            // return new Failure("300",new Exception("erreur"));
           return new Success("200",objet);
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
    
}
