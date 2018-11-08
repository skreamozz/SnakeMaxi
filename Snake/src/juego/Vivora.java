package juego;

import java.util.LinkedList;

public class Vivora {

	private LinkedList<Cuerpo> cuerpos = new LinkedList<Cuerpo>();
	private int x, y;
	private int largo = 1;
	private Cola cola = new Cola();
	public static int despx, despy;
	public static int vidas = 3;
	public static int puntos = 0;
	private static int contador = 0;
	public static char direccion = 'd';

	// getters y setters
	// ----------------------------------------------------------------------------------
	public void setx(int x) {
		this.x = x;
	}

	public void sety(int y) {
		this.y = y;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public int getLargo() {
		return largo;
	}
	// ----------------------------------------------------------------------------------

	public Vivora(int x, int y) {
		despx = 1;
		despy = 0;
		this.x = x;
		this.y = y;
		cuerpos.add(new Cuerpo(x, y));
		cola.x = this.x;
		cola.y = this.y;
	}

	public void Dibujar(int[] mapa) {
		mapa[this.y * Mapa.CUADROS_ANCHO + this.x] = CuadroCabeza.ID;

		for (int i = 0; i < cuerpos.size(); i++) {

			mapa[cuerpos.get(i).y * Mapa.CUADROS_ANCHO + cuerpos.get(i).x] = CuadroCuerpo
					.ObtenerID(cuerpos.get(i).Estado);
		}
		mapa[cola.y * Mapa.CUADROS_ANCHO + cola.x] = CuadroCola.ObtenerID(cola.Direccion);
	}

	public void Actualizar(int[] mapa) {
		contador++;
		if (contador >= 10) {
			int x = this.x + despx;
			int y = this.y + despy;

			if (despx > 0 && despy == 0) {
				this.direccion = 'd';
			} else if (despx < 0 && despy == 0) {
				this.direccion = 'i';
			} else if (despx == 0 && despy > 0) {
				this.direccion = 's';
			} else if (despx == 0 && despy < 0) {
				this.direccion = 'w';
			}

			switch (mapa[y * Mapa.CUADROS_ANCHO + x]) {
			case 0xff00ff00:
				// pared abajo
				y = 1;
				break;
			case 0xff00f700:
				// pared Izquierda
				x = 1;

				break;
			case 0xff00f600:
				// pared Arriba
				y = Mapa.CUADROS_ALTO - 2;
				break;
			case 0xff00f500:
				// pared derecha
				x = Mapa.CUADROS_ANCHO - 2;
				break;
			case 0xffffffff:
				break;
			default:
				// en caso de que no sea ninguno de los anteriores es por que esta en colicion.
				if (Colicion(mapa[y * Mapa.CUADROS_ANCHO + x]))
					return;
				break;

			}
			MoverCuerpos();
			this.y = y;
			this.x = x;
			DireccionarCuerpos();
			contador = 0;
		}

	}

	private void ColicionConCuerpo() {
		vidas -= 1;
		if (vidas == 0) {
			Inicio.enMarcha = false;
		}
		x = Mapa.CUADROS_ANCHO / 2;
		y = Mapa.CUADROS_ALTO / 2;
		this.direccion = 'd';
		cuerpos.clear();
		cuerpos.add(new Cuerpo(x - 1, y));
	}

	private boolean Colicion(int val) {
		switch (val) {
		case CuadroCuerpo.IDarr:
			ColicionConCuerpo();
			return true;
		case CuadroCuerpo.IDabj:
			ColicionConCuerpo();
			return true;
		case CuadroCuerpo.IDder:
			ColicionConCuerpo();
			return true;
		case CuadroCuerpo.IDizq:
			ColicionConCuerpo();
			return true;
		case CuadroCuerpo.IDcurba1:
			ColicionConCuerpo();
			return true;
		case CuadroCuerpo.IDcurba2:
			ColicionConCuerpo();
			return true;
		case CuadroCuerpo.IDcurba3:
			ColicionConCuerpo();
			return true;
		case CuadroCuerpo.IDcurba4:
			ColicionConCuerpo();
			return true;
		case CuadroComida.ID:
			cuerpos.add(new Cuerpo(cuerpos.get(cuerpos.size() - 1).x, cuerpos.get(cuerpos.size() - 1).y));
			Comida.GenerarNueva();
			for (int i = 0; i < cuerpos.size(); i++) {
				if (cuerpos.get(i).x == Comida.x && cuerpos.get(i).y == Comida.y) {
					i = 0;
					Comida.GenerarNueva();

				}
			}
			puntos += Comida.Valor;
			return false;
		case CuadroComidaBost.ID:
			cuerpos.add(new Cuerpo(cuerpos.get(cuerpos.size() - 1).x, cuerpos.get(cuerpos.size() - 1).y));
			Vivora.puntos += ComidaBoost.Valor + 5;
			ComidaBoost.Bostear();
			Comida.GenerarNueva();

			for (int i = 0; i < cuerpos.size(); i++) {
				if (cuerpos.get(i).x == Comida.x && cuerpos.get(i).y == Comida.y) {
					i = 0;
					Comida.GenerarNueva();
				}
			}
			return false;
		}
		return false;

	}

	private void MoverCuerpos() {

		cola.x = cuerpos.get(cuerpos.size() - 1).x;
		cola.y = cuerpos.get(cuerpos.size() - 1).y;

		if (cuerpos.size() > 1) {
			for (int i = cuerpos.size() - 1; i > 0; i--) {
				cuerpos.get(i).x = cuerpos.get(i - 1).x;
				cuerpos.get(i).y = cuerpos.get(i - 1).y;
			}

		}
		cuerpos.get(0).x = this.x;
		cuerpos.get(0).y = this.y;

	}

	private void DireccionarCola() {
		if (cuerpos.get(cuerpos.size() - 1).x > cola.x) {
			if (cola.x < Mapa.CUADROS_ANCHO / 2 && cuerpos.get(cuerpos.size() - 1).x > Mapa.CUADROS_ANCHO / 2) {
				cola.Direccion = 'a';
			} else {
				cola.Direccion = 'd';
			}
		} else if (cuerpos.get(cuerpos.size() - 1).x < cola.x) {
			if (cola.x > Mapa.CUADROS_ANCHO / 2 && cuerpos.get(cuerpos.size() - 1).x < Mapa.CUADROS_ANCHO / 2) {
				cola.Direccion = 'd';
			} else {
				cola.Direccion = 'a';
			}
		} else if (cuerpos.get(cuerpos.size() - 1).y > cola.y) {
			if (cola.y < Mapa.CUADROS_ALTO / 2 && cuerpos.get(cuerpos.size() - 1).y > Mapa.CUADROS_ALTO / 2) {
				cola.Direccion = 'w';
			} else {
				cola.Direccion = 's';
			}
		} else if (cuerpos.get(cuerpos.size() - 1).y < cola.y) {
			if (cola.y > Mapa.CUADROS_ALTO / 2 && cuerpos.get(cuerpos.size() - 1).y < Mapa.CUADROS_ALTO / 2) {
				cola.Direccion = 's';
			} else {
				cola.Direccion = 'w';
			}
		}
	}

	private void DireccionarCuerpos() {
		DireccionarCola();
		if (cuerpos.size() > 1) {
			for (int i = cuerpos.size() - 1; i > 0; i--) {
				cuerpos.get(i).Estado = cuerpos.get(i - 1).Estado;
			}
			cuerpos.get(0).Estado = Direccion(this.x, this.y, cuerpos.get(0), cuerpos.get(1));
		} else {
			cuerpos.get(0).Estado = Direccion(this.x, this.y, cuerpos.get(0), this.cola);
		}
	}

	private char Direccion(int CabezaX, int CabezaY, Cuerpo cuerpo, Cuerpo cuerpoAnt) {
		if (cuerpo.x < CabezaX) {
			if (cuerpoAnt.x > cuerpo.x) {
				return 'a';
			} else if (cuerpoAnt.x < cuerpo.x) {
				return 'd';
			} else if (cuerpoAnt.y > cuerpo.y) {
				return 'e';
			} else if (cuerpoAnt.y < cuerpo.y) {
				return 'x';
			}
		} else if (cuerpo.x > CabezaX) {
			if (cuerpoAnt.x < cuerpo.x) {
				return 'd';
			} else if (cuerpoAnt.x > cuerpo.x) {
				return 'a';
			} else if (cuerpoAnt.y > cuerpo.y) {
				return 'q';
			} else if (cuerpoAnt.y < cuerpo.y) {
				return 'z';
			}
		} else if (cuerpo.y > CabezaY) {
			if (cuerpoAnt.y < cuerpo.y) {
				return 's';
			} else if (cuerpoAnt.y > cuerpo.y) {
				return 'w';
			} else if (cuerpoAnt.x > cuerpo.x) {
				return 'x';
			} else if (cuerpoAnt.x < cuerpo.x) {
				return 'z';
			}
		} else if (cuerpo.y < CabezaY) {
			if (cuerpoAnt.y > cuerpo.y) {
				return 'w';
			} else if (cuerpoAnt.y < cuerpo.y) {
				return 's';
			} else if (cuerpoAnt.x > cuerpo.x) {
				return 'e';
			} else if (cuerpoAnt.x < cuerpo.x) {
				return 'q';
			}
		}
		return 'd';
	}

	private char Direccion(int CabezaX, int CabezaY, Cuerpo cuerpo, Cola cuerpoAnt) {
		if (cuerpo.x < CabezaX) {
			if (cuerpoAnt.x > cuerpo.x) {
				return 'a';
			} else if (cuerpoAnt.x < cuerpo.x) {
				return 'd';
			} else if (cuerpoAnt.y > cuerpo.y) {
				return 'e';
			} else if (cuerpoAnt.y < cuerpo.y) {
				return 'x';
			}
		} else if (cuerpo.x > CabezaX) {
			if (cuerpoAnt.x < cuerpo.x) {
				return 'd';
			} else if (cuerpoAnt.x > cuerpo.x) {
				return 'a';
			} else if (cuerpoAnt.y > cuerpo.y) {
				return 'q';
			} else if (cuerpoAnt.y < cuerpo.y) {
				return 'z';
			}
		} else if (cuerpo.y > CabezaY) {
			if (cuerpoAnt.y < cuerpo.y) {
				return 's';
			} else if (cuerpoAnt.y > cuerpo.y) {
				return 'w';
			} else if (cuerpoAnt.x > cuerpo.x) {
				return 'x';
			} else if (cuerpoAnt.x < cuerpo.x) {
				return 'z';
			}
		} else if (cuerpo.y < CabezaY) {
			if (cuerpoAnt.y > cuerpo.y) {
				return 'w';
			} else if (cuerpoAnt.y < cuerpo.y) {
				return 's';
			} else if (cuerpoAnt.x > cuerpo.x) {
				return 'e';
			} else if (cuerpoAnt.x < cuerpo.x) {
				return 'q';
			}
		}
		return 'd';
	}
}
