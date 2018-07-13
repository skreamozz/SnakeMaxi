package juego;

import java.awt.Dimension;

import componentes.Ventana;

public class Inicio {

	private Ventana ventana;
	private Dimension dim;
	private Pantalla pant;
	public static boolean enMarcha = true;
	public static boolean enPausa = false;

	public static int VelocidadInicial = 200;
	public static int Velocidad = VelocidadInicial;
	private int Boost = 30;
	public static boolean esBoost = false;

	public Inicio() {
		dim = new Dimension(Pantalla.CUADROS_ANCHO * Cuadro.LADO, Pantalla.CUADROS_ALTO * Cuadro.LADO);
		ventana = new Ventana("Snake");
		pant = new Pantalla(dim);
		ventana.add(pant);
		ventana.pack();
		ventana.requestFocus();
		pant.requestFocus();
		Iniciar();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Inicio();
	}

	private void Iniciar() {
		while (enMarcha) {
			if (!enPausa) {
				pant.Actualizar();
				pant.Dibujar();
			}

			if (Vivora.vidas == 0) {
				break;
			}
			try {
				if (Velocidad < 50)
					Velocidad = VelocidadInicial;
				if (esBoost) {
					if (Boost == 0) {
						Velocidad = VelocidadInicial;
						Boost = 30;
						esBoost = false;
					}
					Boost--;
				}
				Thread.sleep(Velocidad);

			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}
		ventana.dispose();

	}

}
