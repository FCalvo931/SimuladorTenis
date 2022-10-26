package com.entrevista.Tenis.Util;

import com.entrevista.Tenis.entity.Partido;

public class Util {
	
	public void mostrarPartido(Partido partido) {  // Mostrar partido
		System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
		System.out.println(partido.nombreTorneo + "\t\tPunto     \tGames(tiebreak)          \tTotal Set");
		if(partido.saque == 1){
			System.out.print(partido.jugador1.nombre + "\t\t " + partido.jugador1.punto + " \t- " );
			for (int i = 0; i < partido.jugador1.game.length; i++) {
				System.out.print(partido.jugador1.game[i] + "(" + partido.jugador1.tieBreak[i] + ") - ");
			}
			System.out.println("\t" +partido.jugador1.set);
			
			System.out.print(partido.jugador2.nombre + "\t\t " + partido.jugador2.punto + " \t- " );
			for (int i = 0; i < partido.jugador2.game.length; i++) {
				System.out.print(partido.jugador2.game[i] + "(" + partido.jugador2.tieBreak[i] + ") - ");
			}
			System.out.println("\t" +partido.jugador2.set);
		}else {
			System.out.print(partido.jugador2.nombre + "\t\t " + partido.jugador2.punto + " \t- " );
			for (int i = 0; i < partido.jugador2.game.length; i++) {
				System.out.print(partido.jugador2.game[i] + "(" + partido.jugador2.tieBreak[i] + ") - ");
			}
			System.out.println("\t" +partido.jugador2.set);
			
			System.out.print(partido.jugador1.nombre + "\t\t " + partido.jugador1.punto + " \t- " );
			for (int i = 0; i < partido.jugador1.game.length; i++) {
				System.out.print(partido.jugador1.game[i] + "(" + partido.jugador1.tieBreak[i] + ") - ");
			}
			System.out.println("\t" +partido.jugador1.set);
		}
		System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
	}
	
}
