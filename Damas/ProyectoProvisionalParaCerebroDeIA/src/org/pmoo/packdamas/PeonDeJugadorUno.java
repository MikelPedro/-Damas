package org.pmoo.packdamas;

public class PeonDeJugadorUno extends Ficha{
	
	
    private char valorImpreso = 'a';
	
	public PeonDeJugadorUno (int pFila, int pColumna) {super(pFila, pColumna);}

	
	public void imprimir() {
		
		System.out.append(this.valorImpreso);
		
		
	}
}
