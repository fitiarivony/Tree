/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.logistique;

import com.bubble.tree.controller.BubbleController;
import modele.logistique.Logistique;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="logistique")
public class LogistiqueController extends BubbleController<Logistique,Integer> {
   
    public LogistiqueController() {
        super(Logistique.class);
    }
   
}
