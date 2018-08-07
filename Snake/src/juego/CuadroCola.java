package juego;

public class CuadroCola extends Cuadro {
	public static final int ID = 5;
	public static final int IDderecha = 10, IDizquierda = 11, IDarriba = 12, IDabajo = 13;
	public static Sprites Der, izq, arr, abj;

	public CuadroCola() {
		super(true, 0xff0000ff, new Sprites(LADO, 2, 0, HojaSprites.Hoja1));
		Der = new Sprites(LADO, 2, 2, HojaSprites.Hoja1);
		izq = new Sprites(LADO, 2, 1, HojaSprites.Hoja1);
		arr = new Sprites(LADO, 2, 0, HojaSprites.Hoja1);
		abj = new Sprites(LADO, 2, 3, HojaSprites.Hoja1);
	}

	public static int ObtenerID(char Direccion) {
		switch (Direccion) {
		case 'd':
			return IDderecha;
		case 'a':
			return IDizquierda;
		case 's':
			return IDabajo;
		case 'w':
			return IDarriba;

		}
		return IDderecha;

	}

}
