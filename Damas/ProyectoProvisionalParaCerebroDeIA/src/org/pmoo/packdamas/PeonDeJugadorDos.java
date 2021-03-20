package org.pmoo.packdamas;

public class PeonDeJugadorDos extends Ficha {
	
    private char valorImpreso = 'b';
	
	public PeonDeJugadorDos (int pFila, int pColumna) {super(pFila, pColumna);}


	public void imprimir() {
		
		System.out.append(this.valorImpreso);
		
		
	}
}
