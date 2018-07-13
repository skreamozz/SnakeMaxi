package juego;

public class CuadroPared extends Cuadro {

	private static final int color = 0x4f00bb;
	private static final boolean solidez = true;
	public static final int ID = 1;

	public int getID() {
		return ID;
	}

	public CuadroPared() {
		super(solidez, color, new Sprites(LADO, 3, 0, HojaSprites.Hoja1));

	}

}
