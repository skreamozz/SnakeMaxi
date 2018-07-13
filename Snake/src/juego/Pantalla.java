package juego;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import componentes.Controles;

public class Pantalla extends Canvas {
	public static final int CUADROS_ALTO = 22, CUADROS_ANCHO = 18;
	private Dimension dim = new Dimension();
	private int[] cuadros;
	private BufferedImage imagen;
	private int[] Pixeles;
	private Mapa mapa;
	private Tablero tablero;
	private Cuadro[] ColeccionCuadros = { new CuadroVacio(), new CuadroPared(), new CuadroCabeza(), new CuadroCuerpo(),
			new CuadroComida(), new CuadroCola(), new CuadroComidaBost() };

	private Controles c = new Controles();

	public Pantalla(Dimension dim) {
		this.addKeyListener(c);
		this.dim = dim;
		imagen = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
		Pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();
		cuadros = new int[CUADROS_ALTO * CUADROS_ANCHO];
		this.setSize(dim);
		mapa = new Mapa(new Posicion(1, 8));
		tablero = new Tablero(new Posicion(1, 1));

	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		Dibujar();
		Graphics s = this.imagen.getGraphics();
		s.setColor(new Color(0xf4f4f4));
		s.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 30));
		s.drawString("Vidas: " + Vivora.vidas, 2 * Cuadro.LADO, 2 * Cuadro.LADO);
		s.drawString("Puntos: " + Vivora.puntos, 2 * Cuadro.LADO, 3 * Cuadro.LADO);
		if (Inicio.enPausa) {

			s.drawString("Pause", ((Mapa.CUADROS_ANCHO / 2) * Cuadro.LADO) - 25,
					((8 + Mapa.CUADROS_ALTO / 2) * Cuadro.LADO) + 20);
		}
		g.drawImage(imagen, 0, 0, this);

	}

	public void Limpiar() {
		for (int y = 0; y < Pantalla.CUADROS_ALTO; y++) {
			for (int x = 0; x < Pantalla.CUADROS_ANCHO; x++) {
				cuadros[y * CUADROS_ANCHO + x] = 0;
			}
		}

	}

	public void Dibujar() {
		Limpiar();
		mapa.Dibujar(cuadros);
		tablero.Dibujar(cuadros);
		for (int y = 0; y < Pantalla.CUADROS_ALTO; y++) {
			for (int x = 0; x < Pantalla.CUADROS_ANCHO; x++) {
				switch (cuadros[y * this.CUADROS_ANCHO + x]) {
				case 0:
					DibujarEnPos(x, y, this.ColeccionCuadros[0]);
					break;
				case 1:
					DibujarEnPos(x, y, this.ColeccionCuadros[1]);
					break;
				case 2:
					DibujarEnPos(x, y, this.ColeccionCuadros[2]);
					break;
				case 3:
					DibujarEnPos(x, y, this.ColeccionCuadros[3]);
					break;
				case 4:
					DibujarEnPos(x, y, this.ColeccionCuadros[4]);
					break;
				case 5:
					DibujarEnPos(x, y, this.ColeccionCuadros[5]);
					break;
				case 6:
					DibujarEnPos(x, y, this.ColeccionCuadros[6]);
					break;
				}

			}
		}
		this.repaint();
	}

	public void Actualizar() {
		mapa.Actualizar();

	}

	public void DibujarEnPos(int despx, int despy, Cuadro cuadro) {
		for (int y = 0; y < cuadro.LADO; y++) {
			for (int x = 0; x < cuadro.LADO; x++) {
				this.Pixeles[((Posiciones(despx, despy)) + (y * dim.width)) + x] = cuadro.Cuadro.getPixel(x, y);
			}
		}
	}

	public int Posiciones(int x, int y) {
		x = x * Cuadro.LADO;
		y = y * Cuadro.LADO;

		return y * dim.width + x;

	}

}
