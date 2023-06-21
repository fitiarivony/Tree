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
public class Error {
    String code;
    Exception e;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }
     public Error(String code, Exception e) {
        this.code = code;
        this.e = e;
    }
}
