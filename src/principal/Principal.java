package principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import entidades.Concierto;
import entidades.Gira;
import entidades.Reportero;
import utils.ConexBD_Agencia;
import utils.Datos;

public class Principal {

	public static void main(String[] args) {
		
		//INCREIBLE! SI FUNCIONA! HE INSERTADO LOS DATOS EN LA BASE DE DATOS!!!! OH YESSSSS
		//insertarGira();
		//metodo para insertar los reporteros
		//insertarReporteros();
		insertarConciertos();

	}
	
	        //este metodo junto a los dos de abajo lo que hace es insertar los datos de los arrays en la base de datos
            public static void insertarGiras() {
			Connection conex = ConexBD_Agencia.establecerConexion();
			String consultaInsertStr1 = "insert into gira(id, nombre_gira, fecha_ini, fecha_fin) values (?,?,?,?)";
			try {
				PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
				for (Gira g : Datos.giras) {
					pstmt.setLong(1, g.getIdGira());
					pstmt.setString(2, g.getNombreGira());
					//muy importante usar el formmater antes de introducir cualquier fecha a la base de datos 
					java.sql.Date fechaSQL = java.sql.Date.valueOf(g.getFechaApertura());
					pstmt.setDate(3, fechaSQL);
					java.sql.Date fechaSQL2 = java.sql.Date.valueOf(g.getFechaCierre());
					pstmt.setDate(4, fechaSQL2);
					int resultadoInsercion = pstmt.executeUpdate();
					System.out.println("resultado de insercion"+resultadoInsercion);
				}
				
			} catch (SQLException e) {
				System.out.println("Se ha producido una SQLException:" + e.getMessage());
				e.printStackTrace();
			}

		}
            
            public static void insertarReporteros() {
    			Connection conex = ConexBD_Agencia.establecerConexion();
    			String consultaInsertStr1 = "insert into reportero(id,nombre_apellido,nif_nie,telefono) values (?,?,?,?)";
    			try {
    				PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
    				for (Reportero r : Datos.reporteros) {
    					//lo hice con un long aunqeu este declarado como un int en a base de datos, sin embargo funciona 
    					pstmt.setLong(1,r.getId());
    					pstmt.setString(2,r.getNombreyApellidos());
    					pstmt.setString(3,r.getNif());
    					pstmt.setString(4,r.gettelefono());
    					int resultadoInsercion = pstmt.executeUpdate();
    					System.out.println("resultado de insercion"+resultadoInsercion);
    				}
    				
    			} catch (SQLException e) {
    				System.out.println("Se ha producido una SQLException:" + e.getMessage());
    				e.printStackTrace();
    			}

    		}
            
            public static void insertarConciertos() {
    			Connection conex = ConexBD_Agencia.establecerConexion();
    			String consultaInsertStr1 = "insert into concierto(id,fecha_concierto,id_reportero,id_gira) values (?,?,?,?)";
    			try {
    				PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
    				for (Concierto c : Datos.conciertos) {
    					//lo hice con un long aunqeu este declarado como un int en a base de datos, sin embargo funciona 
    					pstmt.setLong(1,c.getIdConcierto());
    					//por que se llama distinto?
    					java.sql.Date fechaSQL = java.sql.Date.valueOf(c.getFechayHora());
    					pstmt.setDate(2,fechaSQL);
    					//he puesto las foreing keys como null para poder insertarlo sin errores
    					pstmt.setNull(3,Types.INTEGER);
    					pstmt.setNull(4,Types.INTEGER);
    					int resultadoInsercion = pstmt.executeUpdate();
    					System.out.println("resultado de insercion: "+resultadoInsercion);
    				}
    				
    			} catch (SQLException e) {
    				System.out.println("Se ha producido una SQLException:" + e.getMessage());
    				e.printStackTrace();
    			}

    		}        
}
            
