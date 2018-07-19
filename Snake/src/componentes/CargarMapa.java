package componentes;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CargarMapa {
	public final int[] Pixeles;
	final int ancho, alto;

	public CargarMapa(String ruta, int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
		Pixeles = new int[ancho * alto];
		BufferedImage imagen;

		try {
			imagen = ImageIO.read(CargarMapa.class.getResource(ruta));
			imagen.getRGB(0, 0, ancho, alto, Pixeles, 0, ancho);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
