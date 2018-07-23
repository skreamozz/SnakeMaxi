package juego;

public class CuadroCabeza extends Cuadro {

	private static final int color = 0xff00ff00;
	private static final boolean solidez = true;
	private int x, y;
	public static Sprites Derecha;
	public static Sprites Izquierda;
	public static Sprites Arriba;
	public static Sprites Abajo;

	public static final int ID = 2;

	// gett sett
	// ---------------------------------------------------------------
	public int gety() {
		return y;
	}

	public int getx() {
		return x;
	}

	public void sety(int y) {
		this.y = y;
	}

	public void setx(int x) {
		this.x = x;
	}
	// ---------------------------------------------------------------
	// fin get set

	public CuadroCabeza() {
		super(solidez, color, new Sprites(LADO, 0, 0, HojaSprites.Hoja1));
		Derecha = new Sprites(LADO, 0, 0, HojaSprites.Hoja1);
		Abajo = new Sprites(LADO, 0, 1, HojaSprites.Hoja1);
		Izquierda = new Sprites(LADO, 0, 2, HojaSprites.Hoja1);
		Arriba = new Sprites(LADO, 0, 3, HojaSprites.Hoja1);

	}
}
