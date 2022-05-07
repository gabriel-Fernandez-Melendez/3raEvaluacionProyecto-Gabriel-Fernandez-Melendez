package dao;

import java.sql.Connection;
import java.util.Collection;

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
	public boolean insertarConID(Gira elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(Gira elemento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Gira buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Gira> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(Gira elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Gira elemento) {
		// TODO Auto-generated method stub
		return false;
	}


}
