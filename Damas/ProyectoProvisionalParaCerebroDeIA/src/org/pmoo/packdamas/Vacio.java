package org.pmoo.packdamas;

public class Vacio extends Ficha {
	
	private char valorImpreso = '-';
	
	public Vacio (int pFila, int pColumna) {super(pFila, pColumna);}
	
	public void imprimir() {
		
		System.out.append(this.valorImpreso);
		
		
	}

}
