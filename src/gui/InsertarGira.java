package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.GiraDAO;
import entidades.Gira;
import entidades.Reportero;
import utils.ConexBD_Agencia;
import validaciones.Validaciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class InsertarGira extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertarGira frame = new InsertarGira();
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
	public InsertarGira() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre*:");
		lblNewLabel_1.setBounds(24, 73, 81, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(86, 70, 151, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("fecha apertura*:");
		lblNewLabel_2.setBounds(0, 109, 112, 14);
		contentPane.add(lblNewLabel_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(-315622800000L), null, null, Calendar.DAY_OF_YEAR));
		spinner.setBounds(86, 106, 140, 20);
		contentPane.add(spinner);
		
		JLabel lblNewLabel_3 = new JLabel("fecha cierre*:");
		lblNewLabel_3.setBounds(0, 138, 81, 14);
		contentPane.add(lblNewLabel_3);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerDateModel(new Date(-315622800000L), null, null, Calendar.DAY_OF_YEAR));
		spinner_1.setBounds(86, 135, 145, 20);
		contentPane.add(spinner_1);
		
		JButton btnNewButton = new JButton("cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = "Cerrar ventana";
				String msj = "¿Realmente desea cerrar la ventana?";
				int opcion = JOptionPane.showConfirmDialog(null, msj, titulo, JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnNewButton.setBounds(286, 196, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("aceptar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valido = false;
				String titulo = "";
				String msj = "";
				String errores = "";
				//creo  una nueva giras
				
				String nombre = textField_1.getText();
				valido = Validaciones.validarNombre(nombre);
				if (!valido) {
					errores += "el nombre que ingreso  no es valido porfavor introduzca otro nombre ";
					lblNewLabel_1.setForeground(Color.RED);
				} else {
					String nombregira=nombre;
				}
				valido = false;
				
				java.util.Date fecha = (Date) spinner.getValue();

				LocalDate fechaLD = LocalDate.of(fecha.getYear() + 1900, fecha.getMonth() + 1, fecha.getDate());

				valido = Validaciones.validarFecha(fechaLD);
				if (!valido) {
					errores += "la fecha que inserto no es valida!";
					lblNewLabel_2.setForeground(Color.RED);
				} else {
					LocalDate fechag1=fechaLD;
				}
				valido = false;
				java.util.Date fecha2 = (Date) spinner_1.getValue();

				LocalDate fechaLD2 = LocalDate.of(fecha2.getYear() + 1900, fecha2.getMonth() + 1, fecha2.getDate());

				valido = Validaciones.validarFecha(fechaLD2);
				if (!valido) {
					errores += "la fecha que inserto no es valida!";
					lblNewLabel_3.setForeground(Color.RED);
				} else {
					// me daba problemas para en insert into asi que he puesto  aqui una variable que al final  no se usa por que le pongo los atributos al objeto gira al final del todo
					LocalDate fechag2=fechaLD2;
				}
			
				GiraDAO G=GiraDAO.singleGira(ConexBD_Agencia.getCon());
				Gira g =new Gira(nombre,fechaLD,fechaLD2);
				long id=G.insertarSinID(g);
				if(id>0) {
					System.out.println("se inserto en nuevo socio su id en la base de datos es: " + id);
				}
				else {
					titulo = "ERROR al cerrar la Prueba en la BD";
					msj = "Hubo un error y NO se ha cerrado la prueba en la BD.";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_1.setBounds(63, 196, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
	}
}
