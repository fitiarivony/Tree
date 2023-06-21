/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.controller;

import com.utils.controller.Response;

/**
 *
 * @author FITIA ARIVONY
 */
public class Success extends Response{
    String code;
    Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Success(String code, Object data) {
        this.code = code;
        this.data = data;
    }
    
}
