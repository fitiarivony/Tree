/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.autre;

import com.bubble.tree.controller.VueController;
import modele.autre.V_autre;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="v_autre")
public class V_autreController extends VueController<V_autre,Integer>{
    
    public V_autreController() {
        super(V_autre.class);
    }
    
}
