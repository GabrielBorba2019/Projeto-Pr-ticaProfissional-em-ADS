/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaosql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lanav
 */
public class dataSource {
    private String hostname;
    private int port;
    private String database;
    private String username;
    private String password;
    
    private Connection connection;
    
    public static Connection connect() throws ClassNotFoundException{
        Connection con= null;
        
        
        String url = "jdbc:sqlserver://maridoaluguel.cqd6mfkbvemf.us-east-1.rds.amazonaws.com:1433;databaseName=maridoAluguel;user=admin;password=mackenzie";
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);
            System.out.println("Connection is successful");
            
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            
        }
        
        return con;
        
        
        
    }
     
        
        
        
}
    