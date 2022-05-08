package principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

import dao.ConciertoDAO;
import dao.GiraDAO;
import dao.ReporteroDAO;
import entidades.Concierto;
import entidades.Gira;
import entidades.Reportero;
import utils.ConexBD_Agencia;
import utils.Datos;

public class Principal {

	public static void main(String[] args) {
		
		//IMPORTANTE: dejo comentados los metodos usados para introducir los arrays estaticos usados en las anteriores entregas
		
		//INCREIBLE! SI FUNCIONA! HE INSERTADO LOS DATOS EN LA BASE DE DATOS!!!! OH YESSSSS
		//insertarGira();
		
		//metodo para insertar los reporteros
		//insertarReporteros();
		
		//metodo para insertar conciertos
		//insertarConciertos();
		
		//prueba de creacion de una entidad con singeton
		Connection c=ConexBD_Agencia.establecerConexion();
		//creo dos instancias de la clase pasando como argumento la conexion 
		ReporteroDAO R=ReporteroDAO.singleReportero(c);
		ReporteroDAO R2=ReporteroDAO.singleReportero(c);
		Reportero r=new Reportero(6,"prueba","prueba","prueba");
		//aqui imprimo por pantalla el hashcode del objeto y es la misma es que es el mismo objeto que lo estoy llamando ods veces (si lo es,funciona!)
		System.out.println(R.hashCode());
		System.out.println(R2.hashCode());
		
		//R.insertarSinID(r);
		//metodo para buscar por id y añadi un system.out.print para ver el resultado de la consulta en consola(funciona!)
		R.buscarPorID(1);
		//prueba del metodo de la coleccion(funciona!)
		///R.buscarTodos();
		//metodo de update (este no termina de funcionar)
		//Reportero r=new Reportero(1,"vwfv","efve","svw");
		//R.modificar(r);
		
		//prueba del metodo eliminar(este si funciona!)
		//R.eliminar(r);
		//R.insertarConID(r);
		
		GiraDAO G=GiraDAO.singleGira(c);
		Gira g=new Gira(6,"prueba modificar",LocalDate.of(2016, 8, 19),LocalDate.of(2018, 8, 19));
		
		//metodo de insercion de gira(funciona)
		//G.insertarConID(g);
		
		//funciona!
		G.buscarPorID(2);
		
		//funciona!
		//G.eliminar(g);
		
		//prueba de metodos de concierto
		ConciertoDAO CON=ConciertoDAO.singleConcierto(c);
		Concierto con=new Concierto(11,LocalDate.of(2016, 8, 19),r,g);
		
		//CON.insertarConID(con);
		CON.insertarSinID(con);
		//estos metodos de concierto dan problemas
		CON.buscarPorID(2);

		
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
				if(conex!=null) {
					conex.close();
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
    					//lo hice con un long aunque este declarado como un int en a base de datos, sin embargo funciona sin problema
    					pstmt.setLong(1,r.getId());
    					pstmt.setString(2,r.getNombreyApellidos());
    					pstmt.setString(3,r.getNif());
    					pstmt.setString(4,r.gettelefono());
    					int resultadoInsercion = pstmt.executeUpdate();
    					System.out.println("resultado de insercion"+resultadoInsercion);
    				}
    				if(conex!=null) {
    					conex.close();
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
    					java.sql.Date fechaSQL = java.sql.Date.valueOf(c.getFechayhor());
    					pstmt.setDate(2,fechaSQL);
    					//he puesto las foreing keys como null para poder insertarlo sin errores
    					pstmt.setNull(3,Types.INTEGER);
    					pstmt.setNull(4,Types.INTEGER);
    					int resultadoInsercion = pstmt.executeUpdate();
    					System.out.println("resultado de insercion: "+resultadoInsercion);
    				}
    				//importante cerrar la conexion al terminar unaoperacion con la base de datos!
    				if(conex!=null) {
    					conex.close();
    				}	
    			} catch (SQLException e) {
    				System.out.println("Se ha producido una SQLException:" + e.getMessage());
    				e.printStackTrace();
    			}

    		}        
}
            
