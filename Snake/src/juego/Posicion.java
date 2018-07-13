package juego;

public class Posicion {

	private int x;
	private int y;
	private int yReal;
	private int xReal;

	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;

		this.xReal = PosicionRealX(x);
		this.yReal = PosicionRealY(y);

	}

	// getter setter
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getYReal() {
		return yReal;
	}

	public int getXreal() {
		return xReal;
	}

	// fin Getter setter
	public static int PosicionRealX(int x) {
		return x * Cuadro.LADO;
	}

	public static int PosicionRealY(int y) {
		return y * Cuadro.LADO;
	}
}
