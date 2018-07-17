package juego;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

public class Vivora {

	private LinkedList<Point> cuerpos = new LinkedList<Point>();
	private int x, y;
	private int largo = 1;
	private Cola cola = new Cola();
	public static int despx, despy;
	public static int vidas = 3;
	public static int puntos = 0;
	private static int contador = 0;

	// getters y setters
	// ----------------------------------------------------------------------------------
	public void setx(int x) {
		this.x = x;
	}

	public void sety(int y) {
		this.y = y;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public int getLargo() {
		return largo;
	}
	// ----------------------------------------------------------------------------------

	public Vivora(int x, int y) {
		despx = 1;
		despy = 0;
		this.x = x;
		this.y = y;
		cuerpos.add(new Point(x, y));
		cola.x = this.x;
		cola.y = this.y;
	}

	public void Dibujar(int[] mapa) {
		mapa[this.y * Mapa.CUADROS_ANCHO + this.x] = CuadroCabeza.ID;

		for (int i = 0; i < cuerpos.size(); i++) {

			mapa[cuerpos.get(i).y * Mapa.CUADROS_ANCHO + cuerpos.get(i).x] = CuadroCuerpo.ID;
		}
		mapa[cola.y * Mapa.CUADROS_ANCHO + cola.x] = CuadroCola.ID;
	}

	public void Actualizar(int[] mapa) {
		contador++;
		if (contador >= 10) {
			int x = this.x + despx;
			int y = this.y + despy;

			if (x > Mapa.CUADROS_ANCHO - 2) {
				x = 1;
			}
			if (y > Mapa.CUADROS_ALTO - 2) {
				y = 1;
			}
			if (y < 1) {
				y = Mapa.CUADROS_ALTO - 2;
			}
			if (x < 1) {
				x = Mapa.CUADROS_ANCHO - 2;
			}
			if (mapa[y * Mapa.CUADROS_ANCHO + x] != 0) {
				Colicion(mapa[y * Mapa.CUADROS_ANCHO + x]);
			}
			MoverCuerpos();

			this.y = y;
			this.x = x;
			contador = 0;
		}

	}

	private void Colicion(int val) {
		switch (val) {
		case CuadroPared.ID:
			// TODO poner que se hace cuando choca contra la pared;
			break;
		case CuadroCuerpo.ID:
			vidas -= 1;
			x = 1;
			y = 1;
			cuerpos.clear();
			cuerpos.add(new Point(x, y));
			break;
		case CuadroComida.ID:
			Random rand = new Random();
			int r = rand.nextInt(2) + 1;
			cuerpos.add(new Point(cuerpos.get(cuerpos.size() - 1).x, cuerpos.get(cuerpos.size() - 1).y));
			Comida.GenerarNueva();
			for (int i = 0; i < cuerpos.size(); i++) {
				if (cuerpos.get(i).x == Comida.x && cuerpos.get(i).x == Comida.y) {
					Comida.GenerarNueva();
				}
			}
			puntos += Comida.Valor;
			break;
		case CuadroComidaBost.ID:
			cuerpos.add(new Point(cuerpos.get(cuerpos.size() - 1).x, cuerpos.get(cuerpos.size() - 1).y));
			Inicio.esBoost = true;
			Vivora.puntos += ComidaBoost.Valor + 5;
			ComidaBoost.Bostear();
			Comida.GenerarNueva();

			for (int i = 0; i < cuerpos.size(); i++) {
				if (cuerpos.get(i).x == Comida.x && cuerpos.get(i).x == Comida.y) {
					Comida.GenerarNueva();
				}
			}
			break;
		}

	}

	private void MoverCuerpos() {

		cola.x = cuerpos.get(cuerpos.size() - 1).x;
		cola.y = cuerpos.get(cuerpos.size() - 1).y;

		if (cuerpos.size() > 1) {
			for (int i = cuerpos.size() - 1; i > 0; i--) {
				cuerpos.get(i).x = cuerpos.get(i - 1).x;
				cuerpos.get(i).y = cuerpos.get(i - 1).y;
			}

		}
		cuerpos.get(0).x = this.x;
		cuerpos.get(0).y = this.y;

	}

}
