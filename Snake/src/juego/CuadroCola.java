package juego;

public class CuadroCola extends Cuadro {
	public static final int ID = 5;

	public CuadroCola() {
		super(true, 0xff0000ff, new Sprites(LADO, 2, 0, HojaSprites.Hoja1));
	}

}
