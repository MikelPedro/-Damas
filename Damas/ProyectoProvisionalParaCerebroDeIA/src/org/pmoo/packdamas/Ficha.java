package org.pmoo.packdamas;

public class Ficha {
	
	private char valorImpreso ='W';
	private int fila;
	private int columna;
	
	public Ficha (int pFila, int pColumna) {this.fila = pFila; this.columna = pColumna;}
	
	public void imprimir() {
		
		System.out.append(this.valorImpreso);
		
		
	}
	
	public void colocarse (int pFila, int pColumna) {
		
		this.fila = pFila;
		this.columna = pColumna;
		Tablero.getMiTablero().añadirFicha(this, pFila, pColumna);
		
		if (this instanceof PeonDeJugadorUno  && pFila == 0) {Tablero.getMiTablero().añadirDamaJ1(pFila, pColumna);}
		if (this instanceof PeonDeJugadorDos  && pFila == 7) {Tablero.getMiTablero().añadirDamaJ2(pFila, pColumna);}

		
	}
	
	public void borrarse () {
		
		
		Tablero.getMiTablero().añadirVacio(this.fila, this.columna);

	}
	
	public int getFila() {return this.fila;}
	
	public int getColumna() {return this.columna;}
	
	
}
