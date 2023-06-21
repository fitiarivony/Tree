/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.artiste;

import com.bubble.tree.controller.VueController;
import modele.artiste.V_artiste;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="v_artiste")
public class V_artisteController extends VueController<V_artiste,Integer>{
    
    public V_artisteController() {
        super(V_artiste.class);
    }
    
}
