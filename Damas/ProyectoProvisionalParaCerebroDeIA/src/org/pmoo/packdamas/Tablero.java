package org.pmoo.packdamas;

public class Tablero {
	
	private static Tablero miTablero;
	private Ficha[][] tablero;
	
	private Tablero () {
		
		this.tablero = new Ficha[8][8];
		
	}
	
	public static Tablero getMiTablero() {
		
		if (Tablero.miTablero == null) {Tablero.miTablero = new Tablero();} return Tablero.miTablero;
	
	}
	
	public void a�adirPeonJ1 (int pFila, int pColumna) {
		
		this.tablero[pFila][pColumna] = new PeonDeJugadorUno(pFila, pColumna);
		
	}
	
	public void a�adirPeonJ2 (int pFila, int pColumna) {
		
		this.tablero[pFila][pColumna] = new PeonDeJugadorDos(pFila, pColumna);
		
	}
	
	public void a�adirDamaJ1 (int pFila, int pColumna) {
		
		this.tablero[pFila][pColumna] = new DamaDeJugadorUno(pFila, pColumna);
		
	}
	
	public void a�adirDamaJ2 (int pFila, int pColumna) {
		
		this.tablero[pFila][pColumna] = new DamaDeJugadorDos(pFila, pColumna);
		
	}
	
	
	
	public void a�adirFicha (Ficha pFicha, int pFila, int pColumna) {
		
		this.tablero[pFila][pColumna] = pFicha;
		
	}
	
	public void a�adirVacio (int pFila, int pColumna) {
		
		this.tablero[pFila][pColumna] = new Vacio(pFila, pColumna);
		
		
	}
	
	public void resetear () {
		
		int i;
		int j;
		
		i = 0;
		
		while (i< 8) {
			
			j = 0;
			
			while (j<8) {
				
				this.tablero[i][j] = new Vacio(i,j);
				j++;
			}
			i++;
		}
		
	}
	
	public Ficha obtenerFicha(int pFila, int pColumna) {
		
		return this.tablero[pFila][pColumna];
	}
	
	public void imprimir (int pFila, int pColumna) {
		
		int i;
		int j;
		
		i = 0;
		
		while (i < 8) {
			
			j = 0;
			
			while (j < 8) {
				
				if (i != pFila || j != pColumna) {
				this.tablero[i][j].imprimir();
				System.out.append(' ');
				} else {
				
				System.out.append('x'); System.out.append(' ');
					
				}
				
				j++;
				
			}
			
			i++;
			System.out.println(" ");
		}
	}
	
	
	}


