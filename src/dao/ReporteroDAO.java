package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entidades.Reportero;
import utils.ConexBD_Agencia;

public class ReporteroDAO implements operacionesCRUD<Reportero>{
//aplicacion del patroc singleton
	Connection c;
	private static ReporteroDAO r;
	
	//constructor privado con una llamada al 
	private ReporteroDAO() {}
	
	public static ReporteroDAO singleReportero(Connection c) {
		if(r==null) {
		r=new ReporteroDAO();	
		}
		if (c == null) {
			c = ConexBD_Agencia.establecerConexion();
		}
		return r;
	}

	@Override
	public boolean insertarConID(Reportero r) {
		String insert="insert into reportero(id,nombre_apellido,nif_nie,telefono) values (?,?,?,?)";
		boolean confirmacion = false;
		try {
			//he metido la conexion en los metodos , pero entiendo que no hace falta ya que cuando creo el objeto le paso la conexion como argumento
			//pero como no estoy seguro seguire incluyendo esta estructura
			if (this.c == null || this.c.isClosed())
				this.c = ConexBD_Agencia.establecerConexion();
			PreparedStatement pstmt = c.prepareStatement(insert);
			pstmt.setLong(1,r.getId());
			pstmt.setString(2,r.getNombreyApellidos());
			pstmt.setString(3,r.getNif());
			pstmt.setString(4,r.gettelefono());
			int resultadoInsercion = pstmt.executeUpdate();
			//interesante que confirmacion reciba como valor un resultado booleano basado en una condicion
			confirmacion = (resultadoInsercion == 1);
			c.close();
		} catch (SQLException e) {
			System.out.println("SQL EXCEPTION");
			e.printStackTrace();
		}
		return confirmacion;
	}

	@Override
	public long insertarSinID(Reportero r) {
		long ret = -1;
		String insert="insert into reportero(nombre_apellido,nif_nie,telefono)values(?,?,?)";
		try {
			if (this.c == null || this.c.isClosed())
				this.c = ConexBD_Agencia.establecerConexion();
			PreparedStatement pstmt = c.prepareStatement(insert);
			pstmt.setString(1,r.getNombreyApellidos());
			pstmt.setString(2,r.getNif());
			pstmt.setString(3,r.gettelefono());
			ResultSet result=pstmt.executeQuery();
			while(result.next()) {
				long id=result.getLong("id");
				if (id != -1) {
					ret = id;
					System.out.println("el valor del id del reportero que insertaste es: "+ id);
				}
			}
			c.close();
		} catch (SQLException e) {
            System.out.println("se produjo una sql exception!");
			e.printStackTrace();
			//entiendo que este menos uno es para quereturnee un valor que inidica que no se realizo de forma correcta el insert
			return -1;
		}		
		return ret;
	}

	@Override
	public Reportero buscarPorID(long id) {
		Reportero r=null;
		String select="select * from reportero where id=?";
		try {
			if (this.c == null || this.c.isClosed())
				this.c = ConexBD_Agencia.establecerConexion();
			PreparedStatement pstmt = c.prepareStatement(select);
			pstmt.setLong(1, id);
			ResultSet result=pstmt.executeQuery();
			while(result.next()) {
				long id_r=result.getLong("id");
				String nom_r=result.getString("nombre_apellido");
				String nif_r=result.getString("nif_nie");
				String tef_r=result.getString("telefono");
				r=new Reportero();
				r.setId(id_r);
				r.setNombreyApellidos(nom_r);
				r.setNif(nif_r);
				r.settelefono(tef_r);
				System.out.println("el resultado de tu consulta es:"+r.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Collection<Reportero> buscarTodos() {
		List<Reportero> colecc = new ArrayList<Reportero>();
		String select="select * from reportero";
		Statement pstmt;
		try {
			pstmt = c.createStatement();
			ResultSet result = pstmt.executeQuery(select);
			while(result.next()) {
				long id_r=result.getLong("id");
				String nom_r=result.getString("nombre_apellido");
				String nif_r=result.getString("nif_nie");
				String tef_r=result.getString("telefono");
				Reportero r=new Reportero();
				r=new Reportero();
				r.setId(id_r);
				r.setNombreyApellidos(nom_r);
				r.setNif(nif_r);
				r.settelefono(tef_r);
				//esto tambien es para comprobar que el metodo funciona 
				System.out.println("el resultado de tu consulta es:"+r.toString());
				colecc.add(r);
			}
			c.close();
		} catch (SQLException e) {
			System.out.println("exception sql");
			e.printStackTrace();
		}
		return colecc;
	}

	//de estos metodos no hay ejempos, pero decidi intentar implementarlos con una variable auxiliar en la cual guardo los datos que meta el usuario
	//haciendo uso del metodo nuevoxxx
	@Override
	public boolean modificar(Reportero elemento) {
		Reportero r=Reportero.nuevoReportero();
		String update="update reportero set id=?,nombre_apellido=?,nif_nie=?,telefono=? where id=? ";
		PreparedStatement pstmt;
		try {
			pstmt = c.prepareStatement(update);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				long id_r=r.getId();
				String nom_r=r.getNombreyApellidos();
				String nif_r=r.getNif();
				String tef_r=r.gettelefono();
				r.setId(id_r);
				r.setNombreyApellidos(nom_r);
				r.setNif(nif_r);
				r.settelefono(tef_r);
				int resultado=pstmt.executeUpdate();
				System.out.println("el resultado del update es: "+resultado);	
			}
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminar(Reportero elemento) {
		// TODO Auto-generated method stub
		return false;
	}



}
