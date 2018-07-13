package juego;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class HojaSprites {

	private final int ancho, alto;

	public final int[] pixeles;

	public final static HojaSprites Hoja1 = new HojaSprites("/hojas/Hoja1.png", 320, 320);

	public HojaSprites(String ruta, int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;

		pixeles = new int[this.ancho * this.alto];

		BufferedImage imagen;

		try {
			imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getAncho() {
		return ancho;
	}
}
