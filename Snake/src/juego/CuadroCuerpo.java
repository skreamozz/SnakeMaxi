package juego;

public class CuadroCuerpo extends Cuadro {

	private static final int color = 0xffff0000;
	private static final boolean solidez = true;
	private int x, y;
	public static final int ID = 3;
	public static final int IDarr = 21, IDabj = 22, IDder = 23, IDizq = 24, IDcurba1 = 25, IDcurba2 = 26, IDcurba3 = 27,
			IDcurba4 = 28;
	public static Sprites arr, abj, der, izq, curba1, curba2, curba3, curba4;

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
		arr = new Sprites(LADO, 1, 0, HojaSprites.Hoja1);
		der = new Sprites(LADO, 1, 1, HojaSprites.Hoja1);
		abj = new Sprites(LADO, 1, 2, HojaSprites.Hoja1);
		izq = new Sprites(LADO, 1, 3, HojaSprites.Hoja1);
		curba2 = new Sprites(LADO, 1, 4, HojaSprites.Hoja1);
		curba3 = new Sprites(LADO, 1, 5, HojaSprites.Hoja1);
		curba4 = new Sprites(LADO, 1, 6, HojaSprites.Hoja1);
		curba1 = new Sprites(LADO, 1, 7, HojaSprites.Hoja1);
	}

	public static int ObtenerID(char Estado) {
		switch (Estado) {
		case 'e':
			return IDcurba1;
		case 'q':
			return IDcurba2;
		case 'z':
			return IDcurba3;
		case 'x':
			return IDcurba4;
		case 'a':
			return IDizq;
		case 'd':
			return IDder;
		case 'w':
			return IDarr;
		case 's':
			return IDabj;

		}
		return IDder;
	}

}
