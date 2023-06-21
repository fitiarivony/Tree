/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.devis;

import com.bubble.tree.controller.BubbleController;
import modele.reel.Devis_vita;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="devis_vita")
public class Devis_vitaController extends BubbleController<Devis_vita,Integer>{
    
    public Devis_vitaController() {
        super(Devis_vita.class);
    }
    
}
