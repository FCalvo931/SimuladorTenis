package com.entrevista.Tenis.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import com.entrevista.Tenis.Util.Util;
import com.entrevista.Tenis.entity.Jugador;
import com.entrevista.Tenis.entity.Partido;


public class JugadorService {
	
	public Util u = new Util(); 
	
	public List<Jugador> cargarJugadores(String nombre, int probabilidad1, String nombre2) {
		Jugador jugador1 = new Jugador(nombre, probabilidad1);
		int probabilidad2 = 100 - probabilidad1;
		Jugador jugador2 = new Jugador(nombre2, probabilidad2);
		List<Jugador> lJugadores = new ArrayList<>();
		lJugadores.add(jugador1);
		lJugadores.add(jugador2);
		return lJugadores;
	}
	
	public Partido cargarPartido(List<Jugador> jugadores,String nombreTorneo, int BestOf){
		Random rand = new Random();
		int saque = rand.nextInt(1, 2); // Se define que jugador va al primer saque, jugador 1 o jugador 2
		Partido partido = new Partido(jugadores.get(0),jugadores.get(1),nombreTorneo,BestOf, saque);
		return partido;
	}

	public void simularPartido(Partido partido) {
		int setEnJuego=0;
		Random rand = new Random();
		int i = 0;
		if (partido.bestOf == 5){
			i = 3; // Si el partido es un BO5 el que llega a 3 gana
		} else {
			i = 2; // Si el partido es un BO3 el que llega a 2 gana
		}
		do {
			if (rand.nextInt(1, 100) <= partido.jugador1.probabilidadGanarPunto) { // Gana punto jugador 1
					partido.jugador1.punto= partido.jugador1.punto + 15;
					
					if (partido.jugador1.punto == 45) { //Correccion con la suma de mas 15
						partido.jugador1.punto = 40;
					}

					if ((partido.jugador1.punto == 55 &&  partido.jugador2.punto <= 30)
							|| (partido.jugador1.punto == 70 && partido.jugador2.punto == 40)) { // Se verifica si gano o no el Game con el punto ganado
						
						partido.jugador1.punto = 0;
						partido.jugador2.punto = 0;

						partido.jugador1.game[setEnJuego] = partido.jugador1.game[setEnJuego] + 1;
						
						if (partido.saque == 1) {	// Al ganar el Game se pasa el saque al Jugador correspondiente
							partido.saque = 2;
						}else{
							partido.saque = 1;
						}

						if (partido.jugador1.game[setEnJuego] >= 6 && partido.jugador2.game[setEnJuego] <= (partido.jugador1.game[setEnJuego] - 2)) { // Se verifica si gano Set con el game ganado
							partido.jugador1.set = partido.jugador1.set + 1;
							setEnJuego++;
							
						} else if (partido.jugador1.game[setEnJuego] == 6 && partido.jugador2.game[setEnJuego] == 6) { // Tie Break
							partido = tiebreak(partido);
							partido.jugador1.tieBreak[setEnJuego] = partido.jugador1.punto;
							partido.jugador2.tieBreak[setEnJuego] = partido.jugador2.punto;
							partido.jugador2.punto = 0;
							partido.jugador1.punto = 0;
							setEnJuego++;
						}

					} else if (partido.jugador1.punto == 55 && partido.jugador2.punto == 55) { // El jugador 1 rompe laventaja de jugador 2
						partido.jugador1.punto = 40;
						partido.jugador2.punto = 40;
					}

				} else { // Gana punto jugador 2... Se repite como que el if de arriba cambiando Jugador 1 por Jugador 2
					partido.jugador2.punto= partido.jugador2.punto + 15;
					
					if (partido.jugador2.punto == 45) { // Correccion con la suma de mas 15
						partido.jugador2.punto = 40;
					}

					if ((partido.jugador2.punto == 55 &&  partido.jugador1.punto <= 30)
							|| (partido.jugador2.punto == 70 && partido.jugador1.punto == 40)) { // Se verifica si gano o no el Game con el punto ganado
						
						partido.jugador2.punto = 0;
						partido.jugador1.punto = 0;

						partido.jugador2.game[setEnJuego] = partido.jugador2.game[setEnJuego] + 1;
						
						if (partido.saque == 1) {// Al ganar el Game se pasa el saque al Jugador correspondiente
							partido.saque = 2;
						}else{
							partido.saque = 1;
						}

						if (partido.jugador2.game[setEnJuego] >= 6 && partido.jugador1.game[setEnJuego] <= (partido.jugador2.game[setEnJuego] - 2)) { // Se verifica si gano Set con el game ganado
							partido.jugador2.set = partido.jugador2.set + 1;
							setEnJuego++;
							
						} else if (partido.jugador2.game[setEnJuego] == 6 && partido.jugador1.game[setEnJuego] == 6) { // Tie Break
							partido = tiebreak(partido);
							partido.jugador1.tieBreak[setEnJuego] = partido.jugador1.punto;
							partido.jugador2.tieBreak[setEnJuego] = partido.jugador2.punto;
							partido.jugador2.punto = 0;
							partido.jugador1.punto = 0;
							setEnJuego++;
						}

					} else if (partido.jugador2.punto == 55 && partido.jugador1.punto == 55) { // El jugador 1 rompe laventaja de jugador 2
						partido.jugador2.punto = 40;
						partido.jugador1.punto = 40;
					}
				}
				
				u.mostrarPartido(partido);
		} while ((partido.jugador1.set < i) && (partido.jugador2.set < i)); // El partido continua hasta q haya un ganador
	}

	private Partido tiebreak(Partido partido) {
		Random rand = new Random();
		int i = 1; // Este auxliar va a servir para saber quien saca ya q en el Tie Break el saque es diferente
		int aux = 0;
		do {
			
			if (partido.saque == 1) { // El saque lo tiene jugador 1
				if (rand.nextInt(1, 100) <= partido.jugador1.probabilidadGanarPunto) {
					partido.jugador1.punto = partido.jugador1.punto + 1; // 
					if (i == 1) { // Se verifica si el saque cambia de Jugador o no
						partido.saque = 2;
						i=0;
					}
					else {
						i++;
					}
					
					if (partido.jugador1.punto >= 7 && partido.jugador2.punto <= (partido.jugador1.punto - 2)) { // Se verifica que con el punto ganado el Jugador haya o no ganado el set
						partido.jugador1.set = partido.jugador1.set + 1;
						aux = 1; // Con este auxiar salgo de la funcion ya que el SET se termino
					}
				} else {
					partido.jugador2.punto = partido.jugador2.punto + 1;
					if (i == 1) {
						partido.saque = 2;
						i=0;
					}
					else {
						i++;
					}
					
					if (partido.jugador2.punto >= 7 && partido.jugador1.punto <= (partido.jugador2.punto - 2)) {
						partido.jugador2.set = partido.jugador2.set + 1;
						aux = 1; // Con este auxiar salgo de la funcion ya que el SET se termino
					}
				}
				
				
			} else if (partido.saque == 2) {
				
				if (rand.nextInt(1, 100) <= partido.jugador1.probabilidadGanarPunto) {
					partido.jugador1.punto = partido.jugador1.punto + 1;
					if (i == 1) {
						partido.saque = 1;
						i=0;
					}
					else {
						i++;
					}
					
					if (partido.jugador1.punto >= 7 && partido.jugador2.punto <= (partido.jugador1.punto - 2)) {
						partido.jugador1.set = partido.jugador1.set + 1;
						aux = 1; // Con este auxiar salgo de la funcion ya que el SET se termino
					}
				} else {
					partido.jugador2.punto = partido.jugador2.punto + 1;
					if (i == 1) {
						partido.saque = 1;
						i=0;
					}
					else {
						i++;
					}
					if (partido.jugador2.punto >= 7 && partido.jugador1.punto <= (partido.jugador2.punto - 2)) {
						partido.jugador2.set = partido.jugador2.set + 1;
						aux = 1; // Con este auxiar salgo de la funcion ya que el SET se termino
					}
				}
			}
			u.mostrarPartido(partido); // Muestro el partido
		} while (aux == 0);
		return partido;
	}

}
