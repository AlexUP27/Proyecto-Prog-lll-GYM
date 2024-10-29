package gui;
	
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaInicial extends JFrame{
	//Declaramos los componente cuadro de texto
	private JTextField txtNombreUsuario;
	private JPasswordField txtContraseniaUsuario;
	//Declaramos los paneles
	private JPanel pCentro, pNorte, pEste, pOeste, pSur;
		
	
	public VentanaInicial() {
		super();
		
		//Establecemos las propiedades de la ventana
		setBounds(300, 200, 600, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("DEUSTOGYM- Ventana Inicial");
		ImageIcon imagen = new ImageIcon("img/icono.png");
		setIconImage(imagen.getImage());
		
		//Instanciamos los paneles
		pCentro = new JPanel();
		//Modificamos el Layout del panel centro
		pCentro.setLayout(new GridLayout(2, 2)); //Formato de matriz de 2x2
		pNorte = new JPanel();
		pSur = new JPanel();
		pEste = new JPanel();
		pOeste = new JPanel();
		
		//Añadimos los paneles al panel principal de la ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
			
		setVisible(true); //Siempre la última sentencia
	}
	
}
