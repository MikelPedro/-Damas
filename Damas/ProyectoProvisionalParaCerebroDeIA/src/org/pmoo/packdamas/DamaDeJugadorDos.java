package org.pmoo.packdamas;

public class DamaDeJugadorDos extends Ficha {

    private char valorImpreso = 'B';
	
	public DamaDeJugadorDos(int pFila, int pColumna) {super(pFila, pColumna);}

	public void imprimir() {
		
		System.out.append(this.valorImpreso);
		
		
	}

}

