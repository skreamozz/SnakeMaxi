package juego;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import componentes.CargarMapa;
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

	private Controles control = new Controles();

	public Pantalla(Dimension dim) {
		this.addKeyListener(control);
		this.dim = dim;
		imagen = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
		Pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();
		cuadros = new int[CUADROS_ALTO * CUADROS_ANCHO];
		this.setSize(dim);
		mapa = new Mapa(new Posicion(1, 8), CargarMapa.mapa1);
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
		for (int i = 0; i < this.Pixeles.length; i++) {
			Pixeles[i] = 0;
		}

	}

	public void Dibujar() {
		mapa.Dibujar(cuadros);
		tablero.Dibujar(cuadros);
		Limpiar();
		for (int y = 0; y < Pantalla.CUADROS_ALTO; y++) {
			for (int x = 0; x < Pantalla.CUADROS_ANCHO; x++) {
				switch (cuadros[y * this.CUADROS_ANCHO + x]) {
				case 0:
					// cuadro vacio
					DibujarEnPos(x, y, this.ColeccionCuadros[0]);
					break;
				case 0xff00ff00:
					// pared de abajo
					this.ColeccionCuadros[1].setSprite(CuadroPared.AB);
					this.DibujarEnPos(x, y, this.ColeccionCuadros[1]);
					break;
				case 0xff00f700:
					// pared derecha
					this.ColeccionCuadros[1].setSprite(CuadroPared.D);
					this.DibujarEnPos(x, y, this.ColeccionCuadros[1]);
					break;

				case 0xff00f600:
					// pared Arriba
					this.ColeccionCuadros[1].setSprite(CuadroPared.A);
					this.DibujarEnPos(x, y, this.ColeccionCuadros[1]);

					break;
				case 0xff00f500:
					// pared Izquierda
					this.ColeccionCuadros[1].setSprite(CuadroPared.I);
					this.DibujarEnPos(x, y, this.ColeccionCuadros[1]);
					break;
				case 0xff00f400:
					// Esquina inferior derecha
					this.ColeccionCuadros[1].setSprite(CuadroPared.EsquinaID);
					this.DibujarEnPos(x, y, this.ColeccionCuadros[1]);
					break;
				case 0xff00f300:
					// esquina inferior izquierda
					this.ColeccionCuadros[1].setSprite(CuadroPared.EsquinaII);
					this.DibujarEnPos(x, y, this.ColeccionCuadros[1]);
					break;
				case 0xff00f200:
					// esquina derecha
					this.ColeccionCuadros[1].setSprite(CuadroPared.EsquinaD);
					this.DibujarEnPos(x, y, this.ColeccionCuadros[1]);
					break;
				case 0xff00f100:
					// esquina izquierda
					this.ColeccionCuadros[1].setSprite(CuadroPared.EsquinaI);
					this.DibujarEnPos(x, y, this.ColeccionCuadros[1]);
					break;
				case CuadroCabeza.ID:
					switch (Vivora.direccion) {
					case 'd':
						// direccion derecha
						ColeccionCuadros[CuadroCabeza.ID].setSprite(CuadroCabeza.Derecha);
						break;
					case 'i':
						// direccion izquierda
						ColeccionCuadros[CuadroCabeza.ID].setSprite(CuadroCabeza.Izquierda);
						break;
					case 's':
						// direccion abajo
						ColeccionCuadros[CuadroCabeza.ID].setSprite(CuadroCabeza.Abajo);
						break;
					case 'w':
						// direccion arriba
						ColeccionCuadros[CuadroCabeza.ID].setSprite(CuadroCabeza.Arriba);
						break;
					}
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroCabeza.ID]);
					break;
				case CuadroCuerpo.IDarr:
					// cuerpos
					this.ColeccionCuadros[CuadroCuerpo.ID].setSprite(CuadroCuerpo.arr);
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroCuerpo.ID]);
					break;
				case CuadroCuerpo.IDabj:
					// cuerpos
					this.ColeccionCuadros[CuadroCuerpo.ID].setSprite(CuadroCuerpo.abj);
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroCuerpo.ID]);
					break;
				case CuadroCuerpo.IDder:
					// cuerpos
					this.ColeccionCuadros[CuadroCuerpo.ID].setSprite(CuadroCuerpo.der);
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroCuerpo.ID]);
					break;
				case CuadroCuerpo.IDizq:
					// cuerpos
					this.ColeccionCuadros[CuadroCuerpo.ID].setSprite(CuadroCuerpo.izq);
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroCuerpo.ID]);
					break;
				case CuadroCuerpo.IDcurba1:
					// cuerpos
					this.ColeccionCuadros[CuadroCuerpo.ID].setSprite(CuadroCuerpo.curba1);
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroCuerpo.ID]);
					break;
				case CuadroCuerpo.IDcurba2:
					// cuerpos
					this.ColeccionCuadros[CuadroCuerpo.ID].setSprite(CuadroCuerpo.curba2);
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroCuerpo.ID]);
					break;
				case CuadroCuerpo.IDcurba3:
					// cuerpos
					this.ColeccionCuadros[CuadroCuerpo.ID].setSprite(CuadroCuerpo.curba3);
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroCuerpo.ID]);
					break;
				case CuadroCuerpo.IDcurba4:
					// cuerpos
					this.ColeccionCuadros[CuadroCuerpo.ID].setSprite(CuadroCuerpo.curba4);
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroCuerpo.ID]);
					break;
				case CuadroComida.ID:
					// comida
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroComida.ID]);
					break;
				// empieza la cola
				case CuadroCola.IDabajo:
					// cola
					this.ColeccionCuadros[CuadroCola.ID].setSprite(CuadroCola.abj);
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroCola.ID]);
					break;
				case CuadroCola.IDarriba:
					// cola
					this.ColeccionCuadros[CuadroCola.ID].setSprite(CuadroCola.arr);
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroCola.ID]);
					break;
				case CuadroCola.IDizquierda:
					// cola
					this.ColeccionCuadros[CuadroCola.ID].setSprite(CuadroCola.izq);
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroCola.ID]);
					break;
				case CuadroCola.IDderecha:
					// cola
					this.ColeccionCuadros[CuadroCola.ID].setSprite(CuadroCola.Der);
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroCola.ID]);
					break;
				// termina la cola
				case CuadroComidaBost.ID:
					DibujarEnPos(x, y, this.ColeccionCuadros[CuadroComidaBost.ID]);
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
		for (int y = 0; y < Cuadro.LADO; y++) {
			for (int x = 0; x < Cuadro.LADO; x++) {
				int color = cuadro.Cuadro.getPixel(x, y);
				if (color != 0xffff00ff) {
					this.Pixeles[((Posiciones(despx, despy)) + (y * dim.width)) + x] = color;
				}
			}
		}
	}

	public int Posiciones(int x, int y) {
		x = x * Cuadro.LADO;
		y = y * Cuadro.LADO;

		return y * dim.width + x;

	}

}
