/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJavaDb {

   
	private Connection connection;
	
	public Connection getConnection() throws ConexaoException {
		if (connection == null) {
			try {
				String url;
				url = "jdbc:sqlserver://maridoaluguel.cqd6mfkbvemf.us-east-1.rds.amazonaws.com:1433;databaseName=maridoAluguel;user=admin;password=mackenzie";
				Class.forName("org.apache.derby.jdbc.ClientDriver");
				connection = DriverManager.getConnection(url);
			} catch (ClassNotFoundException ex) {
				throw new ConexaoException("Falha ao carregar driver!");
			} catch (SQLException ex) {
				throw new ConexaoException("Falha ao abrir conexão!");
			}
 		}
		return connection;
	}
	public void close() throws ConexaoException {
		if (connection != null) {
			try { 
				connection.close(); 
			} catch (SQLException ex) { 
				throw new ConexaoException("Problema ao fechar conexão!");
			}
		}
	}
    
}
