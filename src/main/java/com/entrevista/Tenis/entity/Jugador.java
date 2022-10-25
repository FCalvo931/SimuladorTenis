package com.entrevista.Tenis.entity;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Jugador {

	public String nombre;
	public int probabilidadGanarPunto;
	public int game[];
	public int punto;
	public int set;
	public int tieBreak[];
	
	public Jugador(String nombre, int probabilidadGanarPunto) {
	this.nombre = nombre;
	this.probabilidadGanarPunto = probabilidadGanarPunto;
	}

	public Jugador() {
	}
}
