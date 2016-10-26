/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alanf
 */
public class ConnectionFactory {
     public static Connection getConexao() throws SQLException {    
         try {    
                 Class.forName("org.postgresql.Driver");    
                 return DriverManager.getConnection("jdbc:postgresql://localhost:5432/srs","postgres","postgres");
         }    
         catch (ClassNotFoundException e) {    
                 throw new SQLException(e.getMessage());    
         }    
    }   
    
}
