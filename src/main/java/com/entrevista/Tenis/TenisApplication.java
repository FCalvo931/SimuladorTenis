package com.entrevista.Tenis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.entrevista.Tenis.Service.JugadorService;
import com.entrevista.Tenis.entity.Jugador;
import com.entrevista.Tenis.entity.Partido;

@SpringBootApplication
public class TenisApplication {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		SpringApplication.run(TenisApplication.class, args);
		JugadorService js = new JugadorService();
		int salir = 0;
		do {
			System.out.println("Nombre Jugador 1");
			String nombre1 = br.readLine();
			System.out.println("Probabilidad de ganar cada punto el Jugador 1");
			int probabilidad1 = Integer.parseInt(br.readLine());
			System.out.println("Nombre Jugador 2");
			String nombre2 = br.readLine();
			
			System.out.println("Nombre del nombre del torneo");
			String nombreTorneo = br.readLine();

			System.out.println("Al mejor de 5 o de 3");
			int bestOf = Integer.parseInt(br.readLine());
			if (bestOf == 3 || bestOf == 5) {
				if ((probabilidad1 >= 1) && (probabilidad1 <= 99)) {
					String repetir;
					do {
					List<Jugador> jugadores = js.cargarJugadores(nombre1, probabilidad1, nombre2);
					Partido partido = js.cargarPartido(jugadores, nombreTorneo, bestOf);
					js.simularPartido(partido);
					
					System.out.println("Desea volver a simular el partido?");
					repetir = br.readLine();
					}while (repetir.equals("si"));
				}
			}

		} while (salir == 0);

	}

}
