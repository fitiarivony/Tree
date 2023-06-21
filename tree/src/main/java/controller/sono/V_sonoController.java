/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sono;

import com.bubble.tree.controller.BubbleController;
import modele.sono.Sono;
import modele.sono.V_sono;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="v_sono")
public class V_sonoController extends BubbleController<V_sono,Integer>{
    
    public V_sonoController() {
        super(V_sono.class);
    }
    
    
}
