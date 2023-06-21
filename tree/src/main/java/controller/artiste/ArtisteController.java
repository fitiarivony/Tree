/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.artiste;

import com.bubble.tree.controller.BubbleController;
import modele.artiste.Artiste;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="artiste")
public class ArtisteController extends BubbleController<Artiste,Integer>{
    
    public ArtisteController() {
        super(Artiste.class);
    }

}
