/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.controller;

/**
 *
 * @author FITIA ARIVONY
 */
public class Failure extends Response{
    Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

   
    

    public Failure(String code,Exception e) {
        this.setError(new Error(code,e));
    }
    
   
    
}
