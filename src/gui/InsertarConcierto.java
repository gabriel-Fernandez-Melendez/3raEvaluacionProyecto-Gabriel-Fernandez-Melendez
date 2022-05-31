package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ConciertoDAO;
import dao.GiraDAO;
import dao.ReporteroDAO;
import entidades.Concierto;
import entidades.Gira;
import entidades.Reportero;
import utils.ConexBD_Agencia;
import validaciones.Validaciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class InsertarConcierto extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertarConcierto frame = new InsertarConcierto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InsertarConcierto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("insertar concierto");
		
		JLabel lblNewLabel = new JLabel("insertar concierto");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 28));
		lblNewLabel.setBounds(10, 11, 263, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("id*:");
		lblNewLabel_1.setBounds(92, 71, 33, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(119, 68, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("fecha del concierto*:");
		lblNewLabel_2.setBounds(10, 96, 112, 14);
		contentPane.add(lblNewLabel_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(-315622800000L), null, null, Calendar.DAY_OF_YEAR));
		spinner.setBounds(119, 96, 92, 20);
		contentPane.add(spinner);
		
		JLabel lblNewLabel_3 = new JLabel("id del reportero*:");
		lblNewLabel_3.setBounds(20, 121, 92, 14);
		contentPane.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(119, 118, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("id de gira*:");
		lblNewLabel_4.setBounds(50, 146, 62, 14);
		contentPane.add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(119, 143, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valido = false;
				String titulo = "";
				String msj = "";
				String errores = "";
				//los dos objetos que mas tarde pasare como argumento!
				Reportero reporcon=new Reportero();
				Gira Giracon=new Gira();
				//aqui  hace falta una transformacion a un long ya que lo que nos devuelve el metodo get text es un string
				long id=Long.parseLong(textField.getText());
				valido=Validaciones.validarId(id);
				if(!valido) {
					errores +="el if que ha inguesado no es valido ingresa otro id!";
					lblNewLabel_1.setForeground(Color.RED);
				}
				else {
					//aqui lo guardo en una variable para luego declarar el objeto y pasar todos los parametros en la misma linea!
					long id_con=id;
				}
				valido = false;
				java.util.Date fecha = (Date) spinner.getValue();

				LocalDate fechaLD = LocalDate.of(fecha.getYear() + 1900, fecha.getMonth() + 1, fecha.getDate());

				valido = Validaciones.validarFecha(fechaLD);
				if (!valido) {
					errores += "la fecha que inserto no es valida!";
					lblNewLabel_2.setForeground(Color.RED);
				} else {
					LocalDate fechald= fechaLD;
				}
                valido = false;
				
				long id_repor=Long.parseLong(textField_1.getText());
				valido=Validaciones.validarId(id_repor);
				if(valido) {
					//en este caso para comprobar que funciona y por ser una fk lo que hago es hacer uso del metodo buscar por id
					ReporteroDAO R=ReporteroDAO.singleReportero(ConexBD_Agencia.getCon());
					//busco el objeto gira apartir del id
					Reportero aux_r=R.buscarPorID(id_repor);
					//si la gira con ese id no es null es que se va a referenciar a una verdadera gira de la base de datos!asi que se guarda el dato
					if(aux_r != null) {
						//creamos un  objeto y le setteamos el valor del id a referenciar
						reporcon.setId(id_repor);
					}
				}
				else {
						errores += "el id del reportero no existe en la BD!";
						lblNewLabel_3.setForeground(Color.RED);
					}
				valido = false;
				
				long id_gira=Long.parseLong(textField_2.getText());
				valido=Validaciones.validarId(id_gira);
				if(valido) {
					//en este caso para comprobar que funciona y por ser una fk lo que hago es hacer uso del metodo buscar por id
					GiraDAO G=GiraDAO.singleGira(ConexBD_Agencia.getCon());
					//busco el objeto gira apartir del id
					Gira aux=G.buscarPorID(id_gira);
					//si la gira con ese id no es null es que se va a referenciar a una verdadera gira de la base de datos!asi que se guarda el dato
					if(aux != null) {
						//creamos un  objeto y le setteamos el valor del if a referenciar
						Giracon.setIdGira(id_gira);
					}
					
				}
				else {
						errores += "el id de la gira no existe en la BD!";
						lblNewLabel_4.setForeground(Color.RED);
					}
				valido = errores.isEmpty();

				if (!valido) {
					titulo = "ERROR: Campos inválidos";
					msj = "ERROR: los siguientes campos NO son válidos:\n\n";
					if (!errores.isEmpty())
						msj += errores + "\n";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
					return;
				} 
				//si  todo ha ido insertamos en la base de datos
					ConciertoDAO CON=ConciertoDAO.singleConcierto(ConexBD_Agencia.getCon());
					Concierto insert=new Concierto(id,fechaLD,reporcon,Giracon);
					valido=CON.insertarConID(insert);
					if(valido) {
						System.out.println("se inserto en concierto en la base de datos!");
					}
					else {
						titulo = "ERROR al insertar el concierto en la BD";
						msj = "Hubo un error y no se inserto el concierto en la BD.";
						JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		btnNewButton.setBounds(60, 178, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = "Cerrar ventana";
				String msj = "¿Realmente desea cerrar la ventana?";
				int opcion = JOptionPane.showConfirmDialog(null, msj, titulo, JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnNewButton_1.setBounds(266, 178, 89, 23);
		contentPane.add(btnNewButton_1);
		
	}

}
