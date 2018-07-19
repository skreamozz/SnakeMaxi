package componentes;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import juego.Mapa;

public class CargarMapa {
	private final int[] Pixeles;
	private int ancho, alto;

	public static CargarMapa mapa1 = new CargarMapa("/mapas/Mapa1.png", Mapa.CUADROS_ANCHO, Mapa.CUADROS_ALTO);

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

	public int[] obtenerPixeles() {
		return this.Pixeles;
	}

}
