package juego;

import java.awt.Dimension;

import componentes.Ventana;

public class Inicio extends Thread implements Runnable {

	private Ventana ventana;
	private Dimension dim;
	private Pantalla pant;
	public static boolean enMarcha = true;
	public static boolean enPausa = false;
	public static int contador = 0;

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

	public void run() {
		final int NS_POR_SEGUNDO = 1000000000;
		final int APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();

		double tiempoTranscurrido;
		double delta = 0;

		while (enMarcha) {
			final long inicioBucle = System.nanoTime();
			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
			while (delta >= 1) {
				Actualizar();
				delta--;

			}
			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
				ventana.setTitle("contador = " + contador);
				contador = 0;
				referenciaContador = System.nanoTime();

			}
		}

	}

	private void iniciar() {
		this.start();

	}

	private void Actualizar() {
		pant.Actualizar();
		contador++;
	}

	private void Dibujar() {
		pant.Dibujar();
	}

	private void Iniciar() {
		while (enMarcha) {
			if (!enPausa) {
				Actualizar();

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
