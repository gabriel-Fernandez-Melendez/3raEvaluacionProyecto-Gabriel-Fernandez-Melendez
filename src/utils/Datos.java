package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//en este caso  tuve que dejar solo estos 3 import, ya que como hice un  proyecto con solo 3 de las clases daba en error 
//ya que habia arrays de objetos que no existian en el programa
import entidades.Concierto;

import entidades.Gira;

import entidades.Reportero;

//autor de la clase Datos: Gabriel
public class Datos {
	/*incluyo un total de 10 objetos distintos por clase,los considero suficientes
	 *para el los ejercicios que haremos, en caso de ser necesario hare una ampliacion
	 *en la clase que sea necesaria  
	 */
	
	/*estos arrays no tendran que ser usados de nuevo, pero dejo la clase en el paquete de datos por si luego quiere  persistirse en una base de datos
	 * con los metodos en principal hechos para ello
	 */

	// el primer numero es el id d la entidad , no su posicion en el array que empieza por cero
	public static Concierto[]conciertos= {
		   new Concierto(1,LocalDate.parse("10/02/1995", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
		   new Concierto(2,LocalDate.parse("22/03/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
		   new Concierto(3,LocalDate.parse("20/08/1992", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
		   new Concierto(4,LocalDate.parse("30/05/1993", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
		   new Concierto(5,LocalDate.parse("17/10/1985", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
		   new Concierto(6,LocalDate.parse("24/11/1993", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
		   new Concierto(7,LocalDate.parse("12/02/1993", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
		   new Concierto(8,LocalDate.parse("14/07/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
		   new Concierto(9,LocalDate.parse("01/06/1991", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
		   new Concierto(10,LocalDate.parse("09/06/1985", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
	};

	public static Gira[]giras= {
			new Gira(1,"ac/dc comeback",LocalDate.parse("10/06/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("10/08/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new Gira(2,"ac/dc return",LocalDate.parse("10/02/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("10/04/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new Gira(3,"ac/dc el regreso",LocalDate.parse("02/06/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("10/09/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new Gira(4,"ac/dc bagette",LocalDate.parse("04/01/2002", DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("10/06/2002", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new Gira(5,"foyone en la casa",LocalDate.parse("10/04/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("20/09/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new Gira(6,"fernando costa v34",LocalDate.parse("10/06/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("10/06/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new Gira(7,"ayaxyprok",LocalDate.parse("05/06/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("10/09/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new Gira(8,"delaossa",LocalDate.parse("10/06/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("10/06/2002", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new Gira(9,"luisker",LocalDate.parse("10/08/2002", DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("10/06/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),
			new Gira(10,"ac/dc comeback2",LocalDate.parse("18/06/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy")),LocalDate.parse("10/12/2001", DateTimeFormatter.ofPattern("dd/MM/yyyy"))),		
	};

	
	public static Reportero[]reporteros= {
			new Reportero(1,"Antonio Domingez","51498665S","77664251"),new Reportero(2,"Bolson Vilvo","51498665Q","99885432"),
			new Reportero(3,"Miles Morales","51498665L","998674445"),new Reportero(4,"Maria Domingez","548446651U","648442569"),
			new Reportero(5,"Derek mark","587452165Q","259887654"),new Reportero(6,"Estefania Domingez","51498665X","698569246"),
			new Reportero(7,"Jessie Domingez","984657421P","336659471"),new Reportero(8,"Willian Mendez","51498665O","259887654"),
			new Reportero(9,"Pedro Domingez","51498665N","325686447"),new Reportero(10,"Antonio Rodrigez","365489754E","452165558"),
	};
	
	
	

}
