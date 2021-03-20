package org.pmoo.packdamas;




// TEST DE IA VS IA

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import java.util.Random;

public class Test {
	
	ClaseProvisionalConMetodoDeCerebro IA;
	Movimiento movimiento;
	Tablero tablero;
	Random rNG;

	@Before
	public void setUp() throws Exception {
		
		IA = new ClaseProvisionalConMetodoDeCerebro(0);
		Tablero.getMiTablero().resetear();
		tablero = Tablero.getMiTablero();
		rNG = new Random(System.currentTimeMillis());
	}

	@After
	public void tearDown() throws Exception {
	}

	@org.junit.Test
	public void testObtenerFicha() throws InterruptedException {
		
		
		int diag;
		int rN;
		int rN2;
		int rN3;
		int fila = -1;
		int columna = -1;
		int filaMovido = -1;
		int columnaMovido = -1;
		boolean sigue = true;
	
		
		
		int i = 4;
	if (true) {	                    // Casos RNG
		while (i != 0) {
			
			rN = rNG.nextInt(99);
			rN2 = rNG.nextInt(8);
			rN3 = rNG.nextInt(8);
			
			
		if (tablero.obtenerFicha(rN2, rN3) instanceof Vacio && (rN2 + rN3) % 2 == 1) {	
			if (rN < 45 && rN2 != 0) {tablero.añadirPeonJ1(rN2, rN3);}
			else if (rN < 90 && rN2 != 7) {tablero.añadirPeonJ2(rN2, rN3);}
			else if (rN < 95) {tablero.añadirDamaJ1(rN2, rN3);}
			else {tablero.añadirDamaJ2(rN2, rN3);}
			
			
			i--;}
		}
	}
		
	if (false) {                    // Casos especificos
		tablero.añadirDamaJ1(1, 0);
		tablero.añadirPeonJ2(3, 2);
		tablero.añadirDamaJ2(1, 6);
	//	tablero.añadirPeonJ1(2, 1);
	//	tablero.añadirDamaJ1(3, 2);
	//	tablero.añadirDamaJ2(3, 4);
	//	tablero.añadirPeonJ1(4, 3);
	//	tablero.añadirPeonJ2(5, 6);
	//	tablero.añadirPeonJ1(6, 3);
	//	tablero.añadirPeonJ1(7, 4);
		
		
		if (movimiento != null) {
		    movimiento.mostrarMovimiento();
			} else {
		    	System.out.println("No se puede encadenar");
		    	
		    }
	}
	
	i = 1;
	while (i < 100) {
		System.out.println("");
		i++;
		
		
	}
	movimiento = new Movimiento();
	
	Tablero.getMiTablero().imprimir(filaMovido, columnaMovido);

		while (sigue) {

		sigue = false;
		
		this.calculando();


		
		movimiento = IA.cerebroUno();
		
	
	
		
		System.out.println("");
		
		if (movimiento != null) {	sigue = true;  filaMovido =movimiento.getFilaEmpezado(); columnaMovido = movimiento.getColumnaEmpezado(); 	movimiento.performar();		movimiento.mostrarMovimiento(); Tablero.getMiTablero().imprimir(filaMovido, columnaMovido);
		

		if (movimiento.getFicha() != null) {
		while (movimiento!= null) {
	

		diag = IA.queDiagonalMovio(movimiento);
		fila = movimiento.getFilaAcabado();
		columna = movimiento.getColumnaAcabado();
		
		movimiento = IA.cerebroDos(fila, columna, diag);

		
		if (movimiento != null) {movimiento.performar(); filaMovido =movimiento.getFilaEmpezado(); columnaMovido = movimiento.getColumnaEmpezado();
		this.calculando();
		movimiento.mostrarMovimiento();
		Tablero.getMiTablero().imprimir(filaMovido, columnaMovido);
		}
		
		
		}}
		IA.cambioDeCerebro();
		Thread.sleep(500);
		}}
		
	
		IA.pierde();
		
	}
	
	
	public void calculando() throws InterruptedException {
		
		
		System.out.append('c');
		Thread.sleep(100);
		System.out.append('a');
		Thread.sleep(100);
		System.out.append('l');
		Thread.sleep(100);
		System.out.append('c');
		Thread.sleep(100);
		System.out.append('u');
		Thread.sleep(100);
		System.out.append('l');
		Thread.sleep(100);
		System.out.append('a');
		Thread.sleep(100);
		System.out.append('n');
		Thread.sleep(100);
		System.out.append('d');
		Thread.sleep(100);
		System.out.append('o');
		Thread.sleep(100);
		Thread.sleep(3000);
		System.out.append('.');
		Thread.sleep(1000);
		System.out.append('.');		Thread.sleep(1000);
		System.out.println('.');
		Thread.sleep(1000);
		
	}

}
