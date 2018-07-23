package juego;

import java.awt.Point;
import java.util.LinkedList;

public class Vivora {

	private LinkedList<Point> cuerpos = new LinkedList<Point>();
	private int x, y;
	private int largo = 1;
	private Cola cola = new Cola();
	public static int despx, despy;
	public static int vidas = 3;
	public static int puntos = 0;
	private static int contador = 0;
	public static char direccion = 'd';

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

			if (despx > 0 && despy == 0) {
				this.direccion = 'd';
			} else if (despx < 0 && despy == 0) {
				this.direccion = 'i';
			} else if (despx == 0 && despy > 0) {
				this.direccion = 's';
			} else if (despx == 0 && despy < 0) {
				this.direccion = 'w';
			}

			switch (mapa[y * Mapa.CUADROS_ANCHO + x]) {
			case 0xff00ff00:
				// pared abajo
				y = 1;
				break;
			case 0xff00f700:
				// pared Izquierda
				x = 1;

				break;
			case 0xff00f600:
				// pared Arriba
				y = Mapa.CUADROS_ALTO - 2;
				break;
			case 0xff00f500:
				// pared derecha
				x = Mapa.CUADROS_ANCHO - 2;
				break;
			case 0:
				break;
			default:
				// en caso de que no sea ninguno de los anteriores es por que esta en colicion.
				Colicion(mapa[y * Mapa.CUADROS_ANCHO + x]);
				break;

			}
			MoverCuerpos();

			this.y = y;
			this.x = x;
			contador = 0;
		}

	}

	private void Colicion(int val) {
		switch (val) {
		case CuadroCuerpo.ID:
			vidas -= 1;
			if (vidas <= 0)
				Inicio.enMarcha = false;
			x = 1;
			y = 1;
			cuerpos.clear();
			cuerpos.add(new Point(x, y));
			break;
		case CuadroComida.ID:
			cuerpos.add(new Point(cuerpos.get(cuerpos.size() - 1).x, cuerpos.get(cuerpos.size() - 1).y));
			Comida.GenerarNueva();
			for (int i = 0; i < cuerpos.size(); i++) {
				if (cuerpos.get(i).x == Comida.x && cuerpos.get(i).y == Comida.y) {
					i = 0;
					Comida.GenerarNueva();

				}
			}
			puntos += Comida.Valor;
			break;
		case CuadroComidaBost.ID:
			cuerpos.add(new Point(cuerpos.get(cuerpos.size() - 1).x, cuerpos.get(cuerpos.size() - 1).y));
			Vivora.puntos += ComidaBoost.Valor + 5;
			ComidaBoost.Bostear();
			Comida.GenerarNueva();

			for (int i = 0; i < cuerpos.size(); i++) {
				if (cuerpos.get(i).x == Comida.x && cuerpos.get(i).y == Comida.y) {
					i = 0;
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
