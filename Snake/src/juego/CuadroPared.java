package juego;

public class CuadroPared extends Cuadro {

	private static final int color = 0x4f00bb;
	private static final boolean solidez = true;
	public static final int ID = 1;
	public static Sprites A;
	public static Sprites AB;
	public static Sprites I;
	public static Sprites D;
	public static Sprites EsquinaI;
	public static Sprites EsquinaD;
	public static Sprites EsquinaII;
	public static Sprites EsquinaID;

	public int getID() {
		return ID;
	}

	public CuadroPared() {
		super(solidez, color, new Sprites(LADO, 3, 0, HojaSprites.Hoja1));
		A = new Sprites(LADO, 3, 3, HojaSprites.Hoja1);
		D = new Sprites(LADO, 3, 2, HojaSprites.Hoja1);
		AB = new Sprites(LADO, 3, 1, HojaSprites.Hoja1);
		I = new Sprites(LADO, 3, 0, HojaSprites.Hoja1);
		EsquinaI = new Sprites(LADO, 3, 7, HojaSprites.Hoja1);
		EsquinaD = new Sprites(LADO, 3, 4, HojaSprites.Hoja1);
		EsquinaID = new Sprites(LADO, 3, 5, HojaSprites.Hoja1);
		EsquinaII = new Sprites(LADO, 3, 6, HojaSprites.Hoja1);
	}

}
