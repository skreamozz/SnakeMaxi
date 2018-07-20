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

	public static void main(String[] args) {
		new Inicio();
	}

	public Inicio() {
		dim = new Dimension(Pantalla.CUADROS_ANCHO * Cuadro.LADO, Pantalla.CUADROS_ALTO * Cuadro.LADO);
		ventana = new Ventana("Snake");
		pant = new Pantalla(dim);
		ventana.add(pant);
		ventana.pack();
		pant.requestFocus();
		iniciar();
	}

	private void iniciar() {
		this.start();
		try {
			this.join();
			ventana.dispose();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

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
			referenciaActualizacion = inicioBucle;
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

	private void Actualizar() {
		if (!enPausa) {
			pant.Actualizar();
		}
		contador++;
	}

}
