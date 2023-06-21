/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.autre;

import com.bubble.tree.controller.BubbleController;
import modele.autre.Autre;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="autre")
public class AutreController extends BubbleController<Autre,Integer>{
    
    public AutreController() {
        super(Autre.class);
    }
    
}
