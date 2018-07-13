package juego;

public class CuadroComida extends Cuadro {

	public static final int ID = 4;

	public CuadroComida() {
		super(true, 0x445566, new Sprites(LADO, 4, 0, HojaSprites.Hoja1));
	}

	public void SetSprite(Sprites sprite) {
		this.Cuadro = sprite;
	}

}
