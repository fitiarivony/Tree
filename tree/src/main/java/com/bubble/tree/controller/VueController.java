/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bubble.tree.controller;

import back.objects.Vue;
import com.utils.controller.Failure;
import com.utils.controller.Response;
import exception.DAOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="vue")
public class VueController<T extends Vue,E extends Object> extends BubbleController<T,E>{

    public VueController(Class<T> classe) {
        super(classe);
    }
    
    @Override
    public Response chargeInsert(T obj, HttpServletRequest request) {
        return new Failure("405",new DAOException("This is a view")); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response chargeUpdate(E id, T obj, HttpServletRequest request) {
       return new Failure("405",new DAOException("This is a view"));//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response delete(E id, T obj) {
       return new Failure("405",new DAOException("This is a view"));//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response updating(T obj, HttpServletRequest request) {
        return new Failure("405",new DAOException("This is a view"));//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response update(E id, T obj, HttpServletRequest request) {
       return new Failure("405",new DAOException("This is a view")); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Response insert(T objet, HttpServletRequest request) {
       return new Failure("405",new DAOException("This is a view"));//To change body of generated methods, choose Tools | Templates.
    }
    
}
