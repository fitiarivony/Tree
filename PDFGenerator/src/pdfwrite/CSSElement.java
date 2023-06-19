/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdfwrite;

/**
 *
 * @author FITIA ARIVONY
 */
public class CSSElement {
    String key;
    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String genereCSSElement(){
        return this.getKey()+":"+this.getValue()+";\n";
    }

    public CSSElement(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
}
