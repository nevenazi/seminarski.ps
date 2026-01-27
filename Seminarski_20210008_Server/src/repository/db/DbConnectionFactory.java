/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import konfiguracija.Konfiguracija;

/**
 *
 * @author N
 */
public class DbConnectionFactory {
    private static DbConnectionFactory instance;
    private Connection connection;

    private DbConnectionFactory() {
        
        try {
            //provera da li je konekcija null ili zatvorena
                String url = Konfiguracija.getInstance().getProperty("url");
                String username = Konfiguracija.getInstance().getProperty("username");
                String password = Konfiguracija.getInstance().getProperty("password");
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
            
            
        } catch (SQLException sQLException) {
        }
    }
    
    public static DbConnectionFactory getInstance(){
        if (instance==null){
            instance= new DbConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
    
    
}
