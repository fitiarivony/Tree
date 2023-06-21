/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.reel;

import com.bubble.tree.controller.BubbleController;
import modele.reel.Billet;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="billet")
public class BilletController extends BubbleController<Billet,Integer>{
    
    public BilletController() {
        super(Billet.class);
    }
    
}
