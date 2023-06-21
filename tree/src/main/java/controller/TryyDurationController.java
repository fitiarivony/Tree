/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.bubble.tree.controller.BubbleController;
import com.utils.controller.Response;
import javax.servlet.http.HttpServletRequest;
import modele.TryyDuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FITIA ARIVONY
 */
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(path="tryyduration")
public class TryyDurationController extends BubbleController<TryyDuration,Integer>{
    
    public TryyDurationController() {
        super(TryyDuration.class);
    }

    @Override
    public Response insert(@RequestBody TryyDuration objet, HttpServletRequest request) {
        System.out.println(objet);
        return super.insert(objet, request); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
