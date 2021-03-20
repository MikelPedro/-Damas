package org.pmoo.packdamas;

public class Movimiento {
	
	
	private int filaAcabado;
	private int columnaAcabado;
	private int filaEmpezado;
	private int columnaEmpezado;
	private Ficha fichaComida; 
	private boolean seHizoDama;
	
	public Movimiento (int pFilaAcabado, int pColumnaAcabado, int pFilaEmpezado, int pColumnaEmpezado, Ficha pFichaComida, boolean pSeHizoDama) {
		
		this.filaAcabado = pFilaAcabado;
		this.columnaAcabado = pColumnaAcabado;
		this.filaEmpezado = pFilaEmpezado;
		this.columnaEmpezado = pColumnaEmpezado;
		this.fichaComida = pFichaComida;
		this.seHizoDama = pSeHizoDama;
		
		
	}
	
	
	private void sumar () {
		this.filaAcabado = this.filaAcabado + 1;
		this.columnaAcabado = this.columnaAcabado + 1;
		this.filaEmpezado = this.filaEmpezado + 1;
		this.columnaEmpezado = this.columnaEmpezado + 1;
		
	}
	
	private void restar () {
		this.filaAcabado = this.filaAcabado - 1;
		this.columnaAcabado = this.columnaAcabado - 1;
		this.filaEmpezado = this.filaEmpezado - 1;
		this.columnaEmpezado = this.columnaEmpezado - 1;
		
	}
	
	public void mostrarMovimiento () {
		

		this.sumar();

		System.out.println("He decidido que la jugada es:");
		System.out.println("Mover la ficha de la posición "+this.filaEmpezado+"," +this.columnaEmpezado+" a la posición "+this.filaAcabado+ "," +this.columnaAcabado);

		if (this.fichaComida == null) {
			
			System.out.append("No he comido ninguna ficha por el camino");
			
			
		} else {System.out.append("He comido la ficha "); this.fichaComida.imprimir();}
		

		if (seHizoDama) {
			
			System.out.append(" y mi ficha se hizo dama");
		}

		
		this.restar();
		
		System.out.println("");
	}
	
	
	public void performar() {
		


		
		Tablero.getMiTablero().obtenerFicha(this.filaEmpezado, this.columnaEmpezado).colocarse(this.filaAcabado, this.columnaAcabado);
		
		
		
		if(this.fichaComida != null) {
		this.fichaComida.borrarse();}
		
		Tablero.getMiTablero().añadirVacio(this.filaEmpezado, this.columnaEmpezado);
		
	}
	
	
	public int getFilaAcabado() {return this.filaAcabado;}
	
	public int getFilaEmpezado() {return this.filaEmpezado;}
	public int getColumnaEmpezado() {return this.columnaEmpezado;}
	public int getColumnaAcabado() {return this.columnaAcabado;}
	
	public Movimiento () {this.filaEmpezado = 0;}

	public Ficha getFicha () {return this.fichaComida;}

}
