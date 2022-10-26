package com.entrevista.Tenis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.entrevista.Tenis.Service.JugadorService;
import com.entrevista.Tenis.entity.Jugador;
import com.entrevista.Tenis.entity.Partido;

public class TenisApplication {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		JugadorService js = new JugadorService();
		String nombre1 = "";
		int probabilidad1 = 0;
		String nombre2 = "";
		String nombreTorneo = "";
		int bestOf = 0;
		int salir = 0;
		do {
			try { // Carga de datos del usuario
				System.out.println("Nombre Jugador 1");
				nombre1 = br.readLine();
				int aux = 0;
				do {
					System.out.println(
							"Probabilidad de ganar cada punto el Jugador 1 -- Debe ir entre 1 a 99 -- El jugador 2 toma la probabilidad restante");
					probabilidad1 = Integer.parseInt(br.readLine());
					if ((probabilidad1 >= 1) && (probabilidad1 <= 99)) {
						aux = 1;
					} else {
						System.out.println("La probabilidad debe ir entre 1 a 99");
					}
				} while (aux == 0);
				System.out.println("Nombre Jugador 2");
				nombre2 = br.readLine();
				System.out.println("Nombre del nombre del torneo");
				nombreTorneo = br.readLine();
				do {
					System.out.println("Al mejor de 3 o de 5");
					bestOf = Integer.parseInt(br.readLine());
					if (bestOf == 3 || bestOf == 5) {
						aux = 0;
					} else {
						System.out.print("Numero no valido... ");
					}
				} while (aux == 1);
				String repetir;
				do {
					List<Jugador> jugadores = js.cargarJugadores(nombre1, probabilidad1, nombre2);
					Partido partido = js.cargarPartido(jugadores, nombreTorneo, bestOf);
					js.simularPartido(partido);

					System.out.println("¿Desea repetir el partido?");
					repetir = br.readLine();
				} while (repetir.equals("si"));
				salir = 1;
			} catch (Exception e) {
				System.out.println("Dato ingresado no valido, intente nuevamente"); // Error salta cuando el usuario quiere cargar un char[] cuando se le pide un numero
			}

		} while (salir == 0);
		System.exit(0);
	}

}
