package juego;

public class Tablero {
	public static final int CUADROS_ANCHO = 16, CUADROS_ALTO = 6;
	private int[] cuadros = new int[CUADROS_ANCHO * CUADROS_ALTO];
	public Posicion Pos;

	public Tablero(Posicion pos) {
		for (int y = 0; y < CUADROS_ALTO; y++) {
			for (int x = 0; x < CUADROS_ANCHO; x++) {
				cuadros[y * CUADROS_ANCHO + x] = 1;
			}
		}

		this.Pos = pos;

	}

	public void Dibujar(int[] cuadro) {
		int PosReal = this.Pos.getY() * Pantalla.CUADROS_ANCHO + this.Pos.getX();
		for (int y = 0; y < this.CUADROS_ALTO; y++) {
			for (int x = 0; x < this.CUADROS_ANCHO; x++) {
				cuadro[(PosReal + (y * Pantalla.CUADROS_ANCHO)) + x] = this.cuadros[y * CUADROS_ANCHO + x];
			}
		}

	}

}
