/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.bubble.tree.controller.LoggableController;
import modele.Administrateur;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="admin")
public class AdministrateurController extends LoggableController<Administrateur,Integer>{
    
    public AdministrateurController() {
        super(Administrateur.class);
    }
    
}
