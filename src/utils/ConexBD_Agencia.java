package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ConexBD_Agencia {
	static Connection conexion = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	

	//este sera el metodo encargado de establecer la conexion con la base de datos
	@SuppressWarnings("finally")
	public static Connection establecerConexion() {
		try {
			System.out.println("Conectando a la Base de Datos...");
			Class.forName("com.mysql.cj.jdbc.Driver");
			//aqui decidi pasar directamente como argumento las cadenas de texto y no crear una variable extra que la almacena
			//IMPORTANTE si poner en phpmyadmin lo siguiente: "SHOW VARIABLES WHERE VARIABLE_NAME IN ('hostname','port');" te dice tu puerto y host del servidor de la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agencia_conciertos", "root","");
		} catch (SQLException ex) {
			System.out.println("Se ha producido una SQLException:" + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println("Se ha producido una ClassNotFoundException:" + ex.getMessage());
		} finally {
			return conexion;
		}
	}

	//este es un metodo que nos permite realizar la conexion de una manera distinta
	public static Connection getCon() {
		try {
			if (conexion == null) {
				Properties properties = new Properties();
				MysqlDataSource m = new MysqlDataSource();
				FileInputStream fis;
				//no se si esta es la direccion correcta del fichero desde mi pc ya que es una ruta absoluta
				fis = new FileInputStream("src/resources/db.properties");
				// cargamos la información del fichero properties
				properties.load(fis);
				// asignamos al origen de datos las propiedades leidas del fichero properties
				m.setUrl(properties.getProperty("url"));
				m.setPassword(properties.getProperty("password"));
				m.setUser(properties.getProperty("usuario")); // obtememos la conexion
				fis.close();
				conexion = m.getConnection();
			}
			return conexion;
		} catch (FileNotFoundException e) {
			System.out.println("Error al acceder al fichero properties " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer las propiedades del fichero properties" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception: " + e.getMessage());
			e.printStackTrace();
		}
		return conexion;
	}
	
	//este es el metodo encargado de cerrar la conexion
	public static void cerrarConexion() {
		try {
			if (conexion != null && !conexion.isClosed()) {
				conexion.close();
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
