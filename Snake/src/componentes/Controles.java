package componentes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import juego.Inicio;
import juego.Vivora;

public class Controles implements KeyListener {

	@Override
	public void keyPressed(KeyEvent k) {
		// Obtengo El caracter que se preciono
		// ---------------------------------------------------------------
		char key = k.getKeyChar();

		// System.out.println("" + key);
		// ---------------------------------------------------------------

		// Lo comparo y dependiendo de esto realizo una u otra accion.
		// ---------------------------------------------------------------
		switch (key) {
		case 'a':
			if (Vivora.despx != 1) {
				Vivora.despx = -1;
				Vivora.despy = 0;
			}
			break;
		case 's':
			if (Vivora.despy != -1) {
				Vivora.despy = 1;
				Vivora.despx = 0;
			}
			break;
		case 'd':
			if (Vivora.despx != -1) {
				Vivora.despx = 1;
				Vivora.despy = 0;
			}

			break;
		case 'w':
			if (Vivora.despy != 1) {
				Vivora.despy = -1;
				Vivora.despx = 0;
			}
			break;
		case '':
			Inicio.enMarcha = false;
			break;
		case 'p':
			if (!Inicio.enPausa) {
				Inicio.enPausa = true;
			} else {
				Inicio.enPausa = false;
			}

			break;
		}
		// ---------------------------------------------------------------

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

}
