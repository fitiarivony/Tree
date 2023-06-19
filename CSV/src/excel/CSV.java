/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel;

import exception.ExcelException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Date;
import utils.ReflectCSV;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author FITIA ARIVONY
 */
public class CSV {
    Object[] obj;
    String[]headers;
    OutputStream out;
    String separator;

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }
    
    

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public OutputStream getOut() {
        return out;
    }

    public void setOut(OutputStream out) {
        this.out = out;
    }

    
    public Object[] getObj() {
        return obj;
    }

    public void setObj(Object[] obj) {
        this.obj = obj;
    }

    public CSV(Object[] obj,String separator) {
        this.obj = obj;
        this.setSeparator(separator);
        this.initCSV();
    }
    public void initCSV(){
        this.initHeaders();
    }
    
    public int getHeader(String header) throws ExcelException{
        for(int i=0;i<this.getHeaders().length;i++){
            if(this.getHeader(i).equals(header))return i;
        }
        throw new ExcelException("There's no header such like that!!!!");
    }
    public String getHeader(int header){
        return this.getHeaders()[header];
    }
    
    public void initHeaders(){
        System.out.println("Init header");
        Field[]fields=this.getObj()[0].getClass().getDeclaredFields();
        System.out.println("header length:"+fields.length);
        String[]headers=new String[this.getObj()[0].getClass().getDeclaredFields().length];
        for(int i=0;i<headers.length;i++){
           
            headers[i]=fields[i].getName();
        }
        this.setHeaders(headers);
    }
    public String returnCSV() throws Exception{
        String val="";
        for(String head:this.getHeaders())val+=head+";";
        val=val.substring(0,val.length()-1);
        val+="\n";
        for(int i=0;i<this.getObj().length;i++){
            val+=this.getCSV(this.getObj()[i]);
            if(i!=this.getObj().length-1)val+="\n";
        }
        System.out.println(val);
        return val;
    }
    public String getCSV(Object obj)throws Exception{
        String val="";
        for(int i=0;i<this.getHeaders().length;i++){
            val+=ReflectCSV.get(obj,this.getHeader(i))+this.getSeparator();
           
        }
        val=val.substring(0, val.length()-1);
        
        return val;
    }

    @Override
    public String toString() {
        return "CSV{" + "obj=" + obj + ", headers=" + headers + ", out=" + out + ", separator=" + separator + '}';
    }
    public Workbook getExcel(String title) throws Exception{
        //Create a workbook with sheet
      Workbook  work=new HSSFWorkbook();
      Sheet sheet=work.createSheet(title);
      
      //Create header row
      Row headerRow=sheet.createRow(0);
      for(int i=0;i<this.getHeaders().length;i++)headerRow.createCell(i).setCellValue(this.getHeaders()[i]);
      //Populate data rows
      int rowNum=1;
      for(Object o:this.getObj()){
           Row row=sheet.createRow(rowNum++);
          for(int i=0;i<this.getHeaders().length;i++){
              Cell cell=row.createCell(i);
              this.setCellValue(cell, ReflectCSV.get(o,this.getHeader(i)));
          }
         
          
      }
      return work;
    }
    private void setCellValue(Cell cell, Object value) {
        if (value == null) {
            cell.setCellValue("");
        } else if (value instanceof String) {
            cell.setCellValue((String)value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if(value instanceof Date){
            cell.setCellValue((Date)value);
        }
        else {
            cell.setCellValue(value.toString());
        }
    }

 
    
}
