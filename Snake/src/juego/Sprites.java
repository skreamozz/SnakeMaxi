package juego;

public final class Sprites {

	private final int lado;
	private final int x, y;

	public int[] pixeles;

	private HojaSprites hoja;

	public Sprites(final int lado, final int columna, final int fila, HojaSprites hoja) {

		this.lado = lado;
		pixeles = new int[lado * lado];

		this.x = columna * lado;
		this.y = fila * lado;

		this.hoja = hoja;

		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[y * lado + x] = hoja.pixeles[(y + this.y) * hoja.getAncho() + (x + this.x)];
			}

		}

	}

	public int getPixel(int x, int y) {

		return pixeles[y * lado + x];

	}

}
