package juego;

public class CuadroPared extends Cuadro {

	private static final int color = 0x4f00bb;
	private static final boolean solidez = true;
	public static final int ID = 1;
	public static Sprites A;
	public static Sprites AB;
	public static Sprites I;
	public static Sprites D;

	public int getID() {
		return ID;
	}

	public CuadroPared() {
		super(solidez, color, new Sprites(LADO, 3, 0, HojaSprites.Hoja1));
		A = new Sprites(LADO, 3, 3, HojaSprites.Hoja1);
		D = new Sprites(LADO, 3, 2, HojaSprites.Hoja1);
		AB = new Sprites(LADO, 3, 1, HojaSprites.Hoja1);
		I = new Sprites(LADO, 3, 0, HojaSprites.Hoja1);

	}

}
