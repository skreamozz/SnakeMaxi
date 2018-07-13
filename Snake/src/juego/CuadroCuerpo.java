package juego;

public class CuadroCuerpo extends Cuadro {

	private static final int color = 0xff0000;
	private static final boolean solidez = true;
	private int x, y;
	public static final int ID = 3;

	// gett
	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}
	// set

	public CuadroCuerpo() {
		super(solidez, color, new Sprites(LADO, 1, 0, HojaSprites.Hoja1));

	}

}
