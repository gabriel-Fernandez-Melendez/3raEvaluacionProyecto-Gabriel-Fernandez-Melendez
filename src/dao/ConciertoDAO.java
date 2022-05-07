package dao;

import java.sql.Connection;
import java.util.Collection;

import entidades.Concierto;
import utils.ConexBD_Agencia;

public class ConciertoDAO implements operacionesCRUD<Concierto> {
	Connection c;
	private static ConciertoDAO con;
	
	private ConciertoDAO() {}
	
	public static ConciertoDAO singleConcierto(Connection c) {
		if(con==null) {
		con=new ConciertoDAO();	
		}
		if (c == null) {
			c = ConexBD_Agencia.establecerConexion();
		}
		return con;
	}

	@Override
	public boolean insertarConID(Concierto elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(Concierto elemento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Concierto buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Concierto> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(Concierto elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Concierto elemento) {
		// TODO Auto-generated method stub
		return false;
	}

}
