package juego;

public class CuadroVacio extends Cuadro {

	private static final boolean solidez = false;
	private static final int color = 0;
	public static final int ID = 0;

	public int getID() {
		return ID;
	}

	public CuadroVacio() {
		super(solidez, color, new Sprites(LADO, 6, 0, HojaSprites.Hoja1));
		// TODO Auto-generated constructor stub
	}

}
