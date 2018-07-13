package juego;

import java.util.Random;

public class Comida {
	public static int x, y;
	public static Random rand = new Random();
	public static final int Valor = 15;
	public static boolean EsBoost;

	public Comida() {

		x = rand.nextInt(Mapa.CUADROS_ANCHO - 2) + 1;
		y = rand.nextInt(Mapa.CUADROS_ALTO - 2) + 1;

	}

	public static void GenerarNueva() {
		int random = rand.nextInt(500);
		if (!(random > 200 && random < 300)) {
			EsBoost = false;
			x = rand.nextInt(Mapa.CUADROS_ANCHO - 2) + 1;
			y = rand.nextInt(Mapa.CUADROS_ALTO - 2) + 1;
		} else {
			EsBoost = true;
			ComidaBoost.x = rand.nextInt(Mapa.CUADROS_ANCHO - 2) + 1;
			ComidaBoost.y = rand.nextInt(Mapa.CUADROS_ALTO - 2) + 1;
		}
	}

}
