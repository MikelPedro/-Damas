package org.pmoo.packdamas;

public class ClaseProvisionalConMetodoDeCerebroJ1 extends ClaseProvisionalConMetodoDeCerebro{

	
	
	public ClaseProvisionalConMetodoDeCerebroJ1 () {super(0);}

	
	public boolean esFichaAliada(Ficha pFicha) {
		
		
			if (pFicha instanceof PeonDeJugadorUno || pFicha instanceof DamaDeJugadorUno) {
				return true;
			} else {return false;}
		
			
		}
	
	public boolean esFichaEnemiga(Ficha pFicha) {
		
		if (!this.esFichaAliada(pFicha) && !this.esVacio(pFicha)) {return true;} else {return false;}
	}
	
	// esVacio() es heredado
	
	
	public boolean esPeonAliado(Ficha pFicha) {	
		
			if (pFicha instanceof PeonDeJugadorUno) {return true;} else {return false;}
	 
	}
	
	public boolean esDamaAliada(Ficha pFicha) {
		
	
			if (pFicha instanceof DamaDeJugadorUno) {return true;} else {return false;}
	
	}
	
	
	public boolean esPeonEnemigo(Ficha pFicha) {
		
		
			if (pFicha instanceof PeonDeJugadorDos) {return true;} else {return false;}
		 
	}
	
	
	public boolean esDamaEnemiga(Ficha pFicha) {
		
	
			if (pFicha instanceof DamaDeJugadorDos) {return true;} else {return false;}
			

		 
	}
	
	public Movimiento cerebroUno() {
		
		
		/* Pre:  La IA tiene mínimo una ficha para mover en el tablero y no ha ejecutado ningún movimiento en su turno
		   Post: Devuelve una variable de tipo movimiento donde guarda 4 integers:
		         el primer par representa la posicion de la ficha a mover y el segundo a donde moverla.
		         También guarda en su interior la ficha comida si ha comido y un booleano indicando si su movimiento
		         implicaría la conversión de un peón a dama. Si no hay movimiento posible, movimiento es null
		
		   La IA ejecutará un movimiento basado en una de sus 4 prioridades:
		
		   Prioridad 1: Comer con una ficha
		   Prioridad 2: Proteger una ficha de ser posiblemente comida en el siguiente turno
		   Prioridad 3: Mover una ficha que no está en peligro sin ponerla en peligro
		   Prioridad 4: Mover una ficha poniéndola en rango de ser comida.
		
		
		   Intentará mover usando una prioridad con número 
		   bajo sobre uno alto generalmente, salvo la excepción de comer con dama una única ficha siendo posible comerle la 
		   dama de vuelta, donde siendo prioridad 1 no será ejecutado si encuentra otro movimiento con prioridad 3 o menos. */
		
		

		boolean decidido = false;
		boolean decididoDecisivo = false;
		boolean decidioAlgunaVez = false;
		boolean seHizoDama = false;
		int f = 0;
		int c = 0;
		int fi = 0;
		int co = 0;
		int filaFicha = -1;
		int columnaFicha = -1;
		int fila = -1;
		int columna = -1;
		Movimiento movimiento = null;
		Ficha fichaComida = null;
		
		
		/* Prioridad 1: Buscar para comer:  -si puede comer con un peón, lo hará
		     			                    -si puede comer con una dama, solo lo hará si se cumple alguno de estos requisitos:
		 																					- come más de una ficha
		 																					- no es comida tras comer
		   				en el caso de que encuentre para comer con dama pero no cumpla los siguientes requisitos, los valores
		                de mover la ficha se mantienen guardados, por si no hay mas movimientos posibles que sobreescriban estos datos
		                
		                
		                La IA comerá siempre una dama sobre cualquier peon si cumple algún requisito (en caso 
		                de mover dama) pero no tiene prioridad si no cumple */
		
		
		while (f != 8 && !decidido ) {
			
			c = 0;
			
			while (c != 8 && !decidido) {
				
				// Si encuentra peon de J1 siendo J1
				
				if (this.esPeonAliado(Tablero.getMiTablero().obtenerFicha(f,c)) && !decidido) { 

					if(f-2 > -1 && c+2<8 && !decidido) {
						
						if(this.esDamaEnemiga(Tablero.getMiTablero().obtenerFicha(f-1,c+1))&& this.esVacio(Tablero.getMiTablero().obtenerFicha(f-2,c+2))) {
							decidido = true;
							fila = f-2;
							columna = c+2;
							filaFicha = f;
							columnaFicha = c;
							fichaComida = Tablero.getMiTablero().obtenerFicha(f-1, c+1);
						}
					}
					
					if(f-2 > -1 && c-2 > -1 && !decidido) {
						
						if(this.esDamaEnemiga(Tablero.getMiTablero().obtenerFicha(f-1,c-1)) && this.esVacio(Tablero.getMiTablero().obtenerFicha(f-2,c-2))) {
							decidido = true;
							fila = f-2;
							columna = c-2;
							filaFicha = f;
							columnaFicha = c;
							fichaComida = Tablero.getMiTablero().obtenerFicha(f-1, c-1);

							
						}
						
					}
					
					
        	 
					
				// Si encuentra dama suya	
					
				} else if (this.esDamaAliada(Tablero.getMiTablero().obtenerFicha(f,c)) && !decidido) {
				


					// Comprobar si come arriba a la izquierda
					fi = f;
					co = c;
					while (fi > 1 && co > 1 && !decidido) {
						
						fi = fi - 1;
						co = co - 1;

						if(this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
							fi = 1;

						} else if(this.esDamaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {

							if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi-1,co-1))) {

								decidido = true;
								fila = fi-1;
								columna = co - 1;
								filaFicha = f;
								columnaFicha = c;
								fichaComida = Tablero.getMiTablero().obtenerFicha(fi, co);

								
							} else {fi = 1;}
							
						} else if(this.esPeonEnemigo(Tablero.getMiTablero().obtenerFicha(fi,co))) {fi = 1;}
						
					}

					
				
					
				if (decidido && !decididoDecisivo) {
					
					if(!this.comible(fila, columna, 4, true, true) || this.encadenar(fila, columna, 4, true)) {decididoDecisivo = true;}
					else {decidido = false; decidioAlgunaVez = true;}
				}
				
				// Comprobar si come arriba a la derecha
					 
				fi = f;
				co = c;
				
				while (fi > 1 && co < 6 && !decidido) {

					fi = fi -1;
					co = co + 1;
					
		
					
					if (this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
						fi = 1;
					} else if(this.esDamaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi-1, co+1))) {
							decidido = true;
							fila = fi -1;
							columna = co + 1;
							filaFicha = f;
							columnaFicha = c;
							fichaComida = Tablero.getMiTablero().obtenerFicha(fi, co);
					
							
							
						} else {fi = 1;} 
							
					} else if(this.esPeonEnemigo(Tablero.getMiTablero().obtenerFicha(fi,co))) {fi = 1;}
					
				}
				
				if (decidido && !decididoDecisivo) {
					
					if(!this.comible(fila, columna, 3, true, true) || this.encadenar(fila, columna, 3, true)) {decididoDecisivo = true;
					
					}
					else {decidido = false; decidioAlgunaVez = true;}
				}				
				
				// comprobar si puede come abajo a la izquierda
				
				fi = f;
				co = c;
				
				while (fi < 6 && co > 1 && !decidido ) {
					
					fi = fi + 1;
					co = co - 1;
					
					if (this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
						fi = 6;
					} else if (this.esDamaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi+1, co-1))) {
							decidido = true;
							fila = fi + 1;
							columna = co - 1;
							filaFicha = f;
							columnaFicha = c;
							fichaComida = Tablero.getMiTablero().obtenerFicha(fi, co);
				
							
						} else {fi = 6;}
						
					} else if(this.esPeonEnemigo(Tablero.getMiTablero().obtenerFicha(fi,co))) {fi = 6;}
					
				}
				
				if (decidido && !decididoDecisivo) {
					
					if(!this.comible(fila, columna, 2, true, true) || this.encadenar(fila, columna, 2, true)) {decididoDecisivo = true;}
					else {decidido = false; decidioAlgunaVez = true;}
				}	
				
				// Comprobar si puede comer abajo a la derecha
				
				fi = f;
				co = c;
				
				while (fi < 6 && co < 6 && !decidido) {
					
					fi = fi + 1;
					co = co + 1;
					
					if (this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
						fi = 6;
					} else if (this.esDamaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi+1, co+1))) {
							decidido = true;
							fila = fi + 1;
							columna = co + 1;
							filaFicha = f;
							columnaFicha = c;
							fichaComida = Tablero.getMiTablero().obtenerFicha(fi, co);
					
							
						} else {fi = 6;}
					
					} else if(this.esPeonEnemigo(Tablero.getMiTablero().obtenerFicha(fi,co))) {fi = 6;}
					
					
				}
				
				if (decidido && !decididoDecisivo) {
					
					if(!this.comible(fila, columna, 1, true, true) || this.encadenar(fila, columna, 1, true)) {decididoDecisivo = true;}
					else {decidido = false; decidioAlgunaVez = true;}
				}
				
					
				}
				c++;
			}
			f++;
		}
		
		f = 0;
		
		while (f != 8 && !decidido ) {
			
			c = 0;

			while (c != 8 && !decidido) {
				
				// Si encuentra peon de J1 siendo J1
				
				if (this.esPeonAliado(Tablero.getMiTablero().obtenerFicha(f,c)) && !decidido) { 
					
					if(f-2 > -1 && c+2<8 && !decidido) {
						
						if(this.esPeonEnemigo(Tablero.getMiTablero().obtenerFicha(f-1,c+1))&& this.esVacio(Tablero.getMiTablero().obtenerFicha(f-2,c+2))) {
							decidido = true;
							fila = f-2;
							columna = c+2;
							filaFicha = f;
							columnaFicha = c;
							fichaComida = Tablero.getMiTablero().obtenerFicha(f-1, c+1);
						}
					}
					
					if(f-2 > -1 && c-2 > -1 && !decidido) {
						
						if(this.esPeonEnemigo(Tablero.getMiTablero().obtenerFicha(f-1,c-1)) && this.esVacio(Tablero.getMiTablero().obtenerFicha(f-2,c-2))) {
							decidido = true;
							fila = f-2;
							columna = c-2;
							filaFicha = f;
							columnaFicha = c;
							fichaComida = Tablero.getMiTablero().obtenerFicha(f-1, c-1);

							
						}
						
					}
					
					
        	}
					
				// Si encuentra dama suya	
					
				 else if (this.esDamaAliada(Tablero.getMiTablero().obtenerFicha(f,c)) && !decidido) {

					// Comprobar si come arriba a la izquierda
					fi = f;
					co = c;
					while (fi > 1 && co > 1 && !decidido) {

						fi = fi - 1;
						co = co - 1;

						if(this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
							fi = 1;

						} else if(this.esPeonEnemigo(Tablero.getMiTablero().obtenerFicha(fi,co))) {

							if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi-1,co-1))) {

								decidido = true;
								fila = fi-1;
								columna = co - 1;
								filaFicha = f;
								columnaFicha = c;
								fichaComida = Tablero.getMiTablero().obtenerFicha(fi, co);

								
								
							} else {fi = 1;}
							
						} else if(this.esDamaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {fi = 1;}
						
					}

					
				
					
				if (decidido && !decididoDecisivo) {
					
					if(!this.comible(fila, columna, 4, true, true) || this.encadenar(fila, columna, 4, true)) {decididoDecisivo = true;}
					else {decidido = false; decidioAlgunaVez = true;}
				}
				
				// Comprobar si come arriba a la derecha
					 
				fi = f;
				co = c;
				
				while (fi > 1 && co < 6 && !decidido) {

					fi = fi -1;
					co = co + 1;
			
					
					if (this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
						fi = 1;
					} else if(this.esPeonEnemigo(Tablero.getMiTablero().obtenerFicha(fi,co))) {
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi-1, co+1))) {
							decidido = true;
							fila = fi -1;
							columna = co + 1;
							filaFicha = f;
							columnaFicha = c;
							fichaComida = Tablero.getMiTablero().obtenerFicha(fi, co);
					
							
							
						} else {fi = 1;}
							
					} else if(this.esDamaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {fi = 1;}
					
				}
				
				if (decidido && !decididoDecisivo) {

					if(!this.comible(fila, columna, 3, true, true) || this.encadenar(fila, columna, 3, true)) {decididoDecisivo = true;
					}
					else {decidido = false; decidioAlgunaVez = true;}
				}				
				
				// comprobar si puede come abajo a la izquierda

				fi = f;
				co = c;
				
				while (fi < 6 && co > 1 && !decidido ) {

					fi = fi + 1;
					co = co - 1;
					if (this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
						fi = 6;
					} else if (this.esPeonEnemigo(Tablero.getMiTablero().obtenerFicha(fi,co))) {
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi+1, co-1))) {
							decidido = true;
							fila = fi + 1;
							columna = co - 1;
							filaFicha = f;
							columnaFicha = c;
							fichaComida = Tablero.getMiTablero().obtenerFicha(fi, co);

							
						} else {fi = 6;}
						
					} else if(this.esDamaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {fi = 6;}
					
				}
				
				if (decidido && !decididoDecisivo) {
					
					if(!this.comible(fila, columna, 2, true, true) || this.encadenar(fila, columna, 2, true)) {decididoDecisivo = true;}
					else {decidido = false; decidioAlgunaVez = true;}
				}	
				
				// Comprobar si puede comer abajo a la derecha
				
				fi = f;
				co = c;
				
				while (fi < 6 && co < 6 && !decidido) {
					
					fi = fi + 1;
					co = co + 1;
					
					if (this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
						fi = 6;
					} else if (this.esPeonEnemigo(Tablero.getMiTablero().obtenerFicha(fi,co))) {
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi+1, co+1))) {
							decidido = true;
							fila = fi + 1;
							columna = co + 1;
							filaFicha = f;
							columnaFicha = c;
							fichaComida = Tablero.getMiTablero().obtenerFicha(fi, co);
							
						} else {fi = 6;}
					
					} else if(this.esDamaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {fi = 6;}
					
					
				}
				
				if (decidido && !decididoDecisivo) {
					
					if(!this.comible(fila, columna, 1, true, true) || this.encadenar(fila, columna, 1, true)) {decididoDecisivo = true;}
					else {decidido = false; decidioAlgunaVez = true;}
				}
				
					
				}
				c++;
			}
			f++;
		}
		
		
		/* Prioridad 2: Buscar una ficha en peligro y moverla a un lugar seguro, el movimiento solo se hará
		                si es posible proteger la ficha. Puede dejar desprotegidas otras tras el movimiento
		                Prioriza damas sobre peones */
		
		// Primero comprueba si hay damas en peligro
		

		
		
		f = 0;
		
		while (f < 8 && !decidido) {
			
			c = 0;
			
			while (c < 8 && !decidido) {
				
				// Si encuentra dama suya
				
				if (this.esDamaAliada(Tablero.getMiTablero().obtenerFicha(f,c))) {
					
					if (this.comible(f,c,0, false, false)) {
						
						// Buscar en diagonal arriba izquierda hueco seguro
						
						fi = f;
						co = c;
						
						while (fi > 0 && co > 0 && !decidido) {
							fi = fi - 1;
							co = co - 1;
							
							if(this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {

								if(!this.comible(fi, co, 4, true, false)) {
									decidido = true;
									fila = fi;
									columna = co;
									filaFicha = f;
									columnaFicha = c;
									fichaComida = null;

									
								}
								
								
							} else {fi = 0;}
							
							
						}
						
						// Buscar en diagonal arriba derecha hueco seguro
						
						fi = f;
						co = c;
						
						while (fi > 0 && co < 7 && !decidido) {
							fi = fi - 1;
							co = co + 1;
							
							if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {
								
								if (!this.comible(fi, co, 3, true, false)) {
									decidido = true;
									fila = fi;
									columna = co;
									filaFicha = f;
									columnaFicha = c;
									fichaComida = null;

									
								}
							} else {fi = 0;}
							
							
						}
						
						// Buscar en diagonal abajo izquierda hueco seguro
						
						fi = f;
						co = c;
						
						while (fi < 7 && co > 0 && !decidido) {
							fi = fi + 1;
							co = co - 1;
							
							if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {
								
								if (!this.comible(fi, co, 2, true, false)) {
									decidido = true;
									fila = fi;
									columna = co;
									filaFicha = f;
									columnaFicha = c;
									fichaComida = null;

									
									
								}
							} else {fi = 7;}
							
						}
						
						// Buscar en diagonal abajo derecha hueco seguro
						
						fi = f;
						co = c;
						
						while (fi < 7 && co < 7 && !decidido) {
							fi = fi + 1;
							co = co + 1;
							
							if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {
								
								if (!this.comible(fi, co, 1, true, false)) {
									decidido = true;
									fila = fi;
									columna = co;
									filaFicha = f;
									columnaFicha = c;
									fichaComida = null;

									
								}
							} else {fi = 7;}
							
						}
						
						
						
						
					 }
				}
				
				
				c++;
			}
			
			f++;
	
		} 

		
		// Ahora comprueba los peones
		
		f = 0;
		
		while (f < 8 && !decidido) {
			
			c = 0;
			
			while (c < 8 && !decidido) {
				
				// Si encuentra peon suyo
				
				if (this.esPeonAliado(Tablero.getMiTablero().obtenerFicha(f,c))) {
					
					if (this.comible(f,c, 0, false, false)) {	
						
						if ( f != 0) { // Siendo J1
							
							if (c != 0) {
								
								if (this.esVacio(Tablero.getMiTablero().obtenerFicha(f-1, c-1))) {
									
									if (!this.comible(f-1, c-1, 4, false, false)) {
										decidido = true;
										fila = f-1;
										columna = c-1;
										filaFicha = f;
										columnaFicha = c;
										fichaComida = null;
									}						
								}			
							}
							
							if (c != 7 && !decidido) {
								
								if (this.esVacio(Tablero.getMiTablero().obtenerFicha(f-1, c+1))) {
									
									if (!this.comible(f-1, c+1, 3, false, false)) {
										decidido = true;
										fila = f-1;
										columna = c + 1;
										filaFicha = f;
										columnaFicha = c;
										fichaComida = null;

									}
								}
								
							}
							
							
						}
		
					}
					
					
				}
				
				
				c++;
			}
			
			f++;
	
		} 
		
		/* Prioridad 3: Mover ficha sin poder ser comida de vuelta (puede desproteger otras).
		                No tiende a mover las damas un único espacio si no es necesario
		                Prefiere mover peon sobre dama.
		                Prioriza un movimiento seguro de peon donde se convertiria en dama */
		
		
		
		// Mover peon siendo J1
		
		
			
			f = 0;
			
		while (f < 8 && !decidido) {

			c = 0;
			
			while (c < 8 && !decidido) {
				
			
			
			if(this.esPeonAliado(Tablero.getMiTablero().obtenerFicha(f, c))) {
				
				
					
					if (c != 0) {
						
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(f-1, c-1))) {
							
							if(!this.comible(f-1, c-1, 4, false, false)) {
								
								decidido = true;
								fila = f-1;
								columna = c-1;
								filaFicha = f;
								columnaFicha = c;
								fichaComida = null;

								
							}	
						}	
					}
					
					if (c != 7 && !decidido) {
						
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(f-1, c+1))) {
							
							if(!this.comible(f-1, c+1, 3, false, false)) {
								
								decidido = true;
								fila = f-1;
								columna = c+1;
								filaFicha = f;
								columnaFicha = c;
								fichaComida = null;

							}
						}	
					}
					
		
			
				
				
			} 
			c++;
			
		}
			f++;
			
		}
		
		
		
		

		
		// Mover dama (ambos J funcionan igual)
		
		f = 0;
	
		while (f < 8 && !decidido) {

			c = 0;

			while (c < 8 && !decidido) {
				
				if (this.esDamaAliada(Tablero.getMiTablero().obtenerFicha(f, c))) {
					
					// Mirar a varias casillas de distancia (>1)

					fi = f -1;
					co = c -1;
					
					if (fi > -1 & co > -1) {
						if (!this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {fi = 0;}}

					while (fi > 0 && co > 0 && !decidido) {
						
						fi = fi -1;
						co = co -1;

						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {

							if (!this.comible(fi, co, 4, true, false)) {
								
								decidido = true;
								fila = fi;
								columna = co;
								filaFicha = f;
								columnaFicha = c;
								fichaComida = null;

							}
						} else {fi = 0;}
						
					}
					
					fi = f-1;
					co = c+1;
					
					if (fi > -1 & co < 8) {
						if (!this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {fi = 0;}}

					
					while (fi > 0 && co < 7 && !decidido) {
						
						fi = fi - 1;
						co = co + 1;
						
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {
							
							if (!this.comible(fi, co, 3, true, false)) {
								
								decidido = true;
								fila = fi;
								columna = co;
								filaFicha = f;
								columnaFicha = c;
								fichaComida = null;

							}
						} else {fi = 0;}
					}
					
					fi = f + 1;
					co = c - 1;
					
					if (fi < 8 & co > -1) {
						if (!this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {fi = 7;}}

					
					while (fi < 7 && co > 0 && !decidido) {
						
						fi = fi + 1;
						co = co - 1;
						
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {
							
							if (!this.comible(fi, co, 2, true, false)) {
								
								decidido = true;
								fila = fi;
								columna = co;
								filaFicha = f;
								columnaFicha = c;
								fichaComida = null;

							}
						} else {fi = 7;}
					}
					
					fi = f + 1;
					co = c + 1;
					
					if (fi < 8 & co < 8) {
					
						if (!this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {fi = 7;}}

					
					while (fi < 7 && co < 7 && !decidido ) {
						
						fi = fi + 1;
						co = co + 1;
						
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {
							
							if (!this.comible(fi, co, 1, true, false)) {
								
								decidido = true;
								fila = fi;
								columna = co;
								filaFicha = f;
								columnaFicha = c;
								fichaComida = null;

								
							}
						} else {fi = 7;}
					}

					
					// Mirar a una casilla de distancia (1)
					
					if (f != 0 && c!= 0 && !decidido) {
						
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(f-1, c-1))) {
							
							if(!this.comible(f-1, c-1, 4, false, false)) {
								
								decidido = true;
								fila = f-1;
								columna = c-1;
								filaFicha = f;
								columnaFicha = c;
								fichaComida = null;

							}
						}
					}
					
					if (f != 0 && c != 7 && !decidido) {
						
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(f-1, c+1))) {
							
							if (!this.comible(f-1, c+1, 3, false, false)) {
								
								decidido = true;
								fila = f-1;
								columna = c+1;
								filaFicha = f;
								columnaFicha = c;
								fichaComida = null;

								
							}
						}
					}
					
					if (f != 7 && c != 0 && !decidido) {
						
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(f+1, c-1))) {
							
							if (!this.comible(f+1, c-1, 2, false, false)) {
								
								decidido = true;
								fila = f+1;
								columna = c-1;
								filaFicha = f;
								columnaFicha = c;
								fichaComida = null;

									
							}
						}
					}
					
					if (f != 7 && c != 7 && !decidido) {
						
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(f+1, c+1))) {
							
							if (!this.comible(f+1, c+1, 1, false, false)) {
								
								decidido = true;
								fila = f+1;
								columna = c+1;
								filaFicha = f;
								columnaFicha = c;
								fichaComida = null;

							}
						}
					}
				}
				
				
				c++;
			}
			f++;
		}


		/* Prioridad 4: Mueve una ficha por mover, esté en peligro o no.
		                Prioriza peones sobre damas */
		
		
		// Mover peon

		f = 0;
		
		while (f < 8 && !decidido && !decidioAlgunaVez) {
			
			c = 0;
			
			while (c < 8 && !decidido) {
				
			
			
			if(this.esPeonAliado(Tablero.getMiTablero().obtenerFicha(f, c))) {
				
				if (f != 0) { // Si es J1
					
					if (c != 0) {
						
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(f-1, c-1))) {
							
								
								decidido = true;
								fila = f-1;
								columna = c-1;
								filaFicha = f;
								columnaFicha = c;
								fichaComida = null;

								
						
						}	
					}
					
					if (c != 7 && !decidido) {
						
						if (this.esVacio(Tablero.getMiTablero().obtenerFicha(f-1, c+1))) {
							
								
								decidido = true;
								fila = f-1;
								columna = c+1;
								filaFicha = f;
								columnaFicha = c;
								fichaComida = null;

							
						}	
					}
					
		
				}
				
			}
			c++;
			
		}
			f++;
			
		}
		
		f = 0;
		
		while (f < 8 & !decidido && !decidioAlgunaVez) {
			
			c = 0;
			
			while (c < 8 && !decidido) {
				 
				if (this.esDamaAliada(Tablero.getMiTablero().obtenerFicha(f, c))) {
						
						// Mirar a varias casillas de distancia (>1)
						
						fi = f -1;
						co = c -1;
						
						while (fi > 0 && co > 0 && !decidido) {
							
							fi = fi -1;
							co = co -1;
							
							if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {
								
									
									decidido = true;
									fila = fi;
									columna = co;
									filaFicha = f;
									columnaFicha = c;
									fichaComida = null;

								
							} else {fi = 0;}
							
						}
						
						fi = f-1;
						co = c+1;
						
						while (fi > 0 && co < 7 && !decidido) {
							
							fi = fi - 1;
							co = co + 1;
							
							if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {
								
		 
									
									decidido = true;
									fila = fi;
									columna = co;
									filaFicha = f;
									columnaFicha = c;
									fichaComida = null;

								
							} else {fi = 0;}
						}
						
						fi = f + 1;
						co = c - 1;
						
						while (fi < 7 && co > 0 && !decidido) {
							
							fi = fi + 1;
							co = co - 1;
							
							if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {
								
									
									decidido = true;
									fila = fi;
									columna = co;
									filaFicha = f;
									columnaFicha = c;
									fichaComida = null;

								
							} else {fi = 7;}
						}
						
						fi = f + 1;
						co = c + 1;
						
						while (fi < 7 && co < 7 && !decidido ) {
							
							fi = fi + 1;
							co = co + 1;
							
							if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {
								
									
									decidido = true;
									fila = fi;
									columna = co;
									filaFicha = f;
									columnaFicha = c;
									fichaComida = null;

									
								
							} else {fi = 7;}
						}

						
						// Mirar a una casilla de distancia (1)
						
						if (f != 0 && c!= 0 && !decidido) {
							
							if (this.esVacio(Tablero.getMiTablero().obtenerFicha(f-1, c-1))) {
								
									
									decidido = true;
									fila = f-1;
									columna = c-1;
									filaFicha = f;
									columnaFicha = c;
									fichaComida = null;

								
							}
						}
						
						if (f != 0 && c != 7 && !decidido) {
							
							if (this.esVacio(Tablero.getMiTablero().obtenerFicha(f-1, c+1))) {
								
									
									decidido = true;
									fila = f-1;
									columna = c+1;
									filaFicha = f;
									columnaFicha = c;
									fichaComida = null;

									
								
							}
						}
						
						if (f != 7 && c != 0 && !decidido) {
							
							if (this.esVacio(Tablero.getMiTablero().obtenerFicha(f+1, c-1))) {
								
									
									decidido = true;
									fila = f+1;
									columna = c-1;
									filaFicha = f;
									columnaFicha = c;
									fichaComida = null;

										
								
							}
						}
						
						if (f != 7 && c != 7 && !decidido) {
							
							if (this.esVacio(Tablero.getMiTablero().obtenerFicha(f+1, c+1))) {
								
									
									decidido = true;
									fila = f+1;
									columna = c+1;
									filaFicha = f;
									columnaFicha = c;
									fichaComida = null;

								
							}
						}
					}
				
				
				
				c++;
			}
			f++;
		}
		
		// Determinar si se convirtió en dama el peón que movió (si movió peón)
		

			
		
		
		if (filaFicha != -1) {
			
			if (this.esPeonAliado(Tablero.getMiTablero().obtenerFicha(filaFicha, columnaFicha))) {
		
					if(fila == 0) {seHizoDama = true;}
					
				}
			
		movimiento = new Movimiento (fila, columna, filaFicha, columnaFicha, fichaComida, seHizoDama);}

		
		
	return movimiento;
		
	}
	
	
	
	// enQueDiagonalMovio() es heredado
	
	
	public Movimiento cerebroDos(int pFila, int pColumna, int pDiagonal) {
		
		/* Pre: Se ha realizado minimo un movimiento en el turno, la posicion de la ficha movida es la de los dos integers recibidos. 
		   El tercer integer dice que en que diagonal se movió en el movimiento anterior
		   0 = movio peon | 1 = arr izq | 2 = arr dcha | 3 = abo izq | 4 = abo dcha 
		   Post: Devuelve el movimiento encadenando el salto con la ficha comida, si no hay posibles, devuelve null. */
		
		
		int fila = -1;
		int columna = -1;
		int fi = 0;
		int co = 0;
		boolean decidido = false;
		boolean seHizoDama = false;
	
		Ficha fichaComida = null;
		Movimiento movimiento = null;
		
		// Si se movio un peon:
		
		if (this.esPeonAliado(Tablero.getMiTablero().obtenerFicha(pFila, pColumna))) {
			
			if (pFila > 1) {
				
				if (pColumna > 1) {
					
					if (this.esFichaEnemiga(Tablero.getMiTablero().obtenerFicha(pFila-1, pColumna-1)) && this.esVacio(Tablero.getMiTablero().obtenerFicha(pFila-2, pColumna-2))) {
						decidido = true;
						fila = pFila - 2;
						columna = pColumna - 2;
						fichaComida = Tablero.getMiTablero().obtenerFicha(pFila-1, pColumna-1);
					}
						
				}
				
				if (pColumna < 6 && !decidido) {
					
					if (this.esFichaEnemiga(Tablero.getMiTablero().obtenerFicha(pFila-1, pColumna+1)) && this.esVacio(Tablero.getMiTablero().obtenerFicha(pFila-2, pColumna+2))) {
						decidido = true;
						fila = pFila -2;
						columna = pColumna + 2;
						fichaComida = Tablero.getMiTablero().obtenerFicha(pFila-1, pColumna+1);
					}
				}
				
				
			}
			
			
		// Si se movió una dama:
	
		} else if (this.esDamaAliada(Tablero.getMiTablero().obtenerFicha(pFila, pColumna))) {
			
			
			// Comprobar si come arriba a la izquierda
			fi = pFila;
			co = pColumna;
			while (fi > 1 && co > 1 && !decidido && pDiagonal != 4) {
				
				fi = fi - 1;
				co = co - 1;
				
				if(this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
					fi = 1;
					
				} else if(this.esFichaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {
					if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi-1,co-1))) {
						decidido = true;
						fila = fi-1;
						columna = co - 1;
						fichaComida = Tablero.getMiTablero().obtenerFicha(fi, co);

						
						
					} else {fi = 1;}
					
				}
				
			
			}
			
		
		// Comprobar si come arriba a la derecha
			 
			fi = pFila;
			co = pColumna;
		while (fi > 1 && co < 6 && !decidido && pDiagonal != 3) {
			
			fi = fi -1;
			co = co + 1;
			
			if (this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
				fi = 1;
			} else if(this.esFichaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {
				if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi-1, co+1))) {
					decidido = true;
					fila = fi -1;
					columna = co + 1;
					fichaComida = Tablero.getMiTablero().obtenerFicha(fi, co);

					
					
				} else {fi = 1;}
					
			}
			
		
		}
			
		
		// comprobar si puede comer abajo a la izquierda
		
		fi = pFila;
		co = pColumna;
		
		while (fi < 6 && co > 1 && !decidido && pDiagonal != 2 ) {
			
			fi = fi + 1;
			co = co - 1;
			
			if (this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
				fi = 6;
			} else if (this.esFichaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {
				if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi+1, co-1))) {
					decidido = true;
					fila = fi + 1;
					columna = co - 1;
					fichaComida = Tablero.getMiTablero().obtenerFicha(fi, co);

					
				} else {fi = 6;}
				
			}
			
		
		}

		
		// Comprobar si puede comer abajo a la derecha
		
		fi = pFila;
		co = pColumna;
		
		
		while (fi < 6 && co < 6 && !decidido && pDiagonal != 1) {
			
			fi = fi + 1;
			co = co + 1;
			
			if (this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
				fi = 6;
			} else if (this.esFichaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {
				if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi+1, co+1))) {
					decidido = true;
					fila = fi + 1;
					columna = co + 1;
					fichaComida = Tablero.getMiTablero().obtenerFicha(fi, co);

					
				} else {fi = 6;}
			
			}
			
			
		
		}
			
			
		} else {System.out.println("Error, no se ha encontrado la ficha");}
		
		
		
		if (this.esPeonAliado(Tablero.getMiTablero().obtenerFicha(pFila, pColumna))) {
			
			
			
				
				if(fila == 0) {seHizoDama = true;}
				
			
			
		}
		
		// Si encontro un movimiento (actualizando fila a valor 0-7, solo entonces genera el movimiento
		
		if (fila != -1) {
		
		movimiento = new Movimiento(fila, columna, pFila, pColumna, fichaComida, seHizoDama);}
		
		
		return movimiento;
		
		
	}
	
	
	public  boolean comible (int pFila, int pColumna, int pDireccion, boolean pDamaInexistente, boolean pComioAdyecente) {
		
		/*  Pre: 3 integers que representan: El primer par la posición en la que está la ficha
		         el último representa si hay que ignorar la ficha adyecente en una de las direcciones
		         (boolean false) o la primera dama aliada de dicha diagonal (boolean true)
		         pDireccion 1 --> arr izq | 2 --> arr dcha | 3 --> abo izq | 4 --> abo dcha | 0 --> tablero tal cual
		         1 booleano que representa: si hay una dama aliada en el camino de la diagonal que hay que ignorar o no
		         1 booleano que representa si se comio una ficha en el movimiento o no (para ignorar)
		
		
		    Post: Un booleano que dice si el rival puede comer la ficha en la posición de los dos primeros integers o no 
		    
		    Las ignoraciones sirven para poder hacer calculos correctos, ya que tras mover el tablero quedaría de
		    forma distinta y los calculos no serían correctos si se perfomasen con el tablero antes de actualizar.
		    */
	
		boolean pasadoPorDama;
		boolean comible = false;
		int fila = -1;
		int columna = -1;
		
	
		
		if (pDamaInexistente) {pasadoPorDama = false;} else {pasadoPorDama = true;}
		
		if (pFila != 0 && pFila != 7 && pColumna != 0 && pColumna != 7) { // No puede ser comido en las esquinas del tablero
																	      // solo se comprueba si no esta en esquinas
		
			// Comprobar si por arriba a la izquierda está en peligro
			// solo puede ser comido en este sentido si el espacio de abajo a la derecha está libre 
			// (pero si venimos de la diagonal abajo derecha (int = 4), entonces la ficha que todavía
			// está en el tablero no estaría porque la hubiesemos comido. En dicho caso tenemos que comprobar
			// los calculos incluso aunque haya todavía una ficha ahí
		
			if (this.esVacio(Tablero.getMiTablero().obtenerFicha(pFila+1, pColumna+1)) || pDireccion == 4) { 
				
				if (this.esPeonEnemigo(Tablero.getMiTablero().obtenerFicha(pFila-1, pColumna-1)) && pDireccion != 1) {
					comible = true;

				}
				
				if ((!pDamaInexistente && pDireccion == 1) || (pDamaInexistente && pComioAdyecente && pDireccion == 1)) {
					
				
					fila = pFila - 2;
					columna = pColumna -2;
				} else {fila = pFila - 1; columna = pColumna - 1;}
			
				while (fila > -1 && columna > -1 && !comible) {
				
					if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fila,columna))) {
						fila = fila - 1;
						columna = columna - 1;
					} else if (this.esDamaEnemiga(Tablero.getMiTablero().obtenerFicha(fila,columna))) {
					
						comible = true;			
			     	} else if (this.esDamaAliada(Tablero.getMiTablero().obtenerFicha(fila,columna)) && !pasadoPorDama && pDireccion == 1) {pasadoPorDama = true; fila--; columna--;
					
			     	}else {fila = -2;}
	
				}
			
			
			}
			// Comprobar  si arriba a la derecha hay ficha que le puede comer
			// solo puede ser comido si el espacio de abajo a la izquierda esta libre
			
	//		if (pDamaInexistente) {pasadoPorDama = false;} else {pasadoPorDama = true;} <-- Codigo no necesario

			
			if ((this.esVacio(Tablero.getMiTablero().obtenerFicha(pFila+1, pColumna-1)) || pDireccion == 3) && ! comible) {
				
				// Comprobar si peon enemigo come
				
				if (this.esPeonEnemigo(Tablero.getMiTablero().obtenerFicha(pFila-1, pColumna+1 )) && pDireccion != 2) {
					comible = true;
				}
				
				// Comprobar si dama enemiga come
				
				if ((!pDamaInexistente && pDireccion == 2) || (pDamaInexistente && pComioAdyecente && pDireccion == 2)) {
					
					fila = pFila - 2;
					columna = pColumna + 2;
				} else {fila = pFila - 1; columna = pColumna + 1;}
				
				while (fila > -1 && columna < 8 && !comible) {
					
					if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fila,columna))) {
						fila = fila - 1;
						columna = columna + 1;	
					} else if (this.esDamaEnemiga(Tablero.getMiTablero().obtenerFicha(fila,columna))) {
						
						comible = true;
					} else if (this.esDamaAliada(Tablero.getMiTablero().obtenerFicha(fila,columna)) && !pasadoPorDama && pDireccion == 2) {pasadoPorDama = true; fila--; columna++;
					
					} else {fila = -2;}
					
				}	
				
				}
			// Comprobar si abajo a la izquierda le pueden comer
			// solo puede ser comido si el espacio de arriba a la derecha esta libre
			
		//	if (pDamaInexistente) {pasadoPorDama = false;} else {pasadoPorDama = true;} <-- Código no necesario

			
			
			if ((this.esVacio(Tablero.getMiTablero().obtenerFicha(pFila-1, pColumna+1)) || pDireccion == 2) && !comible) {
				
			
				

				
				// Comprobar si dama enemiga come
				
				if ((!pDamaInexistente && pDireccion == 3) || (pDamaInexistente && pComioAdyecente && pDireccion == 3)) {
					
					fila = pFila + 2;
					columna = pColumna - 2;		
				} else {fila = pFila + 1; columna = pColumna - 1;}
				
				while (fila < 8 && columna > -1 && !comible) {
					
					if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fila,columna))) {
						fila = fila + 1;
						columna = columna - 1;
					} else if (this.esDamaEnemiga(Tablero.getMiTablero().obtenerFicha(fila, columna))) {
						
						comible = true;
					} else if (this.esDamaAliada(Tablero.getMiTablero().obtenerFicha(fila, columna)) && !pasadoPorDama && pDireccion == 3) {pasadoPorDama = true; fila++; columna--;
					
					} else {fila = 8;}
				}
				
			}
			// Comprobar si puede ser comido por una ficha abajo a la derecha
			// solo puede ser comido si el espacio de arriba a la izquierda esta libre
			
	//		if (pDamaInexistente) {pasadoPorDama = false;} else {pasadoPorDama = true;} <-- Código no necesario

			
			if ((this.esVacio(Tablero.getMiTablero().obtenerFicha(pFila-1, pColumna-1)) || pDireccion == 1) && !comible) {
				
				

				
				// Comprobar si dama enemiga come
				
				if ((!pDamaInexistente && pDireccion == 4) || (pDamaInexistente && pComioAdyecente && pDireccion == 4)) {
					
					fila = pFila + 2;
					columna = pColumna + 2;
				} else {fila = pFila + 1; columna = pColumna + 1;}
				
		
				
				while (fila < 8 && columna < 8 && !comible) {
					
					if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fila, columna))) {
						fila = fila + 1;
						columna = columna + 1;				
					} else if (this.esDamaEnemiga(Tablero.getMiTablero().obtenerFicha(fila,columna))) {
						
						comible = true;

					} else if (this.esDamaAliada(Tablero.getMiTablero().obtenerFicha(fila,columna)) && !pasadoPorDama && pDireccion == 4) {pasadoPorDama = true; fila++; columna++;
					
					} else {fila = 8;}
					
				}
			}
		

		
		
	}

	
		return comible;
	}
	
	
	
	
	public  boolean encadenar(int pFila, int pColumna, int pDireccion, boolean pEsDama) {
		
		/* Pre:  Se dan 3 integers: los dos primeros representan la posición de una ficha
		         del jugador tras hacer el movimiento de comer a una ficha del rival
		         El tercer integer representa la direccion en la que saltó para comer
		         1 -> arr izq | 2 --> arr dcha | 3 --> abo izq | 4 --> abo dcha
		         Se da un booleano: true --> la ficha es dama | false --> la ficha es peon
		  
		 
		   Post: Se devuelve un booleano diciendo si puede encadenar saltos y seguir comiendo 
		   
		   Nota: (debido a que el movimiento todavía no se habría ejecutado al llamar a este método,
		         se necesita saber que tipo de ficha vamos a mover porque usando el método para
		         buscar la ficha de la posicion pFila y pColumna daría un espacio vacio*/

		
		
		
		
		boolean encadenar = false;
		int fi;
		int co;
		
		// Comprobar si puede comer adyacentes
		
		if (pFila > 1 && pColumna > 1 && pDireccion != 1) {
			if (this.esFichaEnemiga(Tablero.getMiTablero().obtenerFicha(pFila-1, pColumna-1)) && this.esVacio(Tablero.getMiTablero().obtenerFicha(pFila-2, pColumna-2))) {
			
				encadenar = true;	// puede encadenar salto diagonal arriba izquierda
		}
			}
		
		
		if (pFila > 1 && pColumna < 6 && pDireccion != 2 && !encadenar) {
			if (this.esFichaEnemiga(Tablero.getMiTablero().obtenerFicha(pFila-1, pColumna+1)) && this.esVacio(Tablero.getMiTablero().obtenerFicha(pFila-2, pColumna+2))) {
				
				encadenar = true; // puede encadenar salto diagonal arriba derecha
			}
			
		}
		
		if (pFila < 6 && pColumna > 1 && pDireccion != 3 && !encadenar && pEsDama) {
			if (this.esFichaEnemiga(Tablero.getMiTablero().obtenerFicha(pFila+1, pColumna-1)) && this.esVacio(Tablero.getMiTablero().obtenerFicha(pFila+2, pColumna-2))) {
				
				encadenar = true; // puede encadenar salto diagonal abajo izquierda
			}
			
		}
		
		if (pFila < 6 && pColumna < 6 && pDireccion != 4 && !encadenar && pEsDama) {
			if (this.esFichaEnemiga(Tablero.getMiTablero().obtenerFicha(pFila+1, pColumna+1)) && this.esVacio(Tablero.getMiTablero().obtenerFicha(pFila+2, pColumna+2))) {
				
				encadenar = true; // puede encadenar salto diagonal abajo derecha
			}
			
		}
		
		// Comprobar si come a distancia (mínimo un hueco vacío entre la ficha a mover y la víctima)
		
		
		if (pEsDama && !encadenar) {

			// Comprobar si come arriba a la izquierda
			fi = pFila - 1;
			co = pColumna - 1;
			
			if (!this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {fi = 1;} // para que no ignore si hay ficha a su lado

			
			while (fi > 1 && co > 1 && !encadenar && pDireccion != 4)  {
				
				fi = fi - 1;
				co = co - 1;
				
				if(this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
					fi = 1;
					
				} else if(this.esFichaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {
					if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi-1,co-1))) {
						encadenar = true;


						
						
					} else {fi = 1;}
					
				}
				
			}
			
			
		
		
		// Comprobar si come arriba a la derecha
			 
		fi = pFila - 1;
		co = pColumna + 1;
		
		if (!this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {fi = 1;} // para que no ignore si hay ficha a su lado
		
		while (fi > 1 && co < 6 && !encadenar && pDireccion != 3) {
			
			fi = fi -1;
			co = co + 1;
			
			if (this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
				fi = 1;
			} else if(this.esFichaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {
				if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi-1, co+1))) {
					encadenar = true;


					
					
				} else {fi = 1;}
					
			}
			
		}
		
			
		
		// comprobar si puede come abajo a la izquierda
		
		fi = pFila + 1;
		co = pColumna - 1;
		
		if (!this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {fi = 6;} // para que no ignore si hay ficha a su lado

		
		while (fi < 6 && co > 1 && !encadenar && pDireccion != 2 ) {
			
			fi = fi + 1;
			co = co - 1;
			
			if (this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
				fi = 6;
			} else if (this.esFichaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {
				if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi+1, co-1))) {
					encadenar  = true;
				

					
				} else {fi = 6;}
				
			}
			
		}
		

		
		// Comprobar si puede comer abajo a la derecha
		
		fi = pFila + 1;
		co = pColumna + 1;
		
		if (!this.esVacio(Tablero.getMiTablero().obtenerFicha(fi, co))) {fi = 6;} // para que no ignore si hay ficha a su lado

		
		while (fi < 6 && co < 6 && !encadenar && pDireccion != 1) {
			
			fi = fi + 1;
			co = co + 1;
			
			if (this.esFichaAliada(Tablero.getMiTablero().obtenerFicha(fi,co))) {
				fi = 6;
			} else if (this.esFichaEnemiga(Tablero.getMiTablero().obtenerFicha(fi,co))) {
				if (this.esVacio(Tablero.getMiTablero().obtenerFicha(fi+1, co+1))) {
					encadenar = true;
			

					
				} else {fi = 6;}
			
			}
			
			
		}
		}
		
		return encadenar;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
