package componentes;

import java.awt.BorderLayout;

import javax.swing.JFrame;

// Creaci�n y configuraci�n de la ventana.
public class Ventana extends JFrame {

	public Ventana(String titulo) {

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.setTitle(titulo);
		this.setLayout(new BorderLayout());

	}

}
