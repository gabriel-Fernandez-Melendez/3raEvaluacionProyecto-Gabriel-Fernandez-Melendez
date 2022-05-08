package entidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

import utils.Datos;
import utils.Utiles;

public class Concierto {
	//atributos de la clase 
	private long idConcierto;
	private LocalDate fechayhor;
	
	//conexion con Reportero un  solo reportero ya que un reportero documenta varios conciertos,en la clase reportero habra un array de conciertos
	private Reportero reporteroConcierto;
	//conexion con Gira
	private Gira Giraconciertos;
	
    //constructor por defecto
	public Concierto() {
	}

    //constructor con todos los atributos de la clase
	public Concierto(long idConcierto, LocalDate fechayHora) {
		super();
		this.idConcierto = idConcierto;
		this.fechayhor = fechayHora;
	}
	
	//constructor con todos los datos (incluyendo aquellos campos referentes a la correcta conexion a la base de datos)
    public Concierto(long idConcierto, LocalDate fechayhor, Reportero reporteroConcierto, Gira giraconciertos) {
		super();
		this.idConcierto = idConcierto;
		this.fechayhor = fechayhor;
		this.reporteroConcierto = reporteroConcierto;
		Giraconciertos = giraconciertos;
	}

	//getters y setters
	public long getIdConcierto() {
		return idConcierto;
	}

	public void setIdConcierto(long idConcierto) {
		this.idConcierto = idConcierto;
	}

    public LocalDate getFechayhor() {
		return fechayhor;
	}

	public void setFechayhor(LocalDate fechayhor) {
		this.fechayhor = fechayhor;
	}

	public Reportero getReporteroConcierto() {
		return reporteroConcierto;
	}

	public void setReporteroConcierto(Reportero reporteroConcierto) {
		this.reporteroConcierto = reporteroConcierto;
	}

	public Gira getGiraconciertos() {
		return Giraconciertos;
	}

	public void setGiraconciertos(Gira giraconciertos) {
		Giraconciertos = giraconciertos;
	}

	//metodo to string modificado para que muestre de forma correcta los datos de la base de datos
	public String toString() {
		return "id: " + idConcierto + "fecha: " + fechayhor +"id reportero"+ reporteroConcierto.getId()+"id gira: "+Giraconciertos.getIdGira();
	}
		
	//parece estar perfectamente declarado pero no funciona	(esta arreglado,en la primera evaluacion
	//ret devolvia un objeto vacio por que no le pasaba argumentos a su constructor)
		public static Concierto nuevoconcierto() {
			Concierto ret = null;
			Scanner scan =new Scanner(System.in);
			long idConcierto = -1;
			LocalDate fechayhor; 
			System.out.println("indique cual es su identificador");
			idConcierto = scan.nextLong();
			System.out.println("indique el codigo de su descuento");
			fechayhor = Utiles.leerFecha();
			scan.close();
			//arreglado en esta linea
			ret= new Concierto(idConcierto,fechayhor);
			return ret;
	}
		
		//metodo data para exportar los datos de un Concierto de forma ordenanda en un string 
		//y los presenta como : <Concierto.idconcierto> "|" <Concierto.fechahora>
		//es un metodo corto pero son los atributos con los que cuenta esta clase
		public String Data(){
			return ""+this.idConcierto+"|"+this.fechayhor;
		}
		
		//metodo de la segunda evaluacion para exportar un fichero te texto a uno binario(no se como quitar la bombilla que sale por que no se esta utilizando el metodo)
		public static void exportarConcierto(Concierto[]concierto) {
			//fichero imaginado
			String path ="Concierto.txt";
			File f=new File(path);
		 	FileWriter e=null;
		 	PrintWriter escritor=null;
		 	try {
		 		try {
				e=new FileWriter(f,false);
				escritor=new PrintWriter(e);
				for(Concierto c:Datos.conciertos) {
					//uso del metodo data que es lo que sera escrito en el fichero 
					escritor.println(c.Data());
				}
			}finally {
				if (e!=null){
					e.close();
				}
				if(escritor != null) {
					escritor.close();
				}
			}
		 	}catch (IOException e1) {
				e1.printStackTrace();
			}			
		}

		
		
		
		
	
   
	
	
	
}
