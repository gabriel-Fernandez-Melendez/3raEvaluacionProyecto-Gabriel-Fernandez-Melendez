package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ReporteroDAO;
import entidades.Reportero;
import utils.ConexBD_Agencia;
import validaciones.Validaciones;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

//frame probado , funciona he inserta datos en la base de datos sin problema 
public class InsertarReportero extends JFrame {

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
					InsertarReportero frame = new InsertarReportero();
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
	public InsertarReportero() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("insertar Reportero");
		
		JLabel lblNewLabel = new JLabel("nombre y apellidos*:");
		lblNewLabel.setBounds(35, 111, 101, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(146, 108, 144, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("nif*:");
		lblNewLabel_1.setBounds(75, 152, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(146, 149, 122, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("telefono*:");
		lblNewLabel_2.setBounds(75, 188, 61, 14);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(146, 185, 122, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("calcelar");
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
		btnNewButton.setBounds(266, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("aceptar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean valido = false;
				String titulo = "";
				String msj = "";
				String errores = "";
				Reportero r=new Reportero();
				String nombre = textField.getText();
				valido = Validaciones.validarNombre(nombre);
				if (!valido) {
					errores += "el nombre que ingreso  no es valido porfavor introduzca otro nombre ";
					lblNewLabel.setForeground(Color.RED);
				} else {
					r.setNombreyApellidos(nombre);
				}
				valido = false;
				String nif = textField_1.getText();
				valido = Validaciones.validarNIF(nif);
				if (!valido) {
					errores += "el nif no es valido ponga uno valido!";
					lblNewLabel_1.setForeground(Color.RED);
				} else {
					r.setNif(nif);
				}
				valido = false;
				String telefono = textField_2.getText();
				valido = Validaciones.validarTelefono(telefono);
				if (!valido) {
					errores += "el telefono no es valido ponga uno valido!";
					lblNewLabel_2.setForeground(Color.RED);
				} else {
					r.settelefono(telefono);
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
				ReporteroDAO R=ReporteroDAO.singleReportero(ConexBD_Agencia.getCon());
				long id=R.insertarSinID(r);
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
		btnNewButton_1.setBounds(60, 227, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("inserta un reportero:");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 24));
		lblNewLabel_3.setBounds(23, 30, 324, 52);
		contentPane.add(lblNewLabel_3);
	}
}
