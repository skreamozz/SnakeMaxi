package juego;

import componentes.CargarMapa;

public class Mapa {

	public static final int CUADROS_ALTO = 13, CUADROS_ANCHO = 16;
	public static Cuadro[] cuadros = new Cuadro[CUADROS_ALTO * CUADROS_ANCHO];
	public Posicion pos;
	CargarMapa Cargar;
	Vivora vivora = new Vivora(5, 5);
	private int[] objetos = new int[this.CUADROS_ALTO * this.CUADROS_ANCHO];
	private int[] Mapa1 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

	public Mapa(Posicion pos) {
		this.pos = pos;
		Cargar = new CargarMapa("/mapas/Mapa1.png", 16, 13);
		objetos = Cargar.Pixeles.clone();
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
		for (int i = 0; i < objetos.length; i++) {
			objetos[i] = 0;
		}
		objetos = Cargar.Pixeles.clone();
	}

	public void Actualizar() {
		vivora.Actualizar(objetos);
	}
}
