/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.help;

import com.bubble.tree.controller.BubbleController;
import modele.Niveau;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="niveau")
public class NiveauController extends BubbleController<Niveau,Integer>{
    
    public NiveauController() {
        super(Niveau.class);
    }
    
}
