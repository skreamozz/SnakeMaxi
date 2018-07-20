package juego;

import componentes.CargarMapa;

public class Mapa {

	public static final int CUADROS_ALTO = 13, CUADROS_ANCHO = 16;
	public Posicion pos;
	public Vivora vivora = new Vivora(5, 5);
	private int[] mapa;
	private int[] objetos = new int[this.CUADROS_ALTO * this.CUADROS_ANCHO];

	public Mapa(Posicion pos, CargarMapa mapa) {
		this.pos = pos;
		this.mapa = mapa.obtenerPixeles().clone();
		objetos = this.mapa.clone();
		new Comida();
	}

	public void Dibujar(int[] cuad) {
		int PosReal = this.pos.getY() * Pantalla.CUADROS_ANCHO + this.pos.getX();
		Limpiar();
		if (!Comida.EsBoost) {
			objetos[Comida.y * Mapa.CUADROS_ANCHO + Comida.x] = CuadroComida.ID;
		} else {
			objetos[ComidaBoost.y * Mapa.CUADROS_ANCHO + ComidaBoost.x] = CuadroComidaBost.ID;
		}
		vivora.Dibujar(objetos);
		for (int y = 0; y < this.CUADROS_ALTO; y++) {
			for (int x = 0; x < this.CUADROS_ANCHO; x++) {
				cuad[(PosReal + (y * Pantalla.CUADROS_ANCHO)) + x] = objetos[y * CUADROS_ANCHO + x];
			}
		}

	}

	public void Limpiar() {
		objetos = this.mapa.clone();
	}

	public void Actualizar() {
		vivora.Actualizar(objetos);
	}
}
