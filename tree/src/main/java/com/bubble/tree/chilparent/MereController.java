/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubble.tree.chilparent;

import back.objects.Fille;
import back.objects.Mere;
import com.bubble.tree.controller.BubbleController;
import com.bubble.tree.crud.create.Create;
import com.bubble.tree.crud.fiche.Fiche;
import com.utils.TreeUtils;
import com.utils.controller.Failure;
import com.utils.controller.Response;
import com.utils.controller.Success;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pdfwrite.PDFElement;
import pdfwrite.PDFGenerator;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="mere")
public class MereController<T extends Mere,E extends Object> extends BubbleController<T,E>{

    public MereController(Class<T> classe) {
        super(classe);
    }
    @Override
    public Response get(@PathVariable E id,T model){
        Connect conn=null;
        try{
            conn=new Connect();
            conn.initSpring();
            model.setPrimary_key(id);
           T mod=(T)model.getById(conn).get(0);
            mod.initFilles();
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

    @Override
     public ResponseEntity<byte[]> getPdfElement(T model,@RequestBody T pdf,@RequestParam("titre")String titre,HttpServletResponse response){
            response.setContentType("application/pdf");
            System.out.println("Tonga ato");
            String title=titre;
            System.out.println(title);
          
        try{
              ArrayList<PDFElement>l=new ArrayList<>();
            l.add(new PDFElement(pdf,model.getClass().getSimpleName(),model.getClass().getSimpleName()));
            pdf.initFilles();
            l.add(new PDFElement(pdf.getFilles(),pdf.getClassChild().getSimpleName(),model.getClass().getSimpleName()));
            System.out.println(l.get(0).getTitre());
            PDFGenerator gen=new PDFGenerator(title,l);
            TreeUtils.setCssStyle(gen);
            response.setHeader("Content-Disposition", "attachment; filename=\"traj-"+titre+".pdf\"");
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
    
   
    
    
    
}
