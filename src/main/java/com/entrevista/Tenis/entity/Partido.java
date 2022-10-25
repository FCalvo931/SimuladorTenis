package com.entrevista.Tenis.entity;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Partido {
	
	public Jugador jugador1;
	public Jugador jugador2;
	public String nombreTorneo;
	public int bestOf;
	public int saque;
	
	public Partido(Jugador jugador1, Jugador jugador2, String nombreTorneo, int bestOf, int saque) {
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.nombreTorneo = nombreTorneo;
		this.bestOf = bestOf;
		this.saque = saque;
		jugador1.game = new int[bestOf];
		jugador2.game = new int[bestOf];
		jugador1.tieBreak = new int[bestOf];
		jugador2.tieBreak = new int[bestOf];
	}
}
