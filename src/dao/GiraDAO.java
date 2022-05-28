package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entidades.Gira;
import utils.ConexBD_Agencia;

public class GiraDAO implements operacionesCRUD<Gira>{
	//aplicacion de patron singleton
	Connection c;
	//importante hacer estatica a la instancia del objeto!
	private static GiraDAO g;
	
	//constructor privado que llamaremos desde el metodo publico
	private GiraDAO() {}
	
	//metodo del cual devuelves una entidad del objeto y de paso le pasas la conexcion como argumento para usar los metodos Scrud
	public static GiraDAO singleGira(Connection c) {
		if(g==null) {
		g=new GiraDAO();
		}
		if (c == null) {
			c = ConexBD_Agencia.establecerConexion();
		}
		return g;
	}
	
	@Override
	public boolean insertarConID(Gira g) {
		String insert="insert into gira(id,nombre_gira,fecha_ini,fecha_fin) values (?,?,?,?)";
		boolean confirmacion = false;
		try {
			if (this.c == null || this.c.isClosed())
				this.c = ConexBD_Agencia.establecerConexion();
			PreparedStatement pstmt = c.prepareStatement(insert);
			pstmt.setLong(1,g.getIdGira());
			pstmt.setString(2,g.getNombreGira());
			java.sql.Date fechaSQL = java.sql.Date.valueOf(g.getFechaApertura());
			pstmt.setDate(3, fechaSQL);
			java.sql.Date fechaSQL2 = java.sql.Date.valueOf(g.getFechaCierre());
			pstmt.setDate(4,fechaSQL2);
			int resultadoInsercion = pstmt.executeUpdate();
			confirmacion = (resultadoInsercion == 1);
			System.out.println("el resultado de la insercion es "+ resultadoInsercion);
			c.close();
		} catch (SQLException e) {
			System.out.println("SQL EXCEPTION");
			e.printStackTrace();
		}
		return confirmacion;
	}

	@Override
	public long insertarSinID(Gira g) {
		long resultad = -1;
		String insert="insert into gira(nombre_gira,fecha_ini,fecha_fin)values(?,?,?)";
		try {
			if (this.c == null || this.c.isClosed())
				this.c = ConexBD_Agencia.establecerConexion();
			PreparedStatement pstmt = c.prepareStatement(insert);
			pstmt.setString(1,g.getNombreGira());
			java.sql.Date fechaSQL = java.sql.Date.valueOf(g.getFechaApertura());
			pstmt.setDate(2, fechaSQL);
			java.sql.Date fechaSQL2 = java.sql.Date.valueOf(g.getFechaCierre());
			pstmt.setDate(3,fechaSQL2);
			resultad=pstmt.executeUpdate();
			System.out.println("el resultado de la insercion es "+resultad);
			c.close();
		} catch (SQLException e) {
            System.out.println("se produjo una sql exception!");
			e.printStackTrace();
			//entiendo que este menos uno es para que returnee un valor que inidica que no se realizo de forma correcta el insert
			return -1;
		}		
		return resultad;
	}

	@Override
	public Gira buscarPorID(long id) {
		Gira g=null;
		String select="select * from gira where id=?";
		try {
			if (this.c == null || this.c.isClosed())
				this.c = ConexBD_Agencia.establecerConexion();
			PreparedStatement pstmt = c.prepareStatement(select);
			pstmt.setLong(1, id);
			ResultSet result=pstmt.executeQuery();
			while(result.next()) {
				long id_g=result.getLong("id");
				String nom_g=result.getString("nombre_gira");
				java.sql.Date fechaSQL =result.getDate("fecha_ini");
				java.sql.Date fechaSQL2 =result.getDate("fecha_fin");
				//bastante engorroso el pasar a un localDate pero consegui un metodo en stackoverflow
				LocalDate fecha_l=fechaSQL.toLocalDate();
				LocalDate fecha_l2=fechaSQL2.toLocalDate();
				g=new Gira();
				g.setIdGira(id_g);
				g.setNombreGira(nom_g);
				g.setFechaApertura(fecha_l);
				g.setFechaCierre(fecha_l2);

			}	
			System.out.println("el resultado de tu consulta es:"+g.toString());
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g;
	}

	@Override
	public Collection<Gira> buscarTodos() {
		List<Gira> colecc = new ArrayList<Gira>();
		String select="select * from gira";
		Statement pstmt;
		try {
			pstmt = c.createStatement();
			ResultSet result = pstmt.executeQuery(select);
			while(result.next()) {
				long id_g=result.getLong("id");
				String nom_g=result.getString("nombre_gira");
				java.sql.Date fechaSQL =result.getDate("fecha_ini");
				java.sql.Date fechaSQL2 =result.getDate("fecha_fin");
				//bastante engorroso el pasar a un localDate pero consegui un metodo en stackoverflow
				LocalDate fecha_l=fechaSQL.toLocalDate();
				LocalDate fecha_l2=fechaSQL2.toLocalDate();
				Gira g=new Gira();
				g.setIdGira(id_g);
				g.setNombreGira(nom_g);
				g.setFechaApertura(fecha_l);
				g.setFechaCierre(fecha_l2);
				//esto tambien es para comprobar que el metodo funciona 
				System.out.println("el resultado de tu consulta es:"+g.toString());
				colecc.add(g);
			}
			c.close();
		} catch (SQLException e) {
			System.out.println("exception sql");
			e.printStackTrace();
		}
		return colecc;
	}

	//unico ejercicio que tiene algun fallo que no logro detectar
	@Override
	public boolean modificar(Gira g) {
		boolean val=false;
		Gira aux=new Gira();
		String update="update gira set id=?,nombre_gira=?,fecha_ini=?,fecha_fin=? where id=? ";
		PreparedStatement pstmt;
		try {
			if (this.c == null || this.c.isClosed())
				this.c = ConexBD_Agencia.establecerConexion();
			pstmt = c.prepareStatement(update);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				long id_g=g.getIdGira();
				String nom_g=g.getNombreGira();
				LocalDate fecha=g.getFechaApertura();
				LocalDate fecha2=g.getFechaCierre();
				aux.setIdGira(id_g);
				aux.setNombreGira(nom_g);
				aux.setFechaApertura(fecha);
				aux.setFechaCierre(fecha2);
				int resultado=pstmt.executeUpdate();
				System.out.println("el resultado del update es: "+resultado);	
			}
			c.close();
		} catch (SQLException e) {
			System.out.println("hubo un error de sql");
			e.printStackTrace();
		}
		return val;
	}

	//funciona!
	@Override
	public boolean eliminar(Gira g) {
		boolean conf=false;
		 try {  
				if (this.c == null || this.c.isClosed())
					this.c = ConexBD_Agencia.establecerConexion();
		        PreparedStatement pstmt = c.prepareStatement("delete from gira where id ="+g.getIdGira()+ ";");
		        int resultado=pstmt.executeUpdate();
		        System.out.println("resultado de tu eliminacion: "+resultado);
		        conf= (resultado == 1);
		    } catch(Exception e) {
		        System.out.println(e);
		    }
		return conf;
	}


}
