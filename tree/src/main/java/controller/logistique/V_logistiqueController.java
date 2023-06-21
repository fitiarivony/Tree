/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.logistique;

import com.bubble.tree.controller.VueController;
import modele.logistique.V_logistique;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="v_logistique")
public class V_logistiqueController extends VueController<V_logistique,Integer>{
    
    public V_logistiqueController() {
        super(V_logistique.class);
    }
    
}
