package entidades;


//importamos la clase arraylist para poder implementarla
import java.util.ArrayList;
import java.util.Scanner;
//autor Germ�n

///*Aqu� puse los atributos*
public class Reportero {
	private long id;
	private String nombreyApellidos;
	private String nif;
	private String telefono;
	
	//autor de la conexion con momento:Gabriel importante:no se si es correcta pero adjunto el link de donde saque la informacion para
	//realizar la conexion link:https://www.youtube.com/watch?v=BGStNPa0qRk&t=7s
	
	//conexion con conciertos 1:N ya que un reportero documenta varios conciertos
	public ArrayList<Concierto> conciertos=new ArrayList<Concierto>();
	
	//getters setters de conciertos
	public ArrayList<Concierto> getConciertos() {
		return conciertos;
	}

	public void setConciertos(ArrayList<Concierto> conciertos) {
		this.conciertos = conciertos;
	}

	// Aqu� est� el construcor de los atributos
	public Reportero(String nombreyApellidos, String nif, String telefono) {
		this.nombreyApellidos = nombreyApellidos;
		this.nif = nif;
		this.telefono = telefono;
	}

	// Aqu� est� el construcor de los Reportero
	public Reportero() {
	}
	
	//constructor con el parametro id para usaro en la base de datos
	public Reportero(long id, String nombreyApellidos, String nif, String telefono) {
		super();
		this.id = id;
		this.nombreyApellidos = nombreyApellidos;
		this.nif = nif;
		this.telefono = telefono;
	}

	// en este metodo se introduce informacion en los difernetes metodos
	public static Reportero nuevoReportero() {
		Reportero ret = new Reportero(); // se hace el ret para poder hacer un return
		Scanner read = new Scanner(System.in);
		String nombreyApellidos = "";
		String nif = "";
		String telefono = "";
		long id=0;
		System.out.println("introduce un id para el reportero");
		read.nextLong();
		System.out.print("introduce el nombre y el apellido");
		nombreyApellidos = read.next();
		System.out.print("introduce el NIF");
		nif = read.next();
		System.out.print("introduce el telefono");
		telefono = read.nextLine();
		read.close();
		//este es con el constructor que tiene el telefono como string y el if para la base de datos
		ret=new Reportero(id,nombreyApellidos,nif,telefono);
    
		return ret;// aqui se ejecuta el return
	}

	// getter de nombre y apellidos
	public String getNombreyApellidoos() {
		return nombreyApellidos;
	}

	// setter de nombre y apellidos
	public void setNombreyApellidos(String nombreyapellidos) {
		nombreyApellidos = nombreyapellidos;
	}

	// getter de NIF
	public String getNif() {
		return nif;
	}

	// setter de NIF
	public void setNif(String nif) {
		this.nif = nif;
	}

	// getter de telefono
	public String gettelefono() {
		return telefono;
	}

	// setter de telefono
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreyApellidos() {
		return nombreyApellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	// Aqui est� los strings
	public String toString() {
		return "Reportero [NombreyApellidos=" + nombreyApellidos + ", NIF=" + nif + ", Telefono=" + telefono + "]";
	}

}