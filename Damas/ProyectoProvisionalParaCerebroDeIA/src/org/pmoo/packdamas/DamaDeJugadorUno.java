package org.pmoo.packdamas;

public class DamaDeJugadorUno extends Ficha{
	
    private char valorImpreso = 'A';
	
	public DamaDeJugadorUno(int pFila, int pColumna) {super(pFila, pColumna);}

	
	public void imprimir() {
		
		System.out.append(this.valorImpreso);
		
		
	}
}

