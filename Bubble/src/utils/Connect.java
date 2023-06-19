/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import exception.ConnexionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp.BasicDataSource;



/**
 *
 * @author FITIA ARIVONY
 */
public final class Connect implements Serializable {
    Connection connect;
    boolean ouvert;
    boolean willbeused;
    int numberusing;
    String url;
    String username;
    String password;
    
    BasicDataSource datasource;

    public BasicDataSource getDatasource() {
        return datasource;
    }

    public void setDatasource(BasicDataSource datasource) {
        this.datasource = datasource;
    }
     public void initConnection() throws SQLException, Exception{
       // System.out.println(this.getDatasource());
        this.setConnect(this.getDatasource().getConnection());
        this.setUrl(this.getDatasource().getUrl());
        this.setPassword(this.getDatasource().getPassword());
        this.setUsername(this.getDatasource().getUsername());
       
    }
    

    public boolean isWillbeused() {
        return willbeused;
    }

    public void setWillbeused(boolean willbeused) {
        this.willbeused = willbeused;
    }

    public String getUrl() {
        if(url==null)this.setUrl("jdbc:postgresql://localhost:8089/extraction");
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        if(username==null)this.setUsername("societe");
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
          if(password==null)this.setPassword("mdp");
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getNumberusing() {
        return numberusing;
    }

    public void setNumberusing(int numberusing) {
        this.numberusing = numberusing;
    }
    

    public boolean Willbeused() {
        return willbeused;
    }

    public void setuses(boolean willbeused) {
        this.willbeused = willbeused;
    }

    public Connection getConnect() {
        return connect;
    }

    public void setConnect(Connection connect) throws SQLException {
        this.connect = connect;
        this.setAutoCommit(false);
    }

    public boolean isOuvert() {
        return ouvert;
    }

    public void setOuvert(boolean ouvert) {
        this.ouvert = ouvert;
    }
    public void close() throws SQLException{
        this.setOuvert(false);
      //  System.out.println(this.Willbeused());
        if(this.getConnect()!=null  && !this.Willbeused())this.getConnect().close();
    }
    public void commit() throws SQLException{
        if(this.getConnect()!=null){
            this.getConnect().commit();
            this.close();
        }
        
    }
     public void rollback() throws SQLException{
        if(this.getConnect()!=null){
            this.getConnect().rollback();
            this.close();
        }
    }
    public void setAutoCommit(boolean autocommit) throws SQLException{
        this.getConnect().setAutoCommit(autocommit);
    }
    public boolean getAutoCommit() throws SQLException{
        return this.getConnect().getAutoCommit();
    }
    public void iniConf(){
        
    }
    public Connect() throws ClassNotFoundException, SQLException, Exception{
        this.setOuvert(false);
        this.setuses(false);
    }
    public Connect(ConnectionConfiguration conf) throws SQLException, ClassNotFoundException{
       
            
            this.setUrl(conf.getUrl());
            this.setPassword(conf.getMdp());
            this.setUsername(conf.getUsername());
            this.getConnection();
            
   
    }

    public Connect(String url, String username, String password) throws ClassNotFoundException, SQLException {
        this.setUrl(url);
        this.setUsername(username);
        this.setPassword(password);
        this.getConnection();
        
    }
    
   public boolean isNull() throws SQLException{
       
       if(this.getConnect()==null || this.isClosed())return true;
       return false;
   }
   public boolean isClosed() throws SQLException{
       return this.getConnect().isClosed();
   }
   public PreparedStatement prepareStatement(String sql) throws Exception{
       this.getConnection();
       return this.getConnect().prepareStatement(sql);
   }
     public  void getConnection()throws ClassNotFoundException,SQLException{
       
         if(this.isNull() || this.isClosed()){
             
             Class.forName("org.postgresql.Driver");
             System.out.println(this.getUrl()+"----------"+this.getPassword()+"------"+this.getUsername());
              this.setConnect(DriverManager.getConnection(this.getUrl(),this.getUsername(),this.getPassword()));
              this.setAutoCommit(true);
         }
    }
    public void forceClose() throws SQLException{
        this.setuses(false);
        this.close();
    }
    @Override
    public String toString() {
        return "Connect{" + "connect=" + connect + ", ouvert=" + ouvert + '}';
    }
    
    //spring dbproperties
    public void initSpring() throws Exception{
       
       this.setConnect(Connect.SpringConnection());
       this.setAutoCommit(true);
        
    }
    
    static Properties props;
	public static Connection SpringConnection() throws Exception{
            Class.forName(getProps().getProperty("classname"));
            
            //String url="jdbc:postgresql://localhost:5432/rencontre?user=postgres&password=root&ssl=true&sslmode=disable";
            return DriverManager.getConnection(getProps().getProperty("url"), getProps());
	}

    public static Properties getProps() throws IOException{
       //System.out.println("tonga ato");
        if(props==null){
            try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties")) {
                props=new Properties();
                props.load(in);
                System.out.println(props);
            }
        }
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }
     
    
}
