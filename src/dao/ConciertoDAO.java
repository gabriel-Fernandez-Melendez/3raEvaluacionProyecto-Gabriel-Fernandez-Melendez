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

import entidades.Concierto;
import entidades.Gira;
import entidades.Reportero;
import utils.ConexBD_Agencia;

public class ConciertoDAO implements operacionesCRUD<Concierto> {
	//atributos para la aplicacion de patron singleton
	Connection c;
	private static ConciertoDAO con;
	
	//constructor por defecto privado
	private ConciertoDAO() {}
	
	//metodo que llame al constructor y devuelva una instancia del objeto que necesita como argumento un objeto de tipo conexion
	public static ConciertoDAO singleConcierto(Connection c) {
		if(con==null) {
		con=new ConciertoDAO();	
		}
		if (c == null) {
			c = ConexBD_Agencia.establecerConexion();
		}
		return con;
	}

	
	//esta clase me dio problemas por sus claves foraneas
	@Override
	public boolean insertarConID(Concierto con) {
		String insert="insert into concierto(id,fecha_concierto,id_reportero,id_gira) values (?,?,?,?)";
		boolean confirmacion = false;
		try {
			if (this.c == null || this.c.isClosed())
				this.c = ConexBD_Agencia.establecerConexion();
			PreparedStatement pstmt = c.prepareStatement(insert);
			pstmt.setLong(1,con.getIdConcierto());
			java.sql.Date fechaSQL = java.sql.Date.valueOf(con.getFechayhor());
			pstmt.setDate(2,fechaSQL);
			pstmt.setLong(3,con.getReporteroConcierto().getId());
			pstmt.setLong(4,con.getGiraconciertos().getIdGira());
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
	public long insertarSinID(Concierto con) {
		long resultad=-1;
		String insert="insert into concierto(fecha_concierto,id_reportero,id_gira)values(?,?,?)";
		try {
			
			if (this.c == null || this.c.isClosed())
				this.c = ConexBD_Agencia.establecerConexion();
			PreparedStatement pstmt = c.prepareStatement(insert);
			java.sql.Date fechaSQL = java.sql.Date.valueOf(con.getFechayhor());
			pstmt.setDate(1, fechaSQL);
			pstmt.setLong(2,con.getReporteroConcierto().getId());
			pstmt.setLong(3,con.getGiraconciertos().getIdGira());
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
	public Concierto buscarPorID(long id) {
		//he tenido que crear un objeto para cada una de las fk , para pasar ese objeto "entero"(solo con el id)como parametro valido
		//he logrado eliminar el nullde sus claves foraneas!
		Concierto con=new Concierto();
		Reportero r=new Reportero();
	    Gira g=new Gira();
		String select="select * from concierto where id=?";
		try {
			if (this.c == null || this.c.isClosed())
				this.c = ConexBD_Agencia.establecerConexion();
			PreparedStatement pstmt = c.prepareStatement(select);
			pstmt.setLong(1, id);
			ResultSet result=pstmt.executeQuery();
			while(result.next()) {
				long id_c=result.getLong("id");
				java.sql.Date fechaSQL =result.getDate("fecha_concierto");
				LocalDate fecha=fechaSQL.toLocalDate();
				long id_r =result.getLong("id_reportero");
				long id_g=result.getLong("id_gira");
				con.setIdConcierto(id_c);
				con.setFechayhor(fecha);
				r.setId(id_r);
				con.setReporteroConcierto(r);
				g.setIdGira(id_g);
				con.setGiraconciertos(g);	
			}	
			System.out.println("el resultado de tu consulta es:"+con.toString());
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	@Override
	public Collection<Concierto> buscarTodos() {
		List<Concierto> colecc = new ArrayList<Concierto>();
		String select="select * from concierto";
		Statement pstmt;
		try {
			pstmt = c.createStatement();
			ResultSet result = pstmt.executeQuery(select);
			while(result.next()) {
				long id_c=result.getLong("id");
				java.sql.Date fechaSQL =result.getDate("fecha_concierto");
				LocalDate fecha=fechaSQL.toLocalDate();
				long id_r=result.getLong("id_reportero");
				long id_g=result.getLong("id_concierto");
				Concierto con=new Concierto();
				//OJO  esta es la implementacion de una relacion 1:n con la que tenia problemas , me he dado cuenta de la necesidad
				// de declarar los objetos de el tipo necesarios para settear sus parametros y luego pasar ese objeto "entero" al constructor de concierto
				Reportero r =new Reportero();
				Gira g=new Gira();
				r.setId(id_r);
				g.setIdGira(id_g);
				con.setIdConcierto(id_c);
				con.setFechayhor(fecha);
				con.setReporteroConcierto(r);
				con.setGiraconciertos(g);
				//esto tambien es para comprobar que el metodo funciona 
				System.out.println("el resultado de tu consulta es:"+con.toString());
				colecc.add(con);
			}
			c.close();
		} catch (SQLException e) {
			System.out.println("exception sql");
			e.printStackTrace();
		}
		return colecc;
	}

	@Override
	public boolean modificar(Concierto con) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Concierto con) {
		// TODO Auto-generated method stub
		return false;
	}

}
