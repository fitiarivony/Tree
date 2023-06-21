/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubble.tree.controller;

import back.objects.BddObject;
import back.objects.Loggable;
import com.utils.controller.Failure;
import com.utils.controller.Response;
import com.utils.controller.Success;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 * @param <T>
 * @param <E>
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="loggable")
public class LoggableController<T extends Loggable,E extends Object> extends BubbleController<T,E> {

    public LoggableController(Class<T> classe) {
        super(classe);
    }

 
    @PostMapping("login")
    public Response login(@RequestBody T adm){
        Connect conn=null;
        try {
            conn=new Connect();
            conn.setuses(true);
            conn.initSpring();
            adm.login(conn);
            return new Success("200",adm);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Failure("201",ex);
        }
        finally{
           try {
               conn.forceClose();
           } catch (SQLException ex) {
               ex.printStackTrace();
               return new Failure("300",ex);
           }
        }
    }
     @GetMapping("logout")
    public Response logout(HttpServletRequest req,T admin){
        Connect conn=null;
        try {
            System.out.println("atooooo");
            conn=new Connect();
            conn.setuses(true);
            conn.initSpring();
            
            String token = req.getHeader("Authorization");
            token=token.replace("Bearer ","");
            admin.setToken(token);
            admin.logout(conn);
            return new Success("200",admin);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Failure("201",ex);
        }
        finally{
           try {
               conn.forceClose();
           } catch (SQLException ex) {
               ex.printStackTrace();
               return new Failure("300",ex);
           }
        }
    }
}
