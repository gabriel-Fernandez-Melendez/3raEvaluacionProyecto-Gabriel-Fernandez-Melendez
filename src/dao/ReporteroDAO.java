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
//aplicacion del patron singleton(conexion y una entidad privada de la propia clase)
	private Connection c;
	private static ReporteroDAO r;
	
	//constructor privado y usando el constructor por defecto 
	private ReporteroDAO(Connection c) {
		if (c == null) {
			c = ConexBD_Agencia.establecerConexion();
			this.c=c;
		}}
	
	//un metodo en el cual paso como parametro la conexion y devuelve un objeto ReporteroDAO (siempre el mismo objeto!)
	public static ReporteroDAO singleReportero(Connection c) {
		if(r==null) {
		r=new ReporteroDAO(c);	
		}
		
		return r;
	}

	//este metodo inserta una fila nueva con if en la tabla reportero
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
		long resultad = -1;
		String insert="insert into reportero(nombre_apellido,nif_nie,telefono)values(?,?,?)";
		try {
			if (this.c == null || this.c.isClosed())
				this.c = ConexBD_Agencia.establecerConexion();
			PreparedStatement pstmt = c.prepareStatement(insert);
			pstmt.setString(1,r.getNombreyApellidos());
			pstmt.setString(2,r.getNif());
			pstmt.setString(3,r.gettelefono());
			resultad=pstmt.executeUpdate();
			System.out.println("el resultado de la insercion es "+resultad);
			c.close();
		} catch (SQLException e) {
            System.out.println("se produjo una sql exception!");
			e.printStackTrace();
			//entiendo que este menos uno es para quereturnee un valor que inidica que no se realizo de forma correcta el insert
			return -1;
		}		
		return resultad;
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
    //este metodo lee toda la tabla y mete los datos en una lista de objetos del tipo de la tabla (funciona)
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
	//haciendo uso del metodo nuevoxxx (este es el unico que no logre hacer funcionar)
	@Override
	public boolean modificar(Reportero e) {
		Reportero r=Reportero.nuevoReportero();
		String update="update reportero set id=?,nombre_apellido=?,nif_nie=?,telefono=? where id=? ";
		PreparedStatement pstmt;
		try {
			if (this.c == null || this.c.isClosed())
				this.c = ConexBD_Agencia.establecerConexion();
			pstmt = c.prepareStatement(update);
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				long id_r=e.getId();
				String nom_r=e.getNombreyApellidos();
				String nif_r=e.getNif();
				String tef_r=e.gettelefono();
				r.setId(id_r);
				r.setNombreyApellidos(nom_r);
				r.setNif(nif_r);
				r.settelefono(tef_r);
				int resultado=pstmt.executeUpdate();
				System.out.println("el resultado del update es: "+resultado);	
			}
			c.close();
		} catch (SQLException er) {
			System.out.println("hubo un error de sql");
			er.printStackTrace();
		}
		return false;
	}

	//este metodo elimina una fila de la tabla reportero (funciona)
	@Override
	public boolean eliminar(Reportero r) {
		boolean conf=false;
		 try {  
				if (this.c == null || this.c.isClosed())
					this.c = ConexBD_Agencia.establecerConexion();
		        PreparedStatement pstmt = c.prepareStatement("delete from reportero where id ="+r.getId()+ ";");
		        int resultado=pstmt.executeUpdate();
		        System.out.println("resultado de tu eliminacion: "+resultado);
		        conf= (resultado == 1);
		    } catch(Exception e) {
		        System.out.println(e);
		    }
		return conf;
	}



}
