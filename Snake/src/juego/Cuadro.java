package juego;

public class Cuadro {
	// Variables
	// ---------------------------------------------------------------
	public static final int LADO = 25;
	private int color;
	private boolean solidez;
	private int[] Pixeles;

	public Sprites Cuadro;
	// ---------------------------------------------------------------

	// gets y sets
	// ---------------------------------------------------------------
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public boolean getSolidez() {
		return solidez;
	}

	public void setSolidez(boolean solidez) {
		this.solidez = solidez;
	}
	// ---------------------------------------------------------------
	// fin get y set

	// metodos
	// ---------------------------------------------------------------

	public Cuadro(boolean solidez, int color, Sprites sprite) {
		this.color = color;
		this.solidez = solidez;
		Pixeles = new int[LADO * LADO];
		this.Cuadro = sprite;

	}

	public int[] Dibujar() {
		for (int i = 0; i < Pixeles.length; i++) {
			Pixeles[i] = color;
		}
		return Pixeles;
	}
	// ---------------------------------------------------------------
}
